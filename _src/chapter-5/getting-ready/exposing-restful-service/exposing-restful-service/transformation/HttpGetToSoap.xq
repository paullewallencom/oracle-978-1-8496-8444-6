xquery version "1.0" encoding "Cp1252";
(:: pragma bea:global-element-return element="ns0:RetrieveCustomerByCriteria" location="../wsdl/CustomerServiceCRM.wsdl" ::)

declare namespace xf = "http://tempuri.org/exposing-restful-service/transformation/HttpGetToSoap/";
declare namespace ns0 = "http://www.crm.org/CustomerService/";

declare function xf:test($queryString as xs:string)
    as element(ns0:RetrieveCustomerByCriteria) {
        <ns0:RetrieveCustomerByCriteria>
            <criterias>
              {
              	for $nameValue in fn:tokenize($queryString,"&amp;")
              	return            
               		<criteria>    	
                    	<criteriaField>{ fn:substring-before($nameValue,'=') }</criteriaField>
                    	<criteriaValue>{ fn:substring-after($nameValue,'=') }</criteriaValue>
                    </criteria>	
              }
            </criterias>
        </ns0:RetrieveCustomerByCriteria>
};

declare variable $queryString as xs:string external;

xf:test($queryString)
