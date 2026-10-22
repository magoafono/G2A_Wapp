package it.cnr.ilc.g2a.datahandler;

import it.cnr.ilc.g2a.utils.XmlSecurity;
import java.io.StringReader;
import java.util.Map;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XQueryService;

/** One server-side content replacement for all pericopes involved in a transfer.
 * XML is an external STRING, never XQuery source. Do not escape/modify corpus text.
 */
public final class PericopeUpdates {
    public static final String NAMESPACE = "http://ilc.cnr.it/gtoa/Model/xmlmapping";
    public static final String VARIABLE = "g2aPericopeUpdates";
    private static final Namespace XM = Namespace.getNamespace("xm", NAMESPACE);

    // Validate every replacement before updating. The document lock covers the
    // read/copy/replace, so concurrent updates to unrelated pericopes are retained.
    // eXist cannot replace a document root; update its content once, retaining
    // the root element, attributes and namespaces. Construct an in-memory copy
    // of ALL content first: persistent child references would be invalidated
    // when eXist clears the old content during update value.
    // A SINGLE update operation prevents source-only/target-only commits.
    public static final String QUERY =
        "declare namespace xm='" + NAMESPACE + "';\n"
        + "declare namespace util='http://exist-db.org/xquery/util';\n"
        + "declare variable $g2aPericopeUpdates as xs:string external;\n"
        + "let $items := parse-xml(util:base64-decode($g2aPericopeUpdates))/updates/item\n"
        + "return util:exclusive-lock(doc('pericopes.xml'),\n"
        + "  let $root := doc('pericopes.xml')/xm:sequence\n"
        + "  return if (count($root) = 1 and exists($items)\n"
        + "    and count(distinct-values($items/@id)) = count($items)\n"
        + "    and (every $i in $items satisfies\n"
        + "      (count($i/*) = 1 and count($i/xm:sequence) = 1\n"
        + "       and string($i/@id) = string($i/xm:sequence/@id)\n"
        + "       and $i/xm:sequence/@classname = 'pericope'\n"
        + "       and count($root/xm:sequence[@id = $i/@id]) = 1)))\n"
        + "  then (\n"
        + "    let $content := element g2a-content {\n"
        + "      for $child in $root/node() return\n"
        + "        if ($child instance of element(xm:sequence) and $child/@id = $items/@id)\n"
        + "        then $items[@id = $child/@id]/xm:sequence else $child\n"
        + "    }\n"
        + "    return update value $root with $content/node()\n"
        + "  )\n"
        + "  else error(xs:QName('G2A_INVALID_PERICOPE_UPDATE'), 'Invalid pericope update')\n"
        + ")";

    private PericopeUpdates() { }

    public static String payload(Map<String, String> replacements) {
        if (replacements == null || replacements.isEmpty()) {
            throw new IllegalArgumentException("No pericope replacements");
        }
        Element updates = new Element("updates");
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            String id = entry.getKey();
            if (id == null || id.isEmpty() || entry.getValue() == null) {
                throw new IllegalArgumentException("Missing pericope replacement");
            }
            try {
                // Legacy XmlBeans emits xm:sequence with an implicit xm binding.
                // Supply it while parsing, then serialize a standalone XML plan.
                String wrapped = "<fragment xmlns:xm=\"" + NAMESPACE + "\">"
                    + entry.getValue() + "</fragment>";
                Element fragment = XmlSecurity.newSaxBuilder().build(new StringReader(wrapped)).getRootElement();
                Element sequence = fragment.getChild("sequence", XM);
                if (fragment.getChildren().size() != 1 || sequence == null
                        || !fragment.getTextTrim().isEmpty()
                        || !id.equals(sequence.getAttributeValue("id"))
                        || !"pericope".equals(sequence.getAttributeValue("classname"))) {
                    throw new IllegalArgumentException("Invalid pericope replacement");
                }
                updates.addContent(new Element("item").setAttribute("id", id).addContent(sequence.detach()));
            } catch (org.jdom2.JDOMException | java.io.IOException ex) {
                throw new IllegalArgumentException("Invalid pericope XML", ex);
            }
        }
        return new XMLOutputter(Format.getRawFormat()).outputString(new Document(updates));
    }

    /** The caller supplies a fresh query service/collection (no shared variables). */
    public static void execute(XQueryService service, Map<String, String> replacements) throws XMLDBException {
        String data = payload(replacements); // Serialize/validate ALL before contacting the DB.
        // The legacy remote client/server string conversion expands XML entities.
        // Transport UTF-8 as base64 so decoding occurs once, inside the fixed query,
        // immediately before XML parsing; corpus values themselves are unchanged.
        service.declareVariable(VARIABLE, Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8)));
        ResourceSet result = service.query(QUERY);
        if (result == null) {
            throw new IllegalStateException("Missing acknowledgement for pericope update");
        }
        result.clear();
    }
}
