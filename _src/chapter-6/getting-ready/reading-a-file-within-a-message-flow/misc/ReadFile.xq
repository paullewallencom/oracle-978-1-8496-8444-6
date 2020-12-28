xquery version "1.0" encoding "Cp1252";
(:: pragma  type="xs:anyType" ::)

declare namespace xf = "http://tempuri.org/ReadFile/ReadFileDoc/";
declare namespace prop = "http://packt.osb.book/services";

declare function xf:ReadFileDoc($directory as xs:string, $filename as xs:string)
    as element(*) {
        let $myProperties := doc(concat($directory, '/', $filename))
        return 
<my_value>{data($myProperties/prop:properties/prop:property[1]/prop:value)}</my_value>  
};

declare variable $directory as xs:string external;
declare variable $filename as xs:string external;
	
xf:ReadFileDoc($directory, $filename)
