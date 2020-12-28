(:: pragma bea:global-element-return element="ns0:DeleteExistingCustomer" location="../wsdl/CustomerServiceCRM.wsdl" ::)

declare namespace ns0 = "http://www.crm.org/CustomerService/";
declare namespace xf = "http://tempuri.org/exposing-restful-service/transformation/HttpDeleteToSoap/";

declare function xf:HttpDeleteToSoap($id as xs:string)
    as element(*) {
        	<ns0:DeleteExistingCustomer>
            	<id>{ xs:int($id) }</id>
        	</ns0:DeleteExistingCustomer>
};

declare variable $id as xs:string external;

xf:HttpDeleteToSoap($id)