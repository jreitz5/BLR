<#assign content>
<p>
<header> <codeHead>
<div style="text-align:center">
  <p><strong>${intro}</strong></p>
</div></codeHead></header>
<div style="text-align:center">
<#list actors as dw>
  <li><a href="/actor/${dw.encoded}">${dw.name}</a>
</li>
</#list>
</div>
</#assign>
<#include "main.ftl">

