/*
 * An XML document type.
 * Localname: field
 * Namespace: http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/
 * Java type: it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument
 *
 * Automatically generated - do not modify.
 */
package it.cnr.ilc.cophi.model.importxml.xmlmapping.impl;
/**
 * A document containing one field(@http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/) element.
 *
 * This is a complex type.
 */
public class FieldDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument
{
    private static final long serialVersionUID = 1L;
    
    public FieldDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FIELD$0 = 
        new javax.xml.namespace.QName("http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/", "field");
    
    
    /**
     * Gets the "field" element
     */
    public it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field getField()
    {
        synchronized (monitor())
        {
            check_orphaned();
            it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field target = null;
            target = (it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field)get_store().find_element_user(FIELD$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "field" element
     */
    public void setField(it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field field)
    {
        generatedSetterHelperImpl(field, FIELD$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "field" element
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
     * An XML field(@http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/).
     *
     * This is a complex type.
     */
    public static class FieldImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field
    {
        private static final long serialVersionUID = 1L;
        
        public FieldImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName W$0 = 
            new javax.xml.namespace.QName("http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/", "w");
        private static final javax.xml.namespace.QName NAME$2 = 
            new javax.xml.namespace.QName("", "name");
        
        
        /**
         * Gets array of all "w" elements
         */
        public it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W[] getWArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(W$0, targetList);
                it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W[] result = new it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "w" element
         */
        public it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W getWArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W target = null;
                target = (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W)get_store().find_element_user(W$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "w" element
         */
        public int sizeOfWArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(W$0);
            }
        }
        
        /**
         * Sets array of all "w" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setWArray(it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W[] wArray)
        {
            check_orphaned();
            arraySetterHelper(wArray, W$0);
        }
        
        /**
         * Sets ith "w" element
         */
        public void setWArray(int i, it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W w)
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W target = null;
                target = (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W)get_store().find_element_user(W$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(w);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "w" element
         */
        public it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W insertNewW(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W target = null;
                target = (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W)get_store().insert_element_user(W$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "w" element
         */
        public it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W addNewW()
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W target = null;
                target = (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W)get_store().add_element_user(W$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "w" element
         */
        public void removeW(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(W$0, i);
            }
        }
        
        /**
         * Gets the "name" attribute
         */
        public java.lang.String getName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$2);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "name" attribute
         */
        public org.apache.xmlbeans.XmlNCName xgetName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlNCName target = null;
                target = (org.apache.xmlbeans.XmlNCName)get_store().find_attribute_user(NAME$2);
                return target;
            }
        }
        
        /**
         * Sets the "name" attribute
         */
        public void setName(java.lang.String name)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$2);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(NAME$2);
                }
                target.setStringValue(name);
            }
        }
        
        /**
         * Sets (as xml) the "name" attribute
         */
        public void xsetName(org.apache.xmlbeans.XmlNCName name)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlNCName target = null;
                target = (org.apache.xmlbeans.XmlNCName)get_store().find_attribute_user(NAME$2);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlNCName)get_store().add_attribute_user(NAME$2);
                }
                target.set(name);
            }
        }
    }
}
