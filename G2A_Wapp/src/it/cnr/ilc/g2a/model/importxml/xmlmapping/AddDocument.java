/*
 * An XML document type.
 * Localname: add
 * Namespace: http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/
 * Java type: it.cnr.ilc.cophi.model.importxml.xmlmapping.AddDocument
 *
 * Automatically generated - do not modify.
 */
package it.cnr.ilc.g2a.model.importxml.xmlmapping;


/**
 * A document containing one add(@http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/) element.
 *
 * This is a complex type.
 */
public interface AddDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(AddDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sF326F268E745C51FDDE5C4B9D0AE203B").resolveHandle("add04f6doctype");
    
    /**
     * Gets the "add" element
     */
    it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument.Add getAdd();
    
    /**
     * Sets the "add" element
     */
    void setAdd(it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument.Add add);
    
    /**
     * Appends and returns a new empty "add" element
     */
    it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument.Add addNewAdd();
    
    /**
     * An XML add(@http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/).
     *
     * This is a complex type.
     */
    public interface Add extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Add.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sF326F268E745C51FDDE5C4B9D0AE203B").resolveHandle("addb3c3elemtype");
        
        /**
         * Gets the "doc" element
         */
        it.cnr.ilc.g2a.model.importxml.xmlmapping.DocDocument.Doc getDoc();
        
        /**
         * Sets the "doc" element
         */
        void setDoc(it.cnr.ilc.g2a.model.importxml.xmlmapping.DocDocument.Doc doc);
        
        /**
         * Appends and returns a new empty "doc" element
         */
        it.cnr.ilc.g2a.model.importxml.xmlmapping.DocDocument.Doc addNewDoc();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument.Add newInstance() {
              return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument.Add) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument.Add newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument.Add) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument newInstance() {
          return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
