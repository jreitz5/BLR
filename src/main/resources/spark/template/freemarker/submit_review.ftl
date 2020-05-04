<#assign content>
  <div class="page-box">
    <div class="submit-box">
      <div class="box-title">
        Leave a Review!
      </div>
      <form>
        <div class="landlord-row">
          Landlord:
          <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              required
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
              <a class="dropdown-item" href="#">need a get request to get all of them!</a>
            </div>
          </div>
        </div>
        <div class="property-row">
          Property:
          <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              optional
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
              <a class="dropdown-item" href="#">need a get request to get all of them!</a>
            </div>
          </div>
        </div>
        <div class="star-row">
          Rating:
          <div class="rating">
            <span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span>
          </div>
        </div>
        <textarea class="review-text" placeholder="Type your review here!"></textarea>
        <div class="submit-row">
          <button class="submit-button"> Submit</button>
        </div>
      </form>
    </div>
  </div>

</#assign>
<#include "main.ftl">
