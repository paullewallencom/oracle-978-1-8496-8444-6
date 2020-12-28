/*
 * XML Type:  AddressesTyp
 * Namespace: http://www.somecorp.com/customer
 * Java type: com.somecorp.customer.AddressesTyp
 *
 * Automatically generated - do not modify.
 */
package com.somecorp.customer;


/**
 * An XML AddressesTyp(@http://www.somecorp.com/customer).
 *
 * This is a complex type.
 */
public interface AddressesTyp extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(AddressesTyp.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s178E60C7478763B9F9AEBA2650BA3F15").resolveHandle("addressestyp7901type");
    
    /**
     * Gets a List of "Address" elements
     */
    java.util.List<com.somecorp.customer.AddressTyp> getAddressList();
    
    /**
     * Gets array of all "Address" elements
     * @deprecated
     */
    @Deprecated
    com.somecorp.customer.AddressTyp[] getAddressArray();
    
    /**
     * Gets ith "Address" element
     */
    com.somecorp.customer.AddressTyp getAddressArray(int i);
    
    /**
     * Returns number of "Address" element
     */
    int sizeOfAddressArray();
    
    /**
     * Sets array of all "Address" element
     */
    void setAddressArray(com.somecorp.customer.AddressTyp[] addressArray);
    
    /**
     * Sets ith "Address" element
     */
    void setAddressArray(int i, com.somecorp.customer.AddressTyp address);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Address" element
     */
    com.somecorp.customer.AddressTyp insertNewAddress(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Address" element
     */
    com.somecorp.customer.AddressTyp addNewAddress();
    
    /**
     * Removes the ith "Address" element
     */
    void removeAddress(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static com.somecorp.customer.AddressesTyp newInstance() {
          return (com.somecorp.customer.AddressesTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.somecorp.customer.AddressesTyp newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.somecorp.customer.AddressesTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.somecorp.customer.AddressesTyp parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.customer.AddressesTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.somecorp.customer.AddressesTyp parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.customer.AddressesTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.somecorp.customer.AddressesTyp parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.AddressesTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.somecorp.customer.AddressesTyp parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.AddressesTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.somecorp.customer.AddressesTyp parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.AddressesTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.somecorp.customer.AddressesTyp parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.AddressesTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.somecorp.customer.AddressesTyp parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.AddressesTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.somecorp.customer.AddressesTyp parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.AddressesTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.somecorp.customer.AddressesTyp parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.AddressesTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.somecorp.customer.AddressesTyp parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.AddressesTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.somecorp.customer.AddressesTyp parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.customer.AddressesTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.somecorp.customer.AddressesTyp parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.customer.AddressesTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.somecorp.customer.AddressesTyp parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.customer.AddressesTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.somecorp.customer.AddressesTyp parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.customer.AddressesTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        @Deprecated
        public static com.somecorp.customer.AddressesTyp parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.somecorp.customer.AddressesTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        @Deprecated
        public static com.somecorp.customer.AddressesTyp parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.somecorp.customer.AddressesTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
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
