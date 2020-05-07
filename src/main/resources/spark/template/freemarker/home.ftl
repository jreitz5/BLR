<#assign content>
<div id="content-wrapper">
  <div id="content" role="main">
    <div id="filters-wrapper">
      <label class="dropdown-label">
        <h2><div class="dropdown-btn">
          Sort by
        </div></h2>
        <input type="checkbox" class="dropdown-input" id="sort-by">
        <ul class="dropdown-menu">
          <li>Newest</li>
          <li>Oldest</li>
          <li>Highest Ratings</li>
        </ul>
      </label>
      <label class="dropdown-label">
        <h2><div class="dropdown-btn" id="landlord-selected">
          Landlord
        </div></h2>
        <input type="checkbox" class="dropdown-input" id="landlord">
        <ul class="dropdown-menu" id="landlord-dropdown">
        </ul>
      </label>
      <label class="dropdown-label">
        <h2><div class="dropdown-btn">
          Property
        </div></h2>
        <input type="checkbox" class="dropdown-input" id="property">
        <ul class="dropdown-menu">
          <li>184 Ives St Unit 2</li>
          <li>89 Williams St</li>
          <li>64 John St</li>
        </ul>
      </label>
    </div>
    <div id="landlords-wrapper">
    	<div id="reviews"></div>
    	<#--
	<#list reviews as rev>
      <div class="landlord">
        <img src="../images/landlordicon.png" alt="landlord icon" class="landlord-icon">
        <div class="landlord-content-wrapper">
          <h3>${rev[8]} ${rev[9]}</h3>
          <img src="../images/quotation.png" alt="opening quotation mark" class="quotation">
          <div class="comment-wrapper">
            <a>${rev[5]}</a>
          </div>
        </div>
      </div>	  
	</#list>
		-->
    </div>
  </div>
</div>
<script src="js/home.js"></script>
</#assign>
<#include "main.ftl">