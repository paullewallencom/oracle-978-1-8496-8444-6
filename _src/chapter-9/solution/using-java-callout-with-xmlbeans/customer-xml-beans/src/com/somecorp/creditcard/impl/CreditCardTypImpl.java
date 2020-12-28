/*
 * XML Type:  CreditCardTyp
 * Namespace: http://www.somecorp.com/creditcard
 * Java type: com.somecorp.creditcard.CreditCardTyp
 *
 * Automatically generated - do not modify.
 */
package com.somecorp.creditcard.impl;
/**
 * An XML CreditCardTyp(@http://www.somecorp.com/creditcard).
 *
 * This is a complex type.
 */
public class CreditCardTypImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.somecorp.creditcard.CreditCardTyp
{
    private static final long serialVersionUID = 1L;
    
    public CreditCardTypImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CARDISSUER$0 = 
        new javax.xml.namespace.QName("http://www.somecorp.com/creditcard", "CardIssuer");
    private static final javax.xml.namespace.QName CARDNUMBER$2 = 
        new javax.xml.namespace.QName("http://www.somecorp.com/creditcard", "CardNumber");
    private static final javax.xml.namespace.QName CARDHOLDERNAME$4 = 
        new javax.xml.namespace.QName("http://www.somecorp.com/creditcard", "CardholderName");
    private static final javax.xml.namespace.QName EXPIRATIONDATE$6 = 
        new javax.xml.namespace.QName("http://www.somecorp.com/creditcard", "ExpirationDate");
    private static final javax.xml.namespace.QName CARDVALIDATIONCODE$8 = 
        new javax.xml.namespace.QName("http://www.somecorp.com/creditcard", "CardValidationCode");
    
    
    /**
     * Gets the "CardIssuer" element
     */
    public com.somecorp.creditcard.CardIssuerTyp.Enum getCardIssuer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CARDISSUER$0, 0);
            if (target == null)
            {
                return null;
            }
            return (com.somecorp.creditcard.CardIssuerTyp.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "CardIssuer" element
     */
    public com.somecorp.creditcard.CardIssuerTyp xgetCardIssuer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.somecorp.creditcard.CardIssuerTyp target = null;
            target = (com.somecorp.creditcard.CardIssuerTyp)get_store().find_element_user(CARDISSUER$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "CardIssuer" element
     */
    public void setCardIssuer(com.somecorp.creditcard.CardIssuerTyp.Enum cardIssuer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CARDISSUER$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CARDISSUER$0);
            }
            target.setEnumValue(cardIssuer);
        }
    }
    
    /**
     * Sets (as xml) the "CardIssuer" element
     */
    public void xsetCardIssuer(com.somecorp.creditcard.CardIssuerTyp cardIssuer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.somecorp.creditcard.CardIssuerTyp target = null;
            target = (com.somecorp.creditcard.CardIssuerTyp)get_store().find_element_user(CARDISSUER$0, 0);
            if (target == null)
            {
                target = (com.somecorp.creditcard.CardIssuerTyp)get_store().add_element_user(CARDISSUER$0);
            }
            target.set(cardIssuer);
        }
    }
    
    /**
     * Gets the "CardNumber" element
     */
    public java.lang.String getCardNumber()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CARDNUMBER$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "CardNumber" element
     */
    public org.apache.xmlbeans.XmlString xgetCardNumber()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CARDNUMBER$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "CardNumber" element
     */
    public void setCardNumber(java.lang.String cardNumber)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CARDNUMBER$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CARDNUMBER$2);
            }
            target.setStringValue(cardNumber);
        }
    }
    
    /**
     * Sets (as xml) the "CardNumber" element
     */
    public void xsetCardNumber(org.apache.xmlbeans.XmlString cardNumber)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CARDNUMBER$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CARDNUMBER$2);
            }
            target.set(cardNumber);
        }
    }
    
    /**
     * Gets the "CardholderName" element
     */
    public java.lang.String getCardholderName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CARDHOLDERNAME$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "CardholderName" element
     */
    public org.apache.xmlbeans.XmlString xgetCardholderName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CARDHOLDERNAME$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "CardholderName" element
     */
    public void setCardholderName(java.lang.String cardholderName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CARDHOLDERNAME$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CARDHOLDERNAME$4);
            }
            target.setStringValue(cardholderName);
        }
    }
    
    /**
     * Sets (as xml) the "CardholderName" element
     */
    public void xsetCardholderName(org.apache.xmlbeans.XmlString cardholderName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CARDHOLDERNAME$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CARDHOLDERNAME$4);
            }
            target.set(cardholderName);
        }
    }
    
    /**
     * Gets the "ExpirationDate" element
     */
    public java.lang.String getExpirationDate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EXPIRATIONDATE$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "ExpirationDate" element
     */
    public org.apache.xmlbeans.XmlString xgetExpirationDate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(EXPIRATIONDATE$6, 0);
            return target;
        }
    }
    
    /**
     * Sets the "ExpirationDate" element
     */
    public void setExpirationDate(java.lang.String expirationDate)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EXPIRATIONDATE$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(EXPIRATIONDATE$6);
            }
            target.setStringValue(expirationDate);
        }
    }
    
    /**
     * Sets (as xml) the "ExpirationDate" element
     */
    public void xsetExpirationDate(org.apache.xmlbeans.XmlString expirationDate)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(EXPIRATIONDATE$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(EXPIRATIONDATE$6);
            }
            target.set(expirationDate);
        }
    }
    
    /**
     * Gets the "CardValidationCode" element
     */
    public long getCardValidationCode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CARDVALIDATIONCODE$8, 0);
            if (target == null)
            {
                return 0L;
            }
            return target.getLongValue();
        }
    }
    
    /**
     * Gets (as xml) the "CardValidationCode" element
     */
    public org.apache.xmlbeans.XmlLong xgetCardValidationCode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlLong target = null;
            target = (org.apache.xmlbeans.XmlLong)get_store().find_element_user(CARDVALIDATIONCODE$8, 0);
            return target;
        }
    }
    
    /**
     * Sets the "CardValidationCode" element
     */
    public void setCardValidationCode(long cardValidationCode)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CARDVALIDATIONCODE$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CARDVALIDATIONCODE$8);
            }
            target.setLongValue(cardValidationCode);
        }
    }
    
    /**
     * Sets (as xml) the "CardValidationCode" element
     */
    public void xsetCardValidationCode(org.apache.xmlbeans.XmlLong cardValidationCode)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlLong target = null;
            target = (org.apache.xmlbeans.XmlLong)get_store().find_element_user(CARDVALIDATIONCODE$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlLong)get_store().add_element_user(CARDVALIDATIONCODE$8);
            }
            target.set(cardValidationCode);
        }
    }
}
