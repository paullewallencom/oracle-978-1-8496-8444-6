/*
 * An XML document type.
 * Localname: CreditCard
 * Namespace: http://www.somecorp.com/creditcard
 * Java type: com.somecorp.creditcard.CreditCardDocument
 *
 * Automatically generated - do not modify.
 */
package com.somecorp.creditcard.impl;
/**
 * A document containing one CreditCard(@http://www.somecorp.com/creditcard) element.
 *
 * This is a complex type.
 */
public class CreditCardDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.somecorp.creditcard.CreditCardDocument
{
    private static final long serialVersionUID = 1L;
    
    public CreditCardDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CREDITCARD$0 = 
        new javax.xml.namespace.QName("http://www.somecorp.com/creditcard", "CreditCard");
    
    
    /**
     * Gets the "CreditCard" element
     */
    public com.somecorp.creditcard.CreditCardTyp getCreditCard()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.somecorp.creditcard.CreditCardTyp target = null;
            target = (com.somecorp.creditcard.CreditCardTyp)get_store().find_element_user(CREDITCARD$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CreditCard" element
     */
    public void setCreditCard(com.somecorp.creditcard.CreditCardTyp creditCard)
    {
        generatedSetterHelperImpl(creditCard, CREDITCARD$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "CreditCard" element
     */
    public com.somecorp.creditcard.CreditCardTyp addNewCreditCard()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.somecorp.creditcard.CreditCardTyp target = null;
            target = (com.somecorp.creditcard.CreditCardTyp)get_store().add_element_user(CREDITCARD$0);
            return target;
        }
    }
}
