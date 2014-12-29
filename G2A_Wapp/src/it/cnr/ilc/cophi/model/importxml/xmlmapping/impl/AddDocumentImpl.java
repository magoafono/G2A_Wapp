/*
 * An XML document type.
 * Localname: add
 * Namespace: http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/
 * Java type: it.cnr.ilc.cophi.model.importxml.xmlmapping.AddDocument
 *
 * Automatically generated - do not modify.
 */
package it.cnr.ilc.cophi.model.importxml.xmlmapping.impl;
/**
 * A document containing one add(@http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/) element.
 *
 * This is a complex type.
 */
public class AddDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements it.cnr.ilc.cophi.model.importxml.xmlmapping.AddDocument
{
    private static final long serialVersionUID = 1L;
    
    public AddDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ADD$0 = 
        new javax.xml.namespace.QName("http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/", "add");
    
    
    /**
     * Gets the "add" element
     */
    public it.cnr.ilc.cophi.model.importxml.xmlmapping.AddDocument.Add getAdd()
    {
        synchronized (monitor())
        {
            check_orphaned();
            it.cnr.ilc.cophi.model.importxml.xmlmapping.AddDocument.Add target = null;
            target = (it.cnr.ilc.cophi.model.importxml.xmlmapping.AddDocument.Add)get_store().find_element_user(ADD$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "add" element
     */
    public void setAdd(it.cnr.ilc.cophi.model.importxml.xmlmapping.AddDocument.Add add)
    {
        generatedSetterHelperImpl(add, ADD$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "add" element
     */
    public it.cnr.ilc.cophi.model.importxml.xmlmapping.AddDocument.Add addNewAdd()
    {
        synchronized (monitor())
        {
            check_orphaned();
            it.cnr.ilc.cophi.model.importxml.xmlmapping.AddDocument.Add target = null;
            target = (it.cnr.ilc.cophi.model.importxml.xmlmapping.AddDocument.Add)get_store().add_element_user(ADD$0);
            return target;
        }
    }
    /**
     * An XML add(@http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/).
     *
     * This is a complex type.
     */
    public static class AddImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements it.cnr.ilc.cophi.model.importxml.xmlmapping.AddDocument.Add
    {
        private static final long serialVersionUID = 1L;
        
        public AddImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName DOC$0 = 
            new javax.xml.namespace.QName("http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/", "doc");
        
        
        /**
         * Gets the "doc" element
         */
        public it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument.Doc getDoc()
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument.Doc target = null;
                target = (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument.Doc)get_store().find_element_user(DOC$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "doc" element
         */
        public void setDoc(it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument.Doc doc)
        {
            generatedSetterHelperImpl(doc, DOC$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "doc" element
         */
        public it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument.Doc addNewDoc()
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument.Doc target = null;
                target = (it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument.Doc)get_store().add_element_user(DOC$0);
                return target;
            }
        }
    }
}
