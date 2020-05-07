<#assign content>
<div id="content-wrapper">
<div id="content" role="main">
  <div class="page-box">
    <div class="submit-box">
      <div class="box-title">
        Submit feedback!
      </div>
      <form role="form">
        <div class="check-row">
            <label for="newlandlord"> 
              <input type="checkbox" id="newlandlord" name="newlandlord" value="New Landlord" aria-label="Checkbox to tell us about a new landlord"><label for="newlandlord-text">Tell us about a new landlord</label>
            </label><br>
        </div>
        <textarea class="skinny-text" id="newlandlord-text" placeholder="Unlisted landlord name" name="newlandlord-text"></textarea>
        <div class="check-row">
            <label for="newproperty"> 
              <input type="checkbox" id="newproperty" name="newproperty" value="New Property" aria-label="Checkbox to tell us about a new property"><label for="newproperty-text">Tell us about a new property</label>
            </label><br>
        </div>
        <textarea class="skinny-text" id="newproperty-text" placeholder="Unlisted property address"></textarea>
        <div class="check-row">
            <label for="additional"> 
              <input type="checkbox" id="additional" name="additional" value="Additional Feedback" aria-label="Checkbox to share any additional feedback"><label for="additional-text">Anything else?</label>
            </label><br>
        </div>
        <textarea class="review-text" id="additional-text" placeholder="Additional feedback/concerns"></textarea>
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
</#assign>
<#include "main.ftl">