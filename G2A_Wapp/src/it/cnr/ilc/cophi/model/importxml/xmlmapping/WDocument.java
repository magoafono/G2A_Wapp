/*
 * An XML document type.
 * Localname: w
 * Namespace: http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/
 * Java type: it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument
 *
 * Automatically generated - do not modify.
 */
package it.cnr.ilc.cophi.model.importxml.xmlmapping;


/**
 * A document containing one w(@http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/) element.
 *
 * This is a complex type.
 */
public interface WDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(WDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sF326F268E745C51FDDE5C4B9D0AE203B").resolveHandle("w1b8cdoctype");
    
    /**
     * Gets the "w" element
     */
    it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W getW();
    
    /**
     * Sets the "w" element
     */
    void setW(it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W w);
    
    /**
     * Appends and returns a new empty "w" element
     */
    it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W addNewW();
    
    /**
     * An XML w(@http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/).
     *
     * This is a complex type.
     */
    public interface W extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(W.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sF326F268E745C51FDDE5C4B9D0AE203B").resolveHandle("w216felemtype");
        
        /**
         * Gets the "bibref" attribute
         */
        java.lang.String getBibref();
        
        /**
         * Gets (as xml) the "bibref" attribute
         */
        org.apache.xmlbeans.XmlNMTOKEN xgetBibref();
        
        /**
         * True if has "bibref" attribute
         */
        boolean isSetBibref();
        
        /**
         * Sets the "bibref" attribute
         */
        void setBibref(java.lang.String bibref);
        
        /**
         * Sets (as xml) the "bibref" attribute
         */
        void xsetBibref(org.apache.xmlbeans.XmlNMTOKEN bibref);
        
        /**
         * Unsets the "bibref" attribute
         */
        void unsetBibref();
        
        /**
         * Gets the "end" attribute
         */
        java.math.BigInteger getEnd();
        
        /**
         * Gets (as xml) the "end" attribute
         */
        org.apache.xmlbeans.XmlInteger xgetEnd();
        
        /**
         * Sets the "end" attribute
         */
        void setEnd(java.math.BigInteger end);
        
        /**
         * Sets (as xml) the "end" attribute
         */
        void xsetEnd(org.apache.xmlbeans.XmlInteger end);
        
        /**
         * Gets the "form" attribute
         */
        org.apache.xmlbeans.XmlAnySimpleType getForm();
        
        /**
         * Sets the "form" attribute
         */
        void setForm(org.apache.xmlbeans.XmlAnySimpleType form);
        
        /**
         * Appends and returns a new empty "form" attribute
         */
        org.apache.xmlbeans.XmlAnySimpleType addNewForm();
        
        /**
         * Gets the "id" attribute
         */
        java.lang.String getId();
        
        /**
         * Gets (as xml) the "id" attribute
         */
        org.apache.xmlbeans.XmlNMTOKEN xgetId();
        
        /**
         * Sets the "id" attribute
         */
        void setId(java.lang.String id);
        
        /**
         * Sets (as xml) the "id" attribute
         */
        void xsetId(org.apache.xmlbeans.XmlNMTOKEN id);
        
        /**
         * Gets the "lemma" attribute
         */
        org.apache.xmlbeans.XmlAnySimpleType getLemma();
        
        /**
         * Sets the "lemma" attribute
         */
        void setLemma(org.apache.xmlbeans.XmlAnySimpleType lemma);
        
        /**
         * Appends and returns a new empty "lemma" attribute
         */
        org.apache.xmlbeans.XmlAnySimpleType addNewLemma();
        
        /**
         * Gets the "pos" attribute
         */
        org.apache.xmlbeans.XmlAnySimpleType getPos();
        
        /**
         * Sets the "pos" attribute
         */
        void setPos(org.apache.xmlbeans.XmlAnySimpleType pos);
        
        /**
         * Appends and returns a new empty "pos" attribute
         */
        org.apache.xmlbeans.XmlAnySimpleType addNewPos();
        
        /**
         * Gets the "prog" attribute
         */
        java.math.BigInteger getProg();
        
        /**
         * Gets (as xml) the "prog" attribute
         */
        org.apache.xmlbeans.XmlInteger xgetProg();
        
        /**
         * Sets the "prog" attribute
         */
        void setProg(java.math.BigInteger prog);
        
        /**
         * Sets (as xml) the "prog" attribute
         */
        void xsetProg(org.apache.xmlbeans.XmlInteger prog);
        
        /**
         * Gets the "root" attribute
         */
        org.apache.xmlbeans.XmlAnySimpleType getRoot();
        
        /**
         * True if has "root" attribute
         */
        boolean isSetRoot();
        
        /**
         * Sets the "root" attribute
         */
        void setRoot(org.apache.xmlbeans.XmlAnySimpleType root);
        
        /**
         * Appends and returns a new empty "root" attribute
         */
        org.apache.xmlbeans.XmlAnySimpleType addNewRoot();
        
        /**
         * Unsets the "root" attribute
         */
        void unsetRoot();
        
        /**
         * Gets the "start" attribute
         */
        java.math.BigInteger getStart();
        
        /**
         * Gets (as xml) the "start" attribute
         */
        org.apache.xmlbeans.XmlInteger xgetStart();
        
        /**
         * Sets the "start" attribute
         */
        void setStart(java.math.BigInteger start);
        
        /**
         * Sets (as xml) the "start" attribute
         */
        void xsetStart(org.apache.xmlbeans.XmlInteger start);
        
        /**
         * Gets the "token" attribute
         */
        org.apache.xmlbeans.XmlAnySimpleType getToken();
        
        /**
         * Sets the "token" attribute
         */
        void setToken(org.apache.xmlbeans.XmlAnySimpleType token);
        
        /**
         * Appends and returns a new empty "token" attribute
         */
        org.apache.xmlbeans.XmlAnySimpleType addNewToken();
        
        /**
         * Gets the "uppercaseform" attribute
         */
        java.lang.String getUppercaseform();
        
        /**
         * Gets (as xml) the "uppercaseform" attribute
         */
        org.apache.xmlbeans.XmlNCName xgetUppercaseform();
        
        /**
         * True if has "uppercaseform" attribute
         */
        boolean isSetUppercaseform();
        
        /**
         * Sets the "uppercaseform" attribute
         */
        void setUppercaseform(java.lang.String uppercaseform);
        
        /**
         * Sets (as xml) the "uppercaseform" attribute
         */
        void xsetUppercaseform(org.apache.xmlbeans.XmlNCName uppercaseform);
        
        /**
         * Unsets the "uppercaseform" attribute
         */
        void unsetUppercaseform();
        
        /**
         * Gets the "voc" attribute
         */
        org.apache.xmlbeans.XmlAnySimpleType getVoc();
        
        /**
         * True if has "voc" attribute
         */
        boolean isSetVoc();
        
        /**
         * Sets the "voc" attribute
         */
        void setVoc(org.apache.xmlbeans.XmlAnySimpleType voc);
        
        /**
         * Appends and returns a new empty "voc" attribute
         */
        org.apache.xmlbeans.XmlAnySimpleType addNewVoc();
        
        /**
         * Unsets the "voc" attribute
         */
        void unsetVoc();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W newInstance() {
              return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument newInstance() {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
