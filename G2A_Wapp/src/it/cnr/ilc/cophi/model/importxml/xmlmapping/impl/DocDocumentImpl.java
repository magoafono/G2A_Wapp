/*
 * An XML document type.
 * Localname: doc
 * Namespace: http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/
 * Java type: it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument
 *
 * Automatically generated - do not modify.
 */
package it.cnr.ilc.cophi.model.importxml.xmlmapping.impl;
/**
 * A document containing one doc(@http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/) element.
 *
 * This is a complex type.
 */
public class DocDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument
{
    private static final long serialVersionUID = 1L;
    
    public DocDocumentImpl(org.apache.xmlbeans.SchemaType sType)
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
    /**
     * An XML doc(@http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/).
     *
     * This is a complex type.
     */
    public static class DocImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument.Doc
    {
        private static final long serialVersionUID = 1L;
        
        public DocImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName FIELD$0 = 
            new javax.xml.namespace.QName("http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/", "field");
        
        
        /**
         * Gets array of all "field" elements
         */
        public it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field[] getFieldArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(FIELD$0, targetList);
                it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field[] result = new it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "field" element
         */
        public it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field getFieldArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field target = null;
                target = (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field)get_store().find_element_user(FIELD$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "field" element
         */
        public int sizeOfFieldArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(FIELD$0);
            }
        }
        
        /**
         * Sets array of all "field" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setFieldArray(it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field[] fieldArray)
        {
            check_orphaned();
            arraySetterHelper(fieldArray, FIELD$0);
        }
        
        /**
         * Sets ith "field" element
         */
        public void setFieldArray(int i, it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field field)
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field target = null;
                target = (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field)get_store().find_element_user(FIELD$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(field);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "field" element
         */
        public it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field insertNewField(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field target = null;
                target = (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field)get_store().insert_element_user(FIELD$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "field" element
         */
        public it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field addNewField()
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field target = null;
                target = (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field)get_store().add_element_user(FIELD$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "field" element
         */
        public void removeField(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(FIELD$0, i);
            }
        }
    }
}
