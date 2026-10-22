package it.cnr.ilc.g2a.model.handler;

import it.cnr.ilc.g2a.model.Reference;
import it.cnr.ilc.g2a.model.text.PericopeText;
import it.cnr.ilc.g2a.model.text.RefTokenText;
import it.cnr.ilc.g2a.model.text.TokenText;
import it.cnr.ilc.g2a.utils.G2ASort;
import java.util.*;

/** Stage the existing ADD semantics without modifying the shared model before DB acknowledgement. */
public final class PericopeTransfer {
    private PericopeTransfer() { }

    public static LinkedHashMap<String, PericopeText> plan(List<String> tokenIds, String targetId,
            List<String> sourceIds, Map<String, PericopeText> originals, Map<String, TokenText> tokens) {
        if (tokenIds == null || tokenIds.isEmpty() || sourceIds == null || targetId == null) {
            throw new IllegalArgumentException("Missing pericope selection");
        }
        LinkedHashSet<String> ids = new LinkedHashSet<>(sourceIds);
        ids.add(targetId);
        LinkedHashMap<String, PericopeText> planned = new LinkedHashMap<>();
        for (String id : ids) {
            PericopeText original = originals.get(id);
            if (original == null) throw new IllegalArgumentException("Missing pericope");
            PericopeText copy = new PericopeText();
            copy.setId(original.getId());
            copy.setType(original.getType());
            copy.setClassname(original.getClassname());
            copy.setExtended(original.getExtended());
            copy.setInfo(original.getInfo());
            copy.setValue(new ArrayList<Reference>(original.getValue()));
            planned.put(id, copy);
        }
        for (String id : sourceIds) {
            List<Reference> retained = new ArrayList<>();
            for (Reference ref : planned.get(id).getValue()) {
                if (!tokenIds.contains(ref.getRef())) retained.add(ref);
            }
            planned.get(id).setValue(retained);
        }
        PericopeText target = planned.get(targetId);
        Set<String> present = new HashSet<>();
        for (Reference ref : target.getValue()) present.add(ref.getRef());
        for (String id : tokenIds) {
            if (present.add(id)) {
                TokenText token = tokens.get(id);
                if (token == null) throw new IllegalArgumentException("Missing selected token");
                RefTokenText ref = new RefTokenText();
                ref.setId("ref_" + id);
                ref.setRef(id);
                ref.setTok(token);
                ref.setExtended(token.getText());
                target.getValue().add(ref);
            }
        }
        Collections.sort(target.getValue(), G2ASort.TOKENREF_FROM_ORDER);
        return planned;
    }
}
