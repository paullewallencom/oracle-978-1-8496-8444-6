/*
 * XML Type:  CustomerTyp
 * Namespace: http://www.somecorp.com/customer
 * Java type: com.somecorp.customer.CustomerTyp
 *
 * Automatically generated - do not modify.
 */
package com.somecorp.customer;


/**
 * An XML CustomerTyp(@http://www.somecorp.com/customer).
 *
 * This is a complex type.
 */
public interface CustomerTyp extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CustomerTyp.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s178E60C7478763B9F9AEBA2650BA3F15").resolveHandle("customertypb82ftype");
    
    /**
     * Gets the "ID" element
     */
    long getID();
    
    /**
     * Gets (as xml) the "ID" element
     */
    org.apache.xmlbeans.XmlLong xgetID();
    
    /**
     * Sets the "ID" element
     */
    void setID(long id);
    
    /**
     * Sets (as xml) the "ID" element
     */
    void xsetID(org.apache.xmlbeans.XmlLong id);
    
    /**
     * Gets the "FirstName" element
     */
    java.lang.String getFirstName();
    
    /**
     * Gets (as xml) the "FirstName" element
     */
    org.apache.xmlbeans.XmlString xgetFirstName();
    
    /**
     * Sets the "FirstName" element
     */
    void setFirstName(java.lang.String firstName);
    
    /**
     * Sets (as xml) the "FirstName" element
     */
    void xsetFirstName(org.apache.xmlbeans.XmlString firstName);
    
    /**
     * Gets the "LastName" element
     */
    java.lang.String getLastName();
    
    /**
     * Gets (as xml) the "LastName" element
     */
    org.apache.xmlbeans.XmlString xgetLastName();
    
    /**
     * Sets the "LastName" element
     */
    void setLastName(java.lang.String lastName);
    
    /**
     * Sets (as xml) the "LastName" element
     */
    void xsetLastName(org.apache.xmlbeans.XmlString lastName);
    
    /**
     * Gets the "EmailAddress" element
     */
    java.lang.String getEmailAddress();
    
    /**
     * Gets (as xml) the "EmailAddress" element
     */
    org.apache.xmlbeans.XmlString xgetEmailAddress();
    
    /**
     * Sets the "EmailAddress" element
     */
    void setEmailAddress(java.lang.String emailAddress);
    
    /**
     * Sets (as xml) the "EmailAddress" element
     */
    void xsetEmailAddress(org.apache.xmlbeans.XmlString emailAddress);
    
    /**
     * Gets the "Addresses" element
     */
    com.somecorp.customer.AddressesTyp getAddresses();
    
    /**
     * Sets the "Addresses" element
     */
    void setAddresses(com.somecorp.customer.AddressesTyp addresses);
    
    /**
     * Appends and returns a new empty "Addresses" element
     */
    com.somecorp.customer.AddressesTyp addNewAddresses();
    
    /**
     * Gets the "BirthDate" element
     */
    java.util.Calendar getBirthDate();
    
    /**
     * Gets (as xml) the "BirthDate" element
     */
    org.apache.xmlbeans.XmlDate xgetBirthDate();
    
    /**
     * Sets the "BirthDate" element
     */
    void setBirthDate(java.util.Calendar birthDate);
    
    /**
     * Sets (as xml) the "BirthDate" element
     */
    void xsetBirthDate(org.apache.xmlbeans.XmlDate birthDate);
    
    /**
     * Gets the "Rating" element
     */
    com.somecorp.customer.RatingTyp.Enum getRating();
    
    /**
     * Gets (as xml) the "Rating" element
     */
    com.somecorp.customer.RatingTyp xgetRating();
    
    /**
     * Sets the "Rating" element
     */
    void setRating(com.somecorp.customer.RatingTyp.Enum rating);
    
    /**
     * Sets (as xml) the "Rating" element
     */
    void xsetRating(com.somecorp.customer.RatingTyp rating);
    
    /**
     * Gets the "Gender" element
     */
    java.lang.String getGender();
    
    /**
     * Gets (as xml) the "Gender" element
     */
    org.apache.xmlbeans.XmlString xgetGender();
    
    /**
     * Sets the "Gender" element
     */
    void setGender(java.lang.String gender);
    
    /**
     * Sets (as xml) the "Gender" element
     */
    void xsetGender(org.apache.xmlbeans.XmlString gender);
    
    /**
     * Gets the "CreditCard" element
     */
    com.somecorp.creditcard.CreditCardTyp getCreditCard();
    
    /**
     * Sets the "CreditCard" element
     */
    void setCreditCard(com.somecorp.creditcard.CreditCardTyp creditCard);
    
    /**
     * Appends and returns a new empty "CreditCard" element
     */
    com.somecorp.creditcard.CreditCardTyp addNewCreditCard();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static com.somecorp.customer.CustomerTyp newInstance() {
          return (com.somecorp.customer.CustomerTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.somecorp.customer.CustomerTyp newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.somecorp.customer.CustomerTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.somecorp.customer.CustomerTyp parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.customer.CustomerTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.somecorp.customer.CustomerTyp parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.customer.CustomerTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.somecorp.customer.CustomerTyp parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.CustomerTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.somecorp.customer.CustomerTyp parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.CustomerTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.somecorp.customer.CustomerTyp parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.CustomerTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.somecorp.customer.CustomerTyp parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.CustomerTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.somecorp.customer.CustomerTyp parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.CustomerTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.somecorp.customer.CustomerTyp parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.CustomerTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.somecorp.customer.CustomerTyp parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.CustomerTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.somecorp.customer.CustomerTyp parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.CustomerTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.somecorp.customer.CustomerTyp parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.customer.CustomerTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.somecorp.customer.CustomerTyp parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.customer.CustomerTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.somecorp.customer.CustomerTyp parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.customer.CustomerTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.somecorp.customer.CustomerTyp parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.customer.CustomerTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        @Deprecated
        public static com.somecorp.customer.CustomerTyp parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.somecorp.customer.CustomerTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        @Deprecated
        public static com.somecorp.customer.CustomerTyp parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.somecorp.customer.CustomerTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        @Deprecated
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        @Deprecated
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
