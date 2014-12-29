/*
 * An XML document type.
 * Localname: field
 * Namespace: http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/
 * Java type: it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument
 *
 * Automatically generated - do not modify.
 */
package it.cnr.ilc.cophi.model.importxml.xmlmapping;


/**
 * A document containing one field(@http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/) element.
 *
 * This is a complex type.
 */
public interface FieldDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(FieldDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sF326F268E745C51FDDE5C4B9D0AE203B").resolveHandle("fielda50fdoctype");
    
    /**
     * Gets the "field" element
     */
    it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field getField();
    
    /**
     * Sets the "field" element
     */
    void setField(it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field field);
    
    /**
     * Appends and returns a new empty "field" element
     */
    it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field addNewField();
    
    /**
     * An XML field(@http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/).
     *
     * This is a complex type.
     */
    public interface Field extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Field.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sF326F268E745C51FDDE5C4B9D0AE203B").resolveHandle("field4f75elemtype");
        
        /**
         * Gets array of all "w" elements
         */
        it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W[] getWArray();
        
        /**
         * Gets ith "w" element
         */
        it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W getWArray(int i);
        
        /**
         * Returns number of "w" element
         */
        int sizeOfWArray();
        
        /**
         * Sets array of all "w" element
         */
        void setWArray(it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W[] wArray);
        
        /**
         * Sets ith "w" element
         */
        void setWArray(int i, it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W w);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "w" element
         */
        it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W insertNewW(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "w" element
         */
        it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W addNewW();
        
        /**
         * Removes the ith "w" element
         */
        void removeW(int i);
        
        /**
         * Gets the "name" attribute
         */
        java.lang.String getName();
        
        /**
         * Gets (as xml) the "name" attribute
         */
        org.apache.xmlbeans.XmlNCName xgetName();
        
        /**
         * Sets the "name" attribute
         */
        void setName(java.lang.String name);
        
        /**
         * Sets (as xml) the "name" attribute
         */
        void xsetName(org.apache.xmlbeans.XmlNCName name);
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field newInstance() {
              return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument newInstance() {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
