<#assign content>
<div class="page-box">
    <div class="submit-box">
      <div class="box-title">
        Welcome to Brown Landlord Review.
      </div>
      <div class="login-title">
          Login<a class="gray-text" href="/create">/Signup</a>
      </div>

<#--        <a class="g-signin2" data-onsuccess="onSignIn" data-onfailure="onFailure" data-longtitle="true"></a>-->

      <form>
        <div class="login-title-2 gray-text">
            Enter your brown.edu email address
        </div>
          <textarea class="login-text" placeholder="email" id="email"></textarea>
          <div class="login-title-2 gray-text" id="check-response">
          </div>
        <div class="submit-row">
            <button class="login-button" onclick="signin()"> Login</button>
        </div>
      </form>

    </div>
  </div>
</#assign>
<#include "main.ftl">