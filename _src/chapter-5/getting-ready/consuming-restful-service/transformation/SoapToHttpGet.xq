(:: pragma bea:global-element-parameter parameter="$retrieveCustomerByCriteria1" element="ns0:RetrieveCustomerByCriteria" location="../wsdl/CustomerServiceCRM.wsdl" ::)
(:: pragma bea:global-element-return element="ns1:query-parameters" location="../xsd/HttpTransport.xsd" ::)

declare namespace ns1 = "http://www.bea.com/wli/sb/transports/http";
declare namespace ns0 = "http://www.crm.org/CustomerService/";
declare namespace xf = "http://tempuri.org/consuming-restful-service/transformation/SoapToHttpGet/";

declare function xf:SoapToHttpGet($retrieveCustomerByCriteria1 as element(ns0:RetrieveCustomerByCriteria))
    as element(ns1:query-parameters) {
        <ns1:query-parameters>
            <ns1:parameter name = "{ data($retrieveCustomerByCriteria1/criterias/criteria[1]/criteriaField) }"
                           value = "{ data($retrieveCustomerByCriteria1/criterias/criteria[1]/criteriaValue) }"/>
        </ns1:query-parameters>
};

declare variable $retrieveCustomerByCriteria1 as element(ns0:RetrieveCustomerByCriteria) external;

xf:SoapToHttpGet($retrieveCustomerByCriteria1)
