/*
 * XML Type:  CustomerTyp
 * Namespace: http://www.somecorp.com/customer
 * Java type: com.somecorp.customer.CustomerTyp
 *
 * Automatically generated - do not modify.
 */
package com.somecorp.customer.impl;
/**
 * An XML CustomerTyp(@http://www.somecorp.com/customer).
 *
 * This is a complex type.
 */
public class CustomerTypImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.somecorp.customer.CustomerTyp
{
    private static final long serialVersionUID = 1L;
    
    public CustomerTypImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ID$0 = 
        new javax.xml.namespace.QName("http://www.somecorp.com/customer", "ID");
    private static final javax.xml.namespace.QName FIRSTNAME$2 = 
        new javax.xml.namespace.QName("http://www.somecorp.com/customer", "FirstName");
    private static final javax.xml.namespace.QName LASTNAME$4 = 
        new javax.xml.namespace.QName("http://www.somecorp.com/customer", "LastName");
    private static final javax.xml.namespace.QName EMAILADDRESS$6 = 
        new javax.xml.namespace.QName("http://www.somecorp.com/customer", "EmailAddress");
    private static final javax.xml.namespace.QName ADDRESSES$8 = 
        new javax.xml.namespace.QName("http://www.somecorp.com/customer", "Addresses");
    private static final javax.xml.namespace.QName BIRTHDATE$10 = 
        new javax.xml.namespace.QName("http://www.somecorp.com/customer", "BirthDate");
    private static final javax.xml.namespace.QName RATING$12 = 
        new javax.xml.namespace.QName("http://www.somecorp.com/customer", "Rating");
    private static final javax.xml.namespace.QName GENDER$14 = 
        new javax.xml.namespace.QName("http://www.somecorp.com/customer", "Gender");
    private static final javax.xml.namespace.QName CREDITCARD$16 = 
        new javax.xml.namespace.QName("http://www.somecorp.com/customer", "CreditCard");
    
    
    /**
     * Gets the "ID" element
     */
    public long getID()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ID$0, 0);
            if (target == null)
            {
                return 0L;
            }
            return target.getLongValue();
        }
    }
    
    /**
     * Gets (as xml) the "ID" element
     */
    public org.apache.xmlbeans.XmlLong xgetID()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlLong target = null;
            target = (org.apache.xmlbeans.XmlLong)get_store().find_element_user(ID$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "ID" element
     */
    public void setID(long id)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ID$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ID$0);
            }
            target.setLongValue(id);
        }
    }
    
    /**
     * Sets (as xml) the "ID" element
     */
    public void xsetID(org.apache.xmlbeans.XmlLong id)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlLong target = null;
            target = (org.apache.xmlbeans.XmlLong)get_store().find_element_user(ID$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlLong)get_store().add_element_user(ID$0);
            }
            target.set(id);
        }
    }
    
    /**
     * Gets the "FirstName" element
     */
    public java.lang.String getFirstName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FIRSTNAME$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "FirstName" element
     */
    public org.apache.xmlbeans.XmlString xgetFirstName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FIRSTNAME$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "FirstName" element
     */
    public void setFirstName(java.lang.String firstName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FIRSTNAME$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(FIRSTNAME$2);
            }
            target.setStringValue(firstName);
        }
    }
    
    /**
     * Sets (as xml) the "FirstName" element
     */
    public void xsetFirstName(org.apache.xmlbeans.XmlString firstName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FIRSTNAME$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(FIRSTNAME$2);
            }
            target.set(firstName);
        }
    }
    
    /**
     * Gets the "LastName" element
     */
    public java.lang.String getLastName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(LASTNAME$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "LastName" element
     */
    public org.apache.xmlbeans.XmlString xgetLastName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(LASTNAME$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "LastName" element
     */
    public void setLastName(java.lang.String lastName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(LASTNAME$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(LASTNAME$4);
            }
            target.setStringValue(lastName);
        }
    }
    
    /**
     * Sets (as xml) the "LastName" element
     */
    public void xsetLastName(org.apache.xmlbeans.XmlString lastName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(LASTNAME$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(LASTNAME$4);
            }
            target.set(lastName);
        }
    }
    
    /**
     * Gets the "EmailAddress" element
     */
    public java.lang.String getEmailAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EMAILADDRESS$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "EmailAddress" element
     */
    public org.apache.xmlbeans.XmlString xgetEmailAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(EMAILADDRESS$6, 0);
            return target;
        }
    }
    
    /**
     * Sets the "EmailAddress" element
     */
    public void setEmailAddress(java.lang.String emailAddress)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EMAILADDRESS$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(EMAILADDRESS$6);
            }
            target.setStringValue(emailAddress);
        }
    }
    
    /**
     * Sets (as xml) the "EmailAddress" element
     */
    public void xsetEmailAddress(org.apache.xmlbeans.XmlString emailAddress)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(EMAILADDRESS$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(EMAILADDRESS$6);
            }
            target.set(emailAddress);
        }
    }
    
    /**
     * Gets the "Addresses" element
     */
    public com.somecorp.customer.AddressesTyp getAddresses()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.somecorp.customer.AddressesTyp target = null;
            target = (com.somecorp.customer.AddressesTyp)get_store().find_element_user(ADDRESSES$8, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Addresses" element
     */
    public void setAddresses(com.somecorp.customer.AddressesTyp addresses)
    {
        generatedSetterHelperImpl(addresses, ADDRESSES$8, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "Addresses" element
     */
    public com.somecorp.customer.AddressesTyp addNewAddresses()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.somecorp.customer.AddressesTyp target = null;
            target = (com.somecorp.customer.AddressesTyp)get_store().add_element_user(ADDRESSES$8);
            return target;
        }
    }
    
    /**
     * Gets the "BirthDate" element
     */
    public java.util.Calendar getBirthDate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BIRTHDATE$10, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "BirthDate" element
     */
    public org.apache.xmlbeans.XmlDate xgetBirthDate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDate target = null;
            target = (org.apache.xmlbeans.XmlDate)get_store().find_element_user(BIRTHDATE$10, 0);
            return target;
        }
    }
    
    /**
     * Sets the "BirthDate" element
     */
    public void setBirthDate(java.util.Calendar birthDate)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(BIRTHDATE$10, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(BIRTHDATE$10);
            }
            target.setCalendarValue(birthDate);
        }
    }
    
    /**
     * Sets (as xml) the "BirthDate" element
     */
    public void xsetBirthDate(org.apache.xmlbeans.XmlDate birthDate)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDate target = null;
            target = (org.apache.xmlbeans.XmlDate)get_store().find_element_user(BIRTHDATE$10, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDate)get_store().add_element_user(BIRTHDATE$10);
            }
            target.set(birthDate);
        }
    }
    
    /**
     * Gets the "Rating" element
     */
    public com.somecorp.customer.RatingTyp.Enum getRating()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RATING$12, 0);
            if (target == null)
            {
                return null;
            }
            return (com.somecorp.customer.RatingTyp.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "Rating" element
     */
    public com.somecorp.customer.RatingTyp xgetRating()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.somecorp.customer.RatingTyp target = null;
            target = (com.somecorp.customer.RatingTyp)get_store().find_element_user(RATING$12, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Rating" element
     */
    public void setRating(com.somecorp.customer.RatingTyp.Enum rating)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RATING$12, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(RATING$12);
            }
            target.setEnumValue(rating);
        }
    }
    
    /**
     * Sets (as xml) the "Rating" element
     */
    public void xsetRating(com.somecorp.customer.RatingTyp rating)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.somecorp.customer.RatingTyp target = null;
            target = (com.somecorp.customer.RatingTyp)get_store().find_element_user(RATING$12, 0);
            if (target == null)
            {
                target = (com.somecorp.customer.RatingTyp)get_store().add_element_user(RATING$12);
            }
            target.set(rating);
        }
    }
    
    /**
     * Gets the "Gender" element
     */
    public java.lang.String getGender()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(GENDER$14, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Gender" element
     */
    public org.apache.xmlbeans.XmlString xgetGender()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(GENDER$14, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Gender" element
     */
    public void setGender(java.lang.String gender)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(GENDER$14, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(GENDER$14);
            }
            target.setStringValue(gender);
        }
    }
    
    /**
     * Sets (as xml) the "Gender" element
     */
    public void xsetGender(org.apache.xmlbeans.XmlString gender)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(GENDER$14, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(GENDER$14);
            }
            target.set(gender);
        }
    }
    
    /**
     * Gets the "CreditCard" element
     */
    public com.somecorp.creditcard.CreditCardTyp getCreditCard()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.somecorp.creditcard.CreditCardTyp target = null;
            target = (com.somecorp.creditcard.CreditCardTyp)get_store().find_element_user(CREDITCARD$16, 0);
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
        generatedSetterHelperImpl(creditCard, CREDITCARD$16, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
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
            target = (com.somecorp.creditcard.CreditCardTyp)get_store().add_element_user(CREDITCARD$16);
            return target;
        }
    }
}
