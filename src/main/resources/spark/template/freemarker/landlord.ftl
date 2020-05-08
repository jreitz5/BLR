<#assign content>
  <div class="page-box">
    <div class="submit-box">
        <div class="landlord-row">
            <img src="../images/landlordicon.png" alt="landlord icon" class="landlord-icon">
            <div class="landlord-col">
                <div id="landlord-name"> Landlord Name </div>
                <div class="landlord-info">
                    <div class="star-row">
                        Rating:
                        <div class="rating" id="stars">
                            <span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span>
                        </div>
                    </div> 
                    <div class="row-left-properties">
                        Properties:
                        <div class="properties-container" id="properties-container">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="reviews-wrapper">
            </div>
    </div>
  </div>
  <script src="js/landlord.js"></script>
 </#assign>
 <#include "main.ftl">
