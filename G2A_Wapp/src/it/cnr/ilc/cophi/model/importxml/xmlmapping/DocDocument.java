/*
 * An XML document type.
 * Localname: doc
 * Namespace: http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/
 * Java type: it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument
 *
 * Automatically generated - do not modify.
 */
package it.cnr.ilc.cophi.model.importxml.xmlmapping;


/**
 * A document containing one doc(@http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/) element.
 *
 * This is a complex type.
 */
public interface DocDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(DocDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sF326F268E745C51FDDE5C4B9D0AE203B").resolveHandle("docc40ddoctype");
    
    /**
     * Gets the "doc" element
     */
    it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument.Doc getDoc();
    
    /**
     * Sets the "doc" element
     */
    void setDoc(it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument.Doc doc);
    
    /**
     * Appends and returns a new empty "doc" element
     */
    it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument.Doc addNewDoc();
    
    /**
     * An XML doc(@http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/).
     *
     * This is a complex type.
     */
    public interface Doc extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Doc.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sF326F268E745C51FDDE5C4B9D0AE203B").resolveHandle("doc44b1elemtype");
        
        /**
         * Gets array of all "field" elements
         */
        it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field[] getFieldArray();
        
        /**
         * Gets ith "field" element
         */
        it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field getFieldArray(int i);
        
        /**
         * Returns number of "field" element
         */
        int sizeOfFieldArray();
        
        /**
         * Sets array of all "field" element
         */
        void setFieldArray(it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field[] fieldArray);
        
        /**
         * Sets ith "field" element
         */
        void setFieldArray(int i, it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field field);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "field" element
         */
        it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field insertNewField(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "field" element
         */
        it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field addNewField();
        
        /**
         * Removes the ith "field" element
         */
        void removeField(int i);
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument.Doc newInstance() {
              return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument.Doc) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument.Doc newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument.Doc) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument newInstance() {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
