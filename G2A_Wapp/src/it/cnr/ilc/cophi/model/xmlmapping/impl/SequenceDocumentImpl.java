/*
 * An XML document type.
 * Localname: sequence
 * Namespace: http://ilc.cnr.it/Cophi/Model/xmlmapping
 * Java type: it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument
 *
 * Automatically generated - do not modify.
 */
package it.cnr.ilc.cophi.model.xmlmapping.impl;
/**
 * A document containing one sequence(@http://ilc.cnr.it/Cophi/Model/xmlmapping) element.
 *
 * This is a complex type.
 */
public class SequenceDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument
{
    private static final long serialVersionUID = 1L;
    
    public SequenceDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SEQUENCE$0 = 
        new javax.xml.namespace.QName("http://ilc.cnr.it/Cophi/Model/xmlmapping", "sequence");
    
    
    /**
     * Gets the "sequence" element
     */
    public it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence getSequence()
    {
        synchronized (monitor())
        {
            check_orphaned();
            it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence target = null;
            target = (it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence)get_store().find_element_user(SEQUENCE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "sequence" element
     */
    public void setSequence(it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence sequence)
    {
        generatedSetterHelperImpl(sequence, SEQUENCE$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "sequence" element
     */
    public it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence addNewSequence()
    {
        synchronized (monitor())
        {
            check_orphaned();
            it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence target = null;
            target = (it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence)get_store().add_element_user(SEQUENCE$0);
            return target;
        }
    }
    /**
     * An XML sequence(@http://ilc.cnr.it/Cophi/Model/xmlmapping).
     *
     * This is a complex type.
     */
    public static class SequenceImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence
    {
        private static final long serialVersionUID = 1L;
        
        public SequenceImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName SEQUENCE$0 = 
            new javax.xml.namespace.QName("http://ilc.cnr.it/Cophi/Model/xmlmapping", "sequence");
        private static final javax.xml.namespace.QName ELEMENT$2 = 
            new javax.xml.namespace.QName("http://ilc.cnr.it/Cophi/Model/xmlmapping", "element");
        private static final javax.xml.namespace.QName PARAM$4 = 
            new javax.xml.namespace.QName("http://ilc.cnr.it/Cophi/Model/xmlmapping", "param");
        private static final javax.xml.namespace.QName ID$6 = 
            new javax.xml.namespace.QName("", "id");
        private static final javax.xml.namespace.QName CLASSNAME$8 = 
            new javax.xml.namespace.QName("", "classname");
        private static final javax.xml.namespace.QName EXTENDED$10 = 
            new javax.xml.namespace.QName("", "extended");
        private static final javax.xml.namespace.QName TYPE$12 = 
            new javax.xml.namespace.QName("", "type");
        
        
        /**
         * Gets array of all "sequence" elements
         */
        public it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence[] getSequenceArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(SEQUENCE$0, targetList);
                it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence[] result = new it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "sequence" element
         */
        public it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence getSequenceArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence target = null;
                target = (it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence)get_store().find_element_user(SEQUENCE$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "sequence" element
         */
        public int sizeOfSequenceArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(SEQUENCE$0);
            }
        }
        
        /**
         * Sets array of all "sequence" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setSequenceArray(it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence[] sequenceArray)
        {
            check_orphaned();
            arraySetterHelper(sequenceArray, SEQUENCE$0);
        }
        
        /**
         * Sets ith "sequence" element
         */
        public void setSequenceArray(int i, it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence sequence)
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence target = null;
                target = (it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence)get_store().find_element_user(SEQUENCE$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(sequence);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "sequence" element
         */
        public it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence insertNewSequence(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence target = null;
                target = (it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence)get_store().insert_element_user(SEQUENCE$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "sequence" element
         */
        public it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence addNewSequence()
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence target = null;
                target = (it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence)get_store().add_element_user(SEQUENCE$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "sequence" element
         */
        public void removeSequence(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(SEQUENCE$0, i);
            }
        }
        
        /**
         * Gets array of all "element" elements
         */
        public it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element[] getElementArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(ELEMENT$2, targetList);
                it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element[] result = new it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "element" element
         */
        public it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element getElementArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element target = null;
                target = (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element)get_store().find_element_user(ELEMENT$2, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "element" element
         */
        public int sizeOfElementArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(ELEMENT$2);
            }
        }
        
        /**
         * Sets array of all "element" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setElementArray(it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element[] elementArray)
        {
            check_orphaned();
            arraySetterHelper(elementArray, ELEMENT$2);
        }
        
        /**
         * Sets ith "element" element
         */
        public void setElementArray(int i, it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element element)
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element target = null;
                target = (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element)get_store().find_element_user(ELEMENT$2, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(element);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "element" element
         */
        public it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element insertNewElement(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element target = null;
                target = (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element)get_store().insert_element_user(ELEMENT$2, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "element" element
         */
        public it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element addNewElement()
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element target = null;
                target = (it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element)get_store().add_element_user(ELEMENT$2);
                return target;
            }
        }
        
        /**
         * Removes the ith "element" element
         */
        public void removeElement(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(ELEMENT$2, i);
            }
        }
        
        /**
         * Gets array of all "param" elements
         */
        public it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param[] getParamArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(PARAM$4, targetList);
                it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param[] result = new it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "param" element
         */
        public it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param getParamArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param target = null;
                target = (it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param)get_store().find_element_user(PARAM$4, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "param" element
         */
        public int sizeOfParamArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(PARAM$4);
            }
        }
        
        /**
         * Sets array of all "param" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setParamArray(it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param[] paramArray)
        {
            check_orphaned();
            arraySetterHelper(paramArray, PARAM$4);
        }
        
        /**
         * Sets ith "param" element
         */
        public void setParamArray(int i, it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param param)
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param target = null;
                target = (it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param)get_store().find_element_user(PARAM$4, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(param);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "param" element
         */
        public it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param insertNewParam(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param target = null;
                target = (it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param)get_store().insert_element_user(PARAM$4, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "param" element
         */
        public it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param addNewParam()
        {
            synchronized (monitor())
            {
                check_orphaned();
                it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param target = null;
                target = (it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param)get_store().add_element_user(PARAM$4);
                return target;
            }
        }
        
        /**
         * Removes the ith "param" element
         */
        public void removeParam(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(PARAM$4, i);
            }
        }
        
        /**
         * Gets the "id" attribute
         */
        public java.lang.String getId()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$6);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "id" attribute
         */
        public org.apache.xmlbeans.XmlNMTOKEN xgetId()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlNMTOKEN target = null;
                target = (org.apache.xmlbeans.XmlNMTOKEN)get_store().find_attribute_user(ID$6);
                return target;
            }
        }
        
        /**
         * Sets the "id" attribute
         */
        public void setId(java.lang.String id)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$6);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ID$6);
                }
                target.setStringValue(id);
            }
        }
        
        /**
         * Sets (as xml) the "id" attribute
         */
        public void xsetId(org.apache.xmlbeans.XmlNMTOKEN id)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlNMTOKEN target = null;
                target = (org.apache.xmlbeans.XmlNMTOKEN)get_store().find_attribute_user(ID$6);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlNMTOKEN)get_store().add_attribute_user(ID$6);
                }
                target.set(id);
            }
        }
        
        /**
         * Gets the "classname" attribute
         */
        public java.lang.String getClassname()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(CLASSNAME$8);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "classname" attribute
         */
        public org.apache.xmlbeans.XmlNMTOKEN xgetClassname()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlNMTOKEN target = null;
                target = (org.apache.xmlbeans.XmlNMTOKEN)get_store().find_attribute_user(CLASSNAME$8);
                return target;
            }
        }
        
        /**
         * Sets the "classname" attribute
         */
        public void setClassname(java.lang.String classname)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(CLASSNAME$8);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(CLASSNAME$8);
                }
                target.setStringValue(classname);
            }
        }
        
        /**
         * Sets (as xml) the "classname" attribute
         */
        public void xsetClassname(org.apache.xmlbeans.XmlNMTOKEN classname)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlNMTOKEN target = null;
                target = (org.apache.xmlbeans.XmlNMTOKEN)get_store().find_attribute_user(CLASSNAME$8);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlNMTOKEN)get_store().add_attribute_user(CLASSNAME$8);
                }
                target.set(classname);
            }
        }
        
        /**
         * Gets the "extended" attribute
         */
        public java.lang.String getExtended()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(EXTENDED$10);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "extended" attribute
         */
        public org.apache.xmlbeans.XmlNMTOKEN xgetExtended()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlNMTOKEN target = null;
                target = (org.apache.xmlbeans.XmlNMTOKEN)get_store().find_attribute_user(EXTENDED$10);
                return target;
            }
        }
        
        /**
         * True if has "extended" attribute
         */
        public boolean isSetExtended()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(EXTENDED$10) != null;
            }
        }
        
        /**
         * Sets the "extended" attribute
         */
        public void setExtended(java.lang.String extended)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(EXTENDED$10);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(EXTENDED$10);
                }
                target.setStringValue(extended);
            }
        }
        
        /**
         * Sets (as xml) the "extended" attribute
         */
        public void xsetExtended(org.apache.xmlbeans.XmlNMTOKEN extended)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlNMTOKEN target = null;
                target = (org.apache.xmlbeans.XmlNMTOKEN)get_store().find_attribute_user(EXTENDED$10);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlNMTOKEN)get_store().add_attribute_user(EXTENDED$10);
                }
                target.set(extended);
            }
        }
        
        /**
         * Unsets the "extended" attribute
         */
        public void unsetExtended()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(EXTENDED$10);
            }
        }
        
        /**
         * Gets the "type" attribute
         */
        public java.lang.String getType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TYPE$12);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "type" attribute
         */
        public org.apache.xmlbeans.XmlNMTOKEN xgetType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlNMTOKEN target = null;
                target = (org.apache.xmlbeans.XmlNMTOKEN)get_store().find_attribute_user(TYPE$12);
                return target;
            }
        }
        
        /**
         * True if has "type" attribute
         */
        public boolean isSetType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(TYPE$12) != null;
            }
        }
        
        /**
         * Sets the "type" attribute
         */
        public void setType(java.lang.String type)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TYPE$12);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(TYPE$12);
                }
                target.setStringValue(type);
            }
        }
        
        /**
         * Sets (as xml) the "type" attribute
         */
        public void xsetType(org.apache.xmlbeans.XmlNMTOKEN type)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlNMTOKEN target = null;
                target = (org.apache.xmlbeans.XmlNMTOKEN)get_store().find_attribute_user(TYPE$12);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlNMTOKEN)get_store().add_attribute_user(TYPE$12);
                }
                target.set(type);
            }
        }
        
        /**
         * Unsets the "type" attribute
         */
        public void unsetType()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(TYPE$12);
            }
        }
    }
}
