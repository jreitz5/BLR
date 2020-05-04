<#assign content>
  <div class="page-box">
    <div class="submit-box">
      <div class="box-title">
        Submit feedback!
      </div>
      <form>
        <div class="check-row">
            <label for="newlandlord"> 
                <input type="checkbox" id="newlandlord" name="newlandlord" value="New Landlord">Tell us about a new landlord
            </label><br>
        </div>
        <textarea class="skinny-text" placeholder="Unlisted landlord name"></textarea>
        <div class="check-row">
            <label for="newproperty"> 
                <input type="checkbox" id="newproperty" name="newproperty" value="New Property">Tell us about a new property
            </label><br>
        </div>
        <textarea class="skinny-text" placeholder="Unlisted property address"></textarea>
        <div class="check-row">
            <label for="additional"> 
                <input type="checkbox" id="additional" name="additional" value="Additional Feedback"> Anything else?
            </label><br>
        </div>
        <textarea class="review-text" placeholder="Additional feedback/concerns"></textarea>
        <div class="check-row">
            <label for="anonymous"> 
                <input type="checkbox" id="anonymous" name="anonymous" value="anonymous"> Make me anonymous
            </label><br>
        </div>
        <div class="submit-row">
            <button class="submit-button"> Submit</button>
          </div>

        
      </form>
    </div>
  </div>

</#assign>
<#include "main.ftl">