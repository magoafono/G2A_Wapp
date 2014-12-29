/*
 * An XML document type.
 * Localname: element
 * Namespace: http://ilc.cnr.it/Cophi/Model/xmlmapping
 * Java type: it.cnr.ilc.cophi.model.xmlmapping.ElementDocument
 *
 * Automatically generated - do not modify.
 */
package it.cnr.ilc.cophi.model.xmlmapping;


/**
 * A document containing one element(@http://ilc.cnr.it/Cophi/Model/xmlmapping) element.
 *
 * This is a complex type.
 */
public interface ElementDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ElementDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1009950AD0F8E97D03B7B641559DF25B").resolveHandle("elementec41doctype");
    
    /**
     * Gets the "element" element
     */
    it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element getElement();
    
    /**
     * Sets the "element" element
     */
    void setElement(it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element element);
    
    /**
     * Appends and returns a new empty "element" element
     */
    it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element addNewElement();
    
    /**
     * An XML element(@http://ilc.cnr.it/Cophi/Model/xmlmapping).
     *
     * This is a complex type.
     */
    public interface Element extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Element.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s1009950AD0F8E97D03B7B641559DF25B").resolveHandle("element07d9elemtype");
        
        /**
         * Gets array of all "param" elements
         */
        it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param[] getParamArray();
        
        /**
         * Gets ith "param" element
         */
        it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param getParamArray(int i);
        
        /**
         * Returns number of "param" element
         */
        int sizeOfParamArray();
        
        /**
         * Sets array of all "param" element
         */
        void setParamArray(it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param[] paramArray);
        
        /**
         * Sets ith "param" element
         */
        void setParamArray(int i, it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param param);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "param" element
         */
        it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param insertNewParam(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "param" element
         */
        it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param addNewParam();
        
        /**
         * Removes the ith "param" element
         */
        void removeParam(int i);
        
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
         * Gets the "classname" attribute
         */
        java.lang.String getClassname();
        
        /**
         * Gets (as xml) the "classname" attribute
         */
        org.apache.xmlbeans.XmlNMTOKEN xgetClassname();
        
        /**
         * Sets the "classname" attribute
         */
        void setClassname(java.lang.String classname);
        
        /**
         * Sets (as xml) the "classname" attribute
         */
        void xsetClassname(org.apache.xmlbeans.XmlNMTOKEN classname);
        
        /**
         * Gets the "extended" attribute
         */
        java.lang.String getExtended();
        
        /**
         * Gets (as xml) the "extended" attribute
         */
        org.apache.xmlbeans.XmlNMTOKEN xgetExtended();
        
        /**
         * True if has "extended" attribute
         */
        boolean isSetExtended();
        
        /**
         * Sets the "extended" attribute
         */
        void setExtended(java.lang.String extended);
        
        /**
         * Sets (as xml) the "extended" attribute
         */
        void xsetExtended(org.apache.xmlbeans.XmlNMTOKEN extended);
        
        /**
         * Unsets the "extended" attribute
         */
        void unsetExtended();
        
        /**
         * Gets the "ref" attribute
         */
        java.lang.String getRef();
        
        /**
         * Gets (as xml) the "ref" attribute
         */
        org.apache.xmlbeans.XmlNMTOKEN xgetRef();
        
        /**
         * True if has "ref" attribute
         */
        boolean isSetRef();
        
        /**
         * Sets the "ref" attribute
         */
        void setRef(java.lang.String ref);
        
        /**
         * Sets (as xml) the "ref" attribute
         */
        void xsetRef(org.apache.xmlbeans.XmlNMTOKEN ref);
        
        /**
         * Unsets the "ref" attribute
         */
        void unsetRef();
        
        /**
         * Gets the "type" attribute
         */
        java.lang.String getType();
        
        /**
         * Gets (as xml) the "type" attribute
         */
        org.apache.xmlbeans.XmlNMTOKEN xgetType();
        
        /**
         * True if has "type" attribute
         */
        boolean isSetType();
        
        /**
         * Sets the "type" attribute
         */
        void setType(java.lang.String type);
        
        /**
         * Sets (as xml) the "type" attribute
         */
        void xsetType(org.apache.xmlbeans.XmlNMTOKEN type);
        
        /**
         * Unsets the "type" attribute
         */
        void unsetType();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element newInstance() {
              return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument newInstance() {
          return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static it.cnr.ilc.cophi.model.xmlmapping.ElementDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
