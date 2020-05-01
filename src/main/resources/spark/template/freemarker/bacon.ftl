<#assign content>

<form method="POST" action="/passes">
<header> <codeHead>
<div style="text-align:center">
  <p><strong>Enter in two actors to find the shortest path between them!</strong></p>
</div></codeHead></header>
<div style="text-align:center">
</div>
<section>
  <code>
<p><strong>Please give a start actor!</strong></p>
<input type="text" id="src" name="src" value=""><br>
<ul id="suggestionsSrc"></ul>
</code>
</section>
<sectionOr>
<codeOr>
<div style = "text-align:center">
<p><br><strong>to</strong></p>
</codeOr>
</div>
</sectionOr>

<section><code>
<p><strong>Please give an ending actor!</strong></p>
<input type="text" id="dst" name="dst" value=""><br>
<ul id="suggestionsDst"></ul>
</p>
</code></section>

<div style = "text-align: center">
<footer>
  <p>Once you're done, click</p>
  <input type="checkbox" value="check">
  <input type="submit">
</footer>
</div>
</form>
</#assign>
<#include "main.ftl">

