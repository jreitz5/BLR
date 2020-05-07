<#assign content>
<div id="content-wrapper">
  <div id="content" role="main">
    <div id="filters-wrapper">
      <label class="dropdown-label">
        <h2><div class="dropdown-btn" id="sort-selected">
          Sort by
        </div></h2>
        <input type="checkbox" class="dropdown-input" id="sort-by">
        <ul class="dropdown-menu" id="sort-dropdown">
          <li>Select...</li>
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
        <h2><div class="dropdown-btn" id="property-selected">
          Property
        </div></h2>
        <input type="checkbox" class="dropdown-input" id="property">
        <ul class="dropdown-menu" id="property-dropdown">
        </ul>
      </label>
      <label>
      <form class="form-inline">
          <input class="form-control mr-sm-2" type="search" placeholder="Search..." role="search" aria-label="Search" style="font-size:1.5vw;font-family:Palatino,serif;text-align:center;height:40px;width:160px;border-radius:18px;margin-top:-12px;" id="search-input">
        </form>
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