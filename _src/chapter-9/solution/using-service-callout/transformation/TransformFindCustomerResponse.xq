(:: pragma bea:global-element-parameter parameter="$retrieveCustomerByCriteriaResponse1" element="ns0:RetrieveCustomerByCriteriaResponse" location="../wsdl/CustomerService.wsdl" ::)
(:: pragma bea:global-element-parameter parameter="$retrieveCreditCardInfoByIdResponse1" element="ns4:RetrieveCreditCardByIdResponse" location="../wsdl/CreditCardInfoService.wsdl" ::)
(:: pragma bea:global-element-return element="ns3:FindCustomerResponse" location="../wsdl/CustomerManagement.wsdl" ::)

declare namespace ns2 = "http://www.somecorp.com/customer";
declare namespace ns1 = "http://www.somecorp.com/creditcard";
declare namespace ns4 = "http://www.crm.org/CreditCardInfoService/";
declare namespace ns3 = "http://www.somecorp.org/CustomerManagement";
declare namespace ns0 = "http://www.crm.org/CustomerService/";
declare namespace xf = "http://tempuri.org/basic-osb-service/transformation/TransformFindCustomerResponse/";

declare function xf:TransformFindCustomerResponse($retrieveCustomerByCriteriaResponse1 as element(ns0:RetrieveCustomerByCriteriaResponse),
    $retrieveCreditCardInfoByIdResponse1 as element(ns4:RetrieveCreditCardByIdResponse))
    as element(ns3:FindCustomerResponse) {
        <ns3:FindCustomerResponse>
            <Customer>
                <ns2:ID>{ xs:long($retrieveCustomerByCriteriaResponse1/customers/customer/id) }</ns2:ID>
                <ns2:FirstName>{ data($retrieveCustomerByCriteriaResponse1/customers/customer/firstname) }</ns2:FirstName>
                <ns2:LastName>{ data($retrieveCustomerByCriteriaResponse1/customers/customer/lastname) }</ns2:LastName>
                <ns2:EmailAddress>{ data($retrieveCustomerByCriteriaResponse1/customers/customer/emailAddress) }</ns2:EmailAddress>
                <ns2:Addresses>
                    <ns2:Address>
                        <ns2:Street>{ data($retrieveCustomerByCriteriaResponse1/customers/customer/address/street) }</ns2:Street>
                        <ns2:PostalCode>{ data($retrieveCustomerByCriteriaResponse1/customers/customer/address/zipcode) }</ns2:PostalCode>
                        <ns2:City>{ data($retrieveCustomerByCriteriaResponse1/customers/customer/address/city) }</ns2:City>
                    </ns2:Address>
                </ns2:Addresses>
                <ns2:Rating>{ data($retrieveCustomerByCriteriaResponse1/customers/customer/rating) }</ns2:Rating>
                <ns2:Gender>{ data($retrieveCustomerByCriteriaResponse1/customers/customer/gender) }</ns2:Gender>
                <ns2:CreditCard>
                    <ns1:CardIssuer>{ data($retrieveCreditCardInfoByIdResponse1/creditCard/creditCardType) }</ns1:CardIssuer>
                    <ns1:CardNumber>{ xs:string($retrieveCreditCardInfoByIdResponse1/creditCard/creditCardNumber) }</ns1:CardNumber>
                    <ns1:CardholderName>{ data($retrieveCreditCardInfoByIdResponse1/creditCard/cardHolderName) }</ns1:CardholderName>
                    <ns1:ExpirationDate>{ data($retrieveCreditCardInfoByIdResponse1/creditCard/expiration) }</ns1:ExpirationDate>
                    <ns1:CardValidationCode>{ xs:long($retrieveCreditCardInfoByIdResponse1/creditCard/cvc) }</ns1:CardValidationCode>
                </ns2:CreditCard>
            </Customer>
        </ns3:FindCustomerResponse>
};

declare variable $retrieveCustomerByCriteriaResponse1 as element(ns0:RetrieveCustomerByCriteriaResponse) external;
declare variable $retrieveCreditCardInfoByIdResponse1 as element(ns4:RetrieveCreditCardByIdResponse) external;

xf:TransformFindCustomerResponse($retrieveCustomerByCriteriaResponse1,
    $retrieveCreditCardInfoByIdResponse1)
