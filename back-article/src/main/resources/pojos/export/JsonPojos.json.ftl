<#list pojos as pojo> 
${pojo.name?cap_first}:
   	 	 <#if pojo.id??>
    ${pojo.id.name?uncap_first}: ${pojo.reference.type.simpleName} ID
   	 	</#if>
    	<#if pojo.reference??>
    ${pojo.reference.name?uncap_first}: ${pojo.reference.type.simpleName} REF
   	 	</#if>  	
<#list pojo.fieldsGeneric as fieldGeneric>
	${fieldGeneric.name?uncap_first}: ${fieldGeneric.name?cap_first}  
</#list>
<#list pojo.fieldsList as fieldList>
	${fieldList.name?uncap_first}: ${fieldList.type.simpleName?cap_first} LIST 
</#list>

</#list>
