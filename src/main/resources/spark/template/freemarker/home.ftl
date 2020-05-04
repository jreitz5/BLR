<#assign content>
<div id="content-wrapper">
  <div id="content">
    <div id="filters-wrapper">
      <label class="dropdown-label">
        <div class="dropdown-btn">
          Sort by
        </div>
        <input type="checkbox" class="dropdown-input" id="sort-by">
        <ul class="dropdown-menu">
          <li>Newest</li>
          <li>Oldest</li>
          <li>Highest Ratings</li>
        </ul>
      </label>
      <label class="dropdown-label">
        <div class="dropdown-btn">
          Landlord
        </div>
        <input type="checkbox" class="dropdown-input" id="landlord">
        <ul class="dropdown-menu">
          <li>Jessica Alba</li>
          <li>Will Smith</li>
          <li>Thomas Jefferson</li>
          <li>Oprah Winfrey</li>
        </ul>
      </label>
      <label class="dropdown-label">
        <div class="dropdown-btn">
          Property
        </div>
        <input type="checkbox" class="dropdown-input" id="property">
        <ul class="dropdown-menu">
          <li>184 Ives St Unit 2</li>
          <li>89 Williams St</li>
          <li>64 John St</li>
        </ul>
      </label>
    </div>
    <div id="landlords-wrapper">
      <div class="landlord">
        <img src="../images/landlordicon.png" alt="landlord icon" class="landlord-icon">
        <div class="landlord-content-wrapper">
          <h4>Jessica Alba</h4>
          <img src="../images/quotation.png" alt="opening quotation mark" class="quotation">
          <div class="comment-wrapper">
            <a>Jessica really cares about her tenants and makes sure to answer maintenance requests immediately. She's really lenient on rent deadlines and understanding.</a>
          </div>
        </div>
      </div>
      <div class="landlord">
          <img src="../images/landlordicon.png" alt="landlord icon" class="landlord-icon">
          <div class="landlord-content-wrapper">
            <h4>Will Smith</h4>
            <img src="../images/quotation.png" alt="opening quotation mark" class="quotation">
            <div class="comment-wrapper">
              <a>Can you believe Will Smith is our landlord?!?! Our house at 188 Preston is the best house you could ever rent. We have a 6 bedroom roommate and always throw parties and never get in trouble.</a>
            </div>
          </div>
        </div>
        <div class="landlord">
          <img src="../images/landlordicon.png" alt="landlord icon" class="landlord-icon">
          <div class="landlord-content-wrapper">
            <h4>Thomas Jefferson</h4>
            <img src="../images/quotation.png" alt="opening quotation mark" class="quotation">
            <div class="comment-wrapper">
              <a>Thomas is the worst landlord of all time. Honestly he doesn't give a sh*t that we've been robbed 4 times and refuses to take extra security measures and mend the windows that aren't able to be locked. Plus he never returned our security deposit so my father is suing.</a>
            </div>
          </div>
        </div>
        <div class="landlord">
            <img src="../images/landlordicon.png" alt="landlord icon" class="landlord-icon">
            <div class="landlord-content-wrapper">
              <h4>Oprah Winfrey</h4>
              <img src="../images/quotation.png" alt="opening quotation mark" class="quotation">
              <div class="comment-wrapper">
                <a>Oprah is a pretty good landlord honestly.</a>
              </div>
            </div>
          </div>
    </div>
  </div>
</div>

</#assign>
<#include "main.ftl">