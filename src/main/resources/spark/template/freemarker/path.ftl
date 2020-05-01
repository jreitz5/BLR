<#assign message>
<p>
<header> <codeHead>
<div style="text-align:center">
  <p><strong>The shortest bacon path is:</strong></p>
</div></codeHead></header>
<div style="text-align:center">
<#list passes as dw>
  <li><a href="/actor/${dw.src.encoded}">${dw.src.name}</a> passes the bacon to <a href="/actor/${dw.dest.encoded}">${dw.dest.name}</a> in the film <a href="/film/${dw.movie.encoded}">${dw.movie.name}</a>
</li>
</#list>
</div>
</#assign>
<#include "bacon.ftl">

