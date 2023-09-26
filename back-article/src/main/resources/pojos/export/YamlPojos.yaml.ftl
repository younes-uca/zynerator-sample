<#list pojos as pojo> 
${pojo.name?cap_first}:  	 		
<#list pojo.fields as field>
    <#if pojo.id.name == field.name>
      ${field.name?uncap_first}: ${field.type.simpleName?cap_first} ID
      <#elseif  pojo.reference?? && pojo.reference.name == field.name>
      ${field.name?uncap_first}: ${field.type.simpleName} REF
       <#elseif field.list>
      ${field.name?uncap_first}: ${field.type.simpleName} LIST
      <#else>
       ${field.name?uncap_first}: ${field.type.simpleName} 
 </#if>
</#list>

</#list>
