/*
 * An XML document type.
 * Localname: Customer
 * Namespace: http://www.somecorp.com/customer
 * Java type: com.somecorp.customer.CustomerDocument
 *
 * Automatically generated - do not modify.
 */
package com.somecorp.customer.impl;
/**
 * A document containing one Customer(@http://www.somecorp.com/customer) element.
 *
 * This is a complex type.
 */
public class CustomerDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.somecorp.customer.CustomerDocument
{
    private static final long serialVersionUID = 1L;
    
    public CustomerDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CUSTOMER$0 = 
        new javax.xml.namespace.QName("http://www.somecorp.com/customer", "Customer");
    
    
    /**
     * Gets the "Customer" element
     */
    public com.somecorp.customer.CustomerTyp getCustomer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.somecorp.customer.CustomerTyp target = null;
            target = (com.somecorp.customer.CustomerTyp)get_store().find_element_user(CUSTOMER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Customer" element
     */
    public void setCustomer(com.somecorp.customer.CustomerTyp customer)
    {
        generatedSetterHelperImpl(customer, CUSTOMER$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "Customer" element
     */
    public com.somecorp.customer.CustomerTyp addNewCustomer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.somecorp.customer.CustomerTyp target = null;
            target = (com.somecorp.customer.CustomerTyp)get_store().add_element_user(CUSTOMER$0);
            return target;
        }
    }
}
