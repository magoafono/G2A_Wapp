/*
 * An XML document type.
 * Localname: w
 * Namespace: http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/
 * Java type: it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument
 *
 * Automatically generated - do not modify.
 */
package it.cnr.ilc.cophi.model.importxml.xmlmapping.impl;
/**
 * A document containing one w(@http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/) element.
 *
 * This is a complex type.
 */
public class WDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument
{
    private static final long serialVersionUID = 1L;
    
    public WDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName W$0 = 
        new javax.xml.namespace.QName("http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/", "w");
    
    
    /**
     * Gets the "w" element
     */
    public it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W getW()
    {
        synchronized (monitor())
        {
            check_orphaned();
            it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W target = null;
            target = (it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W)get_store().find_element_user(W$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "w" element
     */
    public void setW(it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W w)
    {
        generatedSetterHelperImpl(w, W$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "w" element
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
     * An XML w(@http://ilc.cnr.it/Cophi/Model/importxml/xmlmapping/).
     *
     * This is a complex type.
     */
    public static class WImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements it.cnr.ilc.cophi.model.importxml.xmlmapping.WDocument.W
    {
        private static final long serialVersionUID = 1L;
        
        public WImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName BIBREF$0 = 
            new javax.xml.namespace.QName("", "bibref");
        private static final javax.xml.namespace.QName END$2 = 
            new javax.xml.namespace.QName("", "end");
        private static final javax.xml.namespace.QName FORM$4 = 
            new javax.xml.namespace.QName("", "form");
        private static final javax.xml.namespace.QName ID$6 = 
            new javax.xml.namespace.QName("", "id");
        private static final javax.xml.namespace.QName LEMMA$8 = 
            new javax.xml.namespace.QName("", "lemma");
        private static final javax.xml.namespace.QName POS$10 = 
            new javax.xml.namespace.QName("", "pos");
        private static final javax.xml.namespace.QName PROG$12 = 
            new javax.xml.namespace.QName("", "prog");
        private static final javax.xml.namespace.QName ROOT$14 = 
            new javax.xml.namespace.QName("", "root");
        private static final javax.xml.namespace.QName START$16 = 
            new javax.xml.namespace.QName("", "start");
        private static final javax.xml.namespace.QName TOKEN$18 = 
            new javax.xml.namespace.QName("", "token");
        private static final javax.xml.namespace.QName UPPERCASEFORM$20 = 
            new javax.xml.namespace.QName("", "uppercaseform");
        private static final javax.xml.namespace.QName VOC$22 = 
            new javax.xml.namespace.QName("", "voc");
        
        
        /**
         * Gets the "bibref" attribute
         */
        public java.lang.String getBibref()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(BIBREF$0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "bibref" attribute
         */
        public org.apache.xmlbeans.XmlNMTOKEN xgetBibref()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlNMTOKEN target = null;
                target = (org.apache.xmlbeans.XmlNMTOKEN)get_store().find_attribute_user(BIBREF$0);
                return target;
            }
        }
        
        /**
         * True if has "bibref" attribute
         */
        public boolean isSetBibref()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(BIBREF$0) != null;
            }
        }
        
        /**
         * Sets the "bibref" attribute
         */
        public void setBibref(java.lang.String bibref)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(BIBREF$0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(BIBREF$0);
                }
                target.setStringValue(bibref);
            }
        }
        
        /**
         * Sets (as xml) the "bibref" attribute
         */
        public void xsetBibref(org.apache.xmlbeans.XmlNMTOKEN bibref)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlNMTOKEN target = null;
                target = (org.apache.xmlbeans.XmlNMTOKEN)get_store().find_attribute_user(BIBREF$0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlNMTOKEN)get_store().add_attribute_user(BIBREF$0);
                }
                target.set(bibref);
            }
        }
        
        /**
         * Unsets the "bibref" attribute
         */
        public void unsetBibref()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(BIBREF$0);
            }
        }
        
        /**
         * Gets the "end" attribute
         */
        public java.math.BigInteger getEnd()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(END$2);
                if (target == null)
                {
                    return null;
                }
                return target.getBigIntegerValue();
            }
        }
        
        /**
         * Gets (as xml) the "end" attribute
         */
        public org.apache.xmlbeans.XmlInteger xgetEnd()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlInteger target = null;
                target = (org.apache.xmlbeans.XmlInteger)get_store().find_attribute_user(END$2);
                return target;
            }
        }
        
        /**
         * Sets the "end" attribute
         */
        public void setEnd(java.math.BigInteger end)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(END$2);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(END$2);
                }
                target.setBigIntegerValue(end);
            }
        }
        
        /**
         * Sets (as xml) the "end" attribute
         */
        public void xsetEnd(org.apache.xmlbeans.XmlInteger end)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlInteger target = null;
                target = (org.apache.xmlbeans.XmlInteger)get_store().find_attribute_user(END$2);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlInteger)get_store().add_attribute_user(END$2);
                }
                target.set(end);
            }
        }
        
        /**
         * Gets the "form" attribute
         */
        public org.apache.xmlbeans.XmlAnySimpleType getForm()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlAnySimpleType target = null;
                target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().find_attribute_user(FORM$4);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "form" attribute
         */
        public void setForm(org.apache.xmlbeans.XmlAnySimpleType form)
        {
            generatedSetterHelperImpl(form, FORM$4, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "form" attribute
         */
        public org.apache.xmlbeans.XmlAnySimpleType addNewForm()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlAnySimpleType target = null;
                target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().add_attribute_user(FORM$4);
                return target;
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
         * Gets the "lemma" attribute
         */
        public org.apache.xmlbeans.XmlAnySimpleType getLemma()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlAnySimpleType target = null;
                target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().find_attribute_user(LEMMA$8);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "lemma" attribute
         */
        public void setLemma(org.apache.xmlbeans.XmlAnySimpleType lemma)
        {
            generatedSetterHelperImpl(lemma, LEMMA$8, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "lemma" attribute
         */
        public org.apache.xmlbeans.XmlAnySimpleType addNewLemma()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlAnySimpleType target = null;
                target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().add_attribute_user(LEMMA$8);
                return target;
            }
        }
        
        /**
         * Gets the "pos" attribute
         */
        public org.apache.xmlbeans.XmlAnySimpleType getPos()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlAnySimpleType target = null;
                target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().find_attribute_user(POS$10);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "pos" attribute
         */
        public void setPos(org.apache.xmlbeans.XmlAnySimpleType pos)
        {
            generatedSetterHelperImpl(pos, POS$10, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "pos" attribute
         */
        public org.apache.xmlbeans.XmlAnySimpleType addNewPos()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlAnySimpleType target = null;
                target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().add_attribute_user(POS$10);
                return target;
            }
        }
        
        /**
         * Gets the "prog" attribute
         */
        public java.math.BigInteger getProg()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(PROG$12);
                if (target == null)
                {
                    return null;
                }
                return target.getBigIntegerValue();
            }
        }
        
        /**
         * Gets (as xml) the "prog" attribute
         */
        public org.apache.xmlbeans.XmlInteger xgetProg()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlInteger target = null;
                target = (org.apache.xmlbeans.XmlInteger)get_store().find_attribute_user(PROG$12);
                return target;
            }
        }
        
        /**
         * Sets the "prog" attribute
         */
        public void setProg(java.math.BigInteger prog)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(PROG$12);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(PROG$12);
                }
                target.setBigIntegerValue(prog);
            }
        }
        
        /**
         * Sets (as xml) the "prog" attribute
         */
        public void xsetProg(org.apache.xmlbeans.XmlInteger prog)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlInteger target = null;
                target = (org.apache.xmlbeans.XmlInteger)get_store().find_attribute_user(PROG$12);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlInteger)get_store().add_attribute_user(PROG$12);
                }
                target.set(prog);
            }
        }
        
        /**
         * Gets the "root" attribute
         */
        public org.apache.xmlbeans.XmlAnySimpleType getRoot()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlAnySimpleType target = null;
                target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().find_attribute_user(ROOT$14);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "root" attribute
         */
        public boolean isSetRoot()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(ROOT$14) != null;
            }
        }
        
        /**
         * Sets the "root" attribute
         */
        public void setRoot(org.apache.xmlbeans.XmlAnySimpleType root)
        {
            generatedSetterHelperImpl(root, ROOT$14, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "root" attribute
         */
        public org.apache.xmlbeans.XmlAnySimpleType addNewRoot()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlAnySimpleType target = null;
                target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().add_attribute_user(ROOT$14);
                return target;
            }
        }
        
        /**
         * Unsets the "root" attribute
         */
        public void unsetRoot()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(ROOT$14);
            }
        }
        
        /**
         * Gets the "start" attribute
         */
        public java.math.BigInteger getStart()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(START$16);
                if (target == null)
                {
                    return null;
                }
                return target.getBigIntegerValue();
            }
        }
        
        /**
         * Gets (as xml) the "start" attribute
         */
        public org.apache.xmlbeans.XmlInteger xgetStart()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlInteger target = null;
                target = (org.apache.xmlbeans.XmlInteger)get_store().find_attribute_user(START$16);
                return target;
            }
        }
        
        /**
         * Sets the "start" attribute
         */
        public void setStart(java.math.BigInteger start)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(START$16);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(START$16);
                }
                target.setBigIntegerValue(start);
            }
        }
        
        /**
         * Sets (as xml) the "start" attribute
         */
        public void xsetStart(org.apache.xmlbeans.XmlInteger start)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlInteger target = null;
                target = (org.apache.xmlbeans.XmlInteger)get_store().find_attribute_user(START$16);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlInteger)get_store().add_attribute_user(START$16);
                }
                target.set(start);
            }
        }
        
        /**
         * Gets the "token" attribute
         */
        public org.apache.xmlbeans.XmlAnySimpleType getToken()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlAnySimpleType target = null;
                target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().find_attribute_user(TOKEN$18);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "token" attribute
         */
        public void setToken(org.apache.xmlbeans.XmlAnySimpleType token)
        {
            generatedSetterHelperImpl(token, TOKEN$18, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "token" attribute
         */
        public org.apache.xmlbeans.XmlAnySimpleType addNewToken()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlAnySimpleType target = null;
                target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().add_attribute_user(TOKEN$18);
                return target;
            }
        }
        
        /**
         * Gets the "uppercaseform" attribute
         */
        public java.lang.String getUppercaseform()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(UPPERCASEFORM$20);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "uppercaseform" attribute
         */
        public org.apache.xmlbeans.XmlNCName xgetUppercaseform()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlNCName target = null;
                target = (org.apache.xmlbeans.XmlNCName)get_store().find_attribute_user(UPPERCASEFORM$20);
                return target;
            }
        }
        
        /**
         * True if has "uppercaseform" attribute
         */
        public boolean isSetUppercaseform()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(UPPERCASEFORM$20) != null;
            }
        }
        
        /**
         * Sets the "uppercaseform" attribute
         */
        public void setUppercaseform(java.lang.String uppercaseform)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(UPPERCASEFORM$20);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(UPPERCASEFORM$20);
                }
                target.setStringValue(uppercaseform);
            }
        }
        
        /**
         * Sets (as xml) the "uppercaseform" attribute
         */
        public void xsetUppercaseform(org.apache.xmlbeans.XmlNCName uppercaseform)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlNCName target = null;
                target = (org.apache.xmlbeans.XmlNCName)get_store().find_attribute_user(UPPERCASEFORM$20);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlNCName)get_store().add_attribute_user(UPPERCASEFORM$20);
                }
                target.set(uppercaseform);
            }
        }
        
        /**
         * Unsets the "uppercaseform" attribute
         */
        public void unsetUppercaseform()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(UPPERCASEFORM$20);
            }
        }
        
        /**
         * Gets the "voc" attribute
         */
        public org.apache.xmlbeans.XmlAnySimpleType getVoc()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlAnySimpleType target = null;
                target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().find_attribute_user(VOC$22);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * True if has "voc" attribute
         */
        public boolean isSetVoc()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().find_attribute_user(VOC$22) != null;
            }
        }
        
        /**
         * Sets the "voc" attribute
         */
        public void setVoc(org.apache.xmlbeans.XmlAnySimpleType voc)
        {
            generatedSetterHelperImpl(voc, VOC$22, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
        }
        
        /**
         * Appends and returns a new empty "voc" attribute
         */
        public org.apache.xmlbeans.XmlAnySimpleType addNewVoc()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlAnySimpleType target = null;
                target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().add_attribute_user(VOC$22);
                return target;
            }
        }
        
        /**
         * Unsets the "voc" attribute
         */
        public void unsetVoc()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_attribute(VOC$22);
            }
        }
    }
}
