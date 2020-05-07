<#assign content>
<div id="content-wrapper">
<div id="content" role="main">
  <div class="page-box">
    <div class="submit-box">
      <h1><div class="box-title" style="font-size:2vw;">
        Leave a Review!
      </div></h1>
      <form role="form">
        <div class="landlord-row">
          <h2>Landlord:</h2>
          <div class="dropdown">

            <button class="btn btn-secondary dropdown-toggle" type="button" value="required" id="landlord-selected" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color:#ffffff;background-color:#363636;font-size:1vw;">required</button>

            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" id="landlords">
            </div>
          </div>
        </div>
        <div class="property-row">
          <h2>Property:</h2>
          <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="property-selected" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color:#ffffff;background-color:#363636;font-size:1vw;">
              optional
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" id="properties">
            </div>
          </div>
        </div>
        <div class="star-row">
          <h2>Rating:</h2>
          <div class="rating">
            <span onclick="ffive()" id="five">☆</span>
            <span onclick="ffour()" id="four">☆</span>
            <span onclick="fthree()" id="three">☆</span>
            <span onclick="ftwo()" id="two">☆</span>
            <span onclick="fone()" id="one">☆</span>
          </div>
        </div>
        <textarea class="review-text" placeholder="Type your review here!" aria-label="Type your review here!" style="font-size:1.5vw;height:25vh;"></textarea>
        <div class="check-row">
          <label for="anonymous">
            <input type="checkbox" id="anonymous" name="anonymous" value="anonymous" aria-label="Checkbox to make me anonymous">Make me anonymous
          </label><br>
        </div>
        <div class="submit-row">
          <button class="submit-button" style="color:#ffffff;background-color:#363636;font-size:1.5vw;width:8vw;height:5vh;border-radius:10px;">Submit</button>
        </div>
      </form>
    </div>
  </div>
</div>
</div>
<script src="js/review.js"></script>
</#assign>
<#include "main.ftl">
