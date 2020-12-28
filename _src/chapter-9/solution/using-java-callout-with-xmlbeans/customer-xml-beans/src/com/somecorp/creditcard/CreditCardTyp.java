/*
 * XML Type:  CreditCardTyp
 * Namespace: http://www.somecorp.com/creditcard
 * Java type: com.somecorp.creditcard.CreditCardTyp
 *
 * Automatically generated - do not modify.
 */
package com.somecorp.creditcard;


/**
 * An XML CreditCardTyp(@http://www.somecorp.com/creditcard).
 *
 * This is a complex type.
 */
public interface CreditCardTyp extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CreditCardTyp.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s178E60C7478763B9F9AEBA2650BA3F15").resolveHandle("creditcardtyp20e5type");
    
    /**
     * Gets the "CardIssuer" element
     */
    com.somecorp.creditcard.CardIssuerTyp.Enum getCardIssuer();
    
    /**
     * Gets (as xml) the "CardIssuer" element
     */
    com.somecorp.creditcard.CardIssuerTyp xgetCardIssuer();
    
    /**
     * Sets the "CardIssuer" element
     */
    void setCardIssuer(com.somecorp.creditcard.CardIssuerTyp.Enum cardIssuer);
    
    /**
     * Sets (as xml) the "CardIssuer" element
     */
    void xsetCardIssuer(com.somecorp.creditcard.CardIssuerTyp cardIssuer);
    
    /**
     * Gets the "CardNumber" element
     */
    java.lang.String getCardNumber();
    
    /**
     * Gets (as xml) the "CardNumber" element
     */
    org.apache.xmlbeans.XmlString xgetCardNumber();
    
    /**
     * Sets the "CardNumber" element
     */
    void setCardNumber(java.lang.String cardNumber);
    
    /**
     * Sets (as xml) the "CardNumber" element
     */
    void xsetCardNumber(org.apache.xmlbeans.XmlString cardNumber);
    
    /**
     * Gets the "CardholderName" element
     */
    java.lang.String getCardholderName();
    
    /**
     * Gets (as xml) the "CardholderName" element
     */
    org.apache.xmlbeans.XmlString xgetCardholderName();
    
    /**
     * Sets the "CardholderName" element
     */
    void setCardholderName(java.lang.String cardholderName);
    
    /**
     * Sets (as xml) the "CardholderName" element
     */
    void xsetCardholderName(org.apache.xmlbeans.XmlString cardholderName);
    
    /**
     * Gets the "ExpirationDate" element
     */
    java.lang.String getExpirationDate();
    
    /**
     * Gets (as xml) the "ExpirationDate" element
     */
    org.apache.xmlbeans.XmlString xgetExpirationDate();
    
    /**
     * Sets the "ExpirationDate" element
     */
    void setExpirationDate(java.lang.String expirationDate);
    
    /**
     * Sets (as xml) the "ExpirationDate" element
     */
    void xsetExpirationDate(org.apache.xmlbeans.XmlString expirationDate);
    
    /**
     * Gets the "CardValidationCode" element
     */
    long getCardValidationCode();
    
    /**
     * Gets (as xml) the "CardValidationCode" element
     */
    org.apache.xmlbeans.XmlLong xgetCardValidationCode();
    
    /**
     * Sets the "CardValidationCode" element
     */
    void setCardValidationCode(long cardValidationCode);
    
    /**
     * Sets (as xml) the "CardValidationCode" element
     */
    void xsetCardValidationCode(org.apache.xmlbeans.XmlLong cardValidationCode);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static com.somecorp.creditcard.CreditCardTyp newInstance() {
          return (com.somecorp.creditcard.CreditCardTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.somecorp.creditcard.CreditCardTyp newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.somecorp.creditcard.CreditCardTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.somecorp.creditcard.CreditCardTyp parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.creditcard.CreditCardTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.somecorp.creditcard.CreditCardTyp parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.creditcard.CreditCardTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.somecorp.creditcard.CreditCardTyp parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.creditcard.CreditCardTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.somecorp.creditcard.CreditCardTyp parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.creditcard.CreditCardTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.somecorp.creditcard.CreditCardTyp parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.creditcard.CreditCardTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.somecorp.creditcard.CreditCardTyp parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.creditcard.CreditCardTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.somecorp.creditcard.CreditCardTyp parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.creditcard.CreditCardTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.somecorp.creditcard.CreditCardTyp parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.creditcard.CreditCardTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.somecorp.creditcard.CreditCardTyp parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.creditcard.CreditCardTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.somecorp.creditcard.CreditCardTyp parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.somecorp.creditcard.CreditCardTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.somecorp.creditcard.CreditCardTyp parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.creditcard.CreditCardTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.somecorp.creditcard.CreditCardTyp parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.creditcard.CreditCardTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.somecorp.creditcard.CreditCardTyp parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.creditcard.CreditCardTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.somecorp.creditcard.CreditCardTyp parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.somecorp.creditcard.CreditCardTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        @Deprecated
        public static com.somecorp.creditcard.CreditCardTyp parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.somecorp.creditcard.CreditCardTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        @Deprecated
        public static com.somecorp.creditcard.CreditCardTyp parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.somecorp.creditcard.CreditCardTyp) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
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
