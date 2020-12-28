/*
 * XML Type:  AddressTyp
 * Namespace: http://www.somecorp.com/customer
 * Java type: com.somecorp.customer.AddressTyp
 *
 * Automatically generated - do not modify.
 */
package com.somecorp.customer;


/**
 * An XML AddressTyp(@http://www.somecorp.com/customer).
 *
 * This is a complex type.
 */
public interface AddressTyp extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(AddressTyp.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s178E60C7478763B9F9AEBA2650BA3F15").resolveHandle("addresstyp7213type");
    
    /**
     * Gets the "Street" element
     */
    java.lang.String getStreet();
    
    /**
     * Gets (as xml) the "Street" element
     */
    org.apache.xmlbeans.XmlString xgetStreet();
    
    /**
     * Sets the "Street" element
     */
    void setStreet(java.lang.String street);
    
    /**
     * Sets (as xml) the "Street" element
     */
    void xsetStreet(org.apache.xmlbeans.XmlString street);
    
    /**
     * Gets the "PostalCode" element
     */
    java.lang.String getPostalCode();
    
    /**
     * Gets (as xml) the "PostalCode" element
     */
    org.apache.xmlbeans.XmlString xgetPostalCode();
    
    /**
     * Sets the "PostalCode" element
     */
    void setPostalCode(java.lang.String postalCode);
    
    /**
     * Sets (as xml) the "PostalCode" element
     */
    void xsetPostalCode(org.apache.xmlbeans.XmlString postalCode);
    
    /**
     * Gets the "City" element
     */
    java.lang.String getCity();
    
    /**
     * Gets (as xml) the "City" element
     */
    org.apache.xmlbeans.XmlString xgetCity();
    
    /**
     * Sets the "City" element
     */
    void setCity(java.lang.String city);
    
    /**
     * Sets (as xml) the "City" element
     */
    void xsetCity(org.apache.xmlbeans.XmlString city);
    
    /**
     * Gets the "Country" element
     */
    java.lang.String getCountry();
    
    /**
     * Gets (as xml) the "Country" element
     */
    org.apache.xmlbeans.XmlString xgetCountry();
    
    /**
     * Sets the "Country" element
     */
    void setCountry(java.lang.String country);
    
    /**
     * Sets (as xml) the "Country" element
     */
    void xsetCountry(org.apache.xmlbeans.XmlString country);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static com.somecorp.customer.AddressTyp newInstance() {
          return (com.somecorp.customer.AddressTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.somecorp.customer.AddressTyp newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.somecorp.customer.AddressTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.somecorp.customer.AddressTyp parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.customer.AddressTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.somecorp.customer.AddressTyp parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.customer.AddressTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.somecorp.customer.AddressTyp parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.AddressTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.somecorp.customer.AddressTyp parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.AddressTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.somecorp.customer.AddressTyp parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.AddressTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.somecorp.customer.AddressTyp parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.AddressTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.somecorp.customer.AddressTyp parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.AddressTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.somecorp.customer.AddressTyp parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.AddressTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.somecorp.customer.AddressTyp parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.AddressTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.somecorp.customer.AddressTyp parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.customer.AddressTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.somecorp.customer.AddressTyp parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.customer.AddressTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.somecorp.customer.AddressTyp parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.customer.AddressTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.somecorp.customer.AddressTyp parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.customer.AddressTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.somecorp.customer.AddressTyp parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.customer.AddressTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        @Deprecated
        public static com.somecorp.customer.AddressTyp parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.somecorp.customer.AddressTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        @Deprecated
        public static com.somecorp.customer.AddressTyp parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.somecorp.customer.AddressTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
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
