/*
 * XML Type:  AddressesTyp
 * Namespace: http://www.somecorp.com/customer
 * Java type: com.somecorp.customer.AddressesTyp
 *
 * Automatically generated - do not modify.
 */
package com.somecorp.customer.impl;
/**
 * An XML AddressesTyp(@http://www.somecorp.com/customer).
 *
 * This is a complex type.
 */
public class AddressesTypImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements com.somecorp.customer.AddressesTyp
{
    private static final long serialVersionUID = 1L;
    
    public AddressesTypImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ADDRESS$0 = 
        new javax.xml.namespace.QName("http://www.somecorp.com/customer", "Address");
    
    
    /**
     * Gets a List of "Address" elements
     */
    public java.util.List<com.somecorp.customer.AddressTyp> getAddressList()
    {
        final class AddressList extends java.util.AbstractList<com.somecorp.customer.AddressTyp>
        {
            @Override
            public com.somecorp.customer.AddressTyp get(int i)
                { return AddressesTypImpl.this.getAddressArray(i); }
            
            @Override
            public com.somecorp.customer.AddressTyp set(int i, com.somecorp.customer.AddressTyp o)
            {
                com.somecorp.customer.AddressTyp old = AddressesTypImpl.this.getAddressArray(i);
                AddressesTypImpl.this.setAddressArray(i, o);
                return old;
            }
            
            @Override
            public void add(int i, com.somecorp.customer.AddressTyp o)
                { AddressesTypImpl.this.insertNewAddress(i).set(o); }
            
            @Override
            public com.somecorp.customer.AddressTyp remove(int i)
            {
                com.somecorp.customer.AddressTyp old = AddressesTypImpl.this.getAddressArray(i);
                AddressesTypImpl.this.removeAddress(i);
                return old;
            }
            
            @Override
            public int size()
                { return AddressesTypImpl.this.sizeOfAddressArray(); }
            
        }
        
        synchronized (monitor())
        {
            check_orphaned();
            return new AddressList();
        }
    }
    
    /**
     * Gets array of all "Address" elements
     * @deprecated
     */
    @Deprecated
    public com.somecorp.customer.AddressTyp[] getAddressArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List<com.somecorp.customer.AddressTyp> targetList = new java.util.ArrayList<com.somecorp.customer.AddressTyp>();
            get_store().find_all_element_users(ADDRESS$0, targetList);
            com.somecorp.customer.AddressTyp[] result = new com.somecorp.customer.AddressTyp[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Address" element
     */
    public com.somecorp.customer.AddressTyp getAddressArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.somecorp.customer.AddressTyp target = null;
            target = (com.somecorp.customer.AddressTyp)get_store().find_element_user(ADDRESS$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Address" element
     */
    public int sizeOfAddressArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ADDRESS$0);
        }
    }
    
    /**
     * Sets array of all "Address" element  WARNING: This method is not atomicaly synchronized.
     */
    public void setAddressArray(com.somecorp.customer.AddressTyp[] addressArray)
    {
        check_orphaned();
        arraySetterHelper(addressArray, ADDRESS$0);
    }
    
    /**
     * Sets ith "Address" element
     */
    public void setAddressArray(int i, com.somecorp.customer.AddressTyp address)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.somecorp.customer.AddressTyp target = null;
            target = (com.somecorp.customer.AddressTyp)get_store().find_element_user(ADDRESS$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(address);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Address" element
     */
    public com.somecorp.customer.AddressTyp insertNewAddress(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.somecorp.customer.AddressTyp target = null;
            target = (com.somecorp.customer.AddressTyp)get_store().insert_element_user(ADDRESS$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Address" element
     */
    public com.somecorp.customer.AddressTyp addNewAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            com.somecorp.customer.AddressTyp target = null;
            target = (com.somecorp.customer.AddressTyp)get_store().add_element_user(ADDRESS$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Address" element
     */
    public void removeAddress(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ADDRESS$0, i);
        }
    }
}
