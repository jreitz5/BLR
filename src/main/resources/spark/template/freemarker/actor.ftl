<#assign content>
<header> <codeHead>
<div style="text-align:center">
  <p><strong>${intro}</strong></p>
</div></codeHead></header>
<div style="text-align:center">
<section> <code>
<#list films as dw>
  <li><a href="/film/${dw.encoded}">${dw.name}</a>
</li>
</#list>
</code>
</section>
</div>
</#assign>
<#include "main.ftl">

