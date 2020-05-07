<#assign content>
    <div class="page-box">
        <div class="submit-box">
            <div class="box-title">
                Welcome to Brown Landlord Review.
            </div>
            <div class="login-title">
                <a class="gray-text" href="/login">Login</a>/Signup
            </div>

            <#--        <a class="g-signin2" data-onsuccess="onSignIn" data-onfailure="onFailure" data-longtitle="true"></a>-->

            <form>
                <textarea class="login-text" placeholder="email" id="email"></textarea>
                <div class="login-title-2 gray-text">
                    Enter your brown.edu email address
                </div>
                <textarea class="login-text" placeholder="first name" id="f_name"></textarea>
                <div class="login-title-2 gray-text">
                    Enter your first name
                </div>
                <textarea class="login-text" placeholder="last name" id="l_name"></textarea>
                <div class="login-title-2 gray-text">
                    Enter your last name
                </div>
                <div class="submit-row">
                    <button class="login-button" onclick="registerUser()"> Signup!</button>
                </div>
            </form>

        </div>
    </div>
 <script src="js/create.js"></script>
</#assign>
<#include "main.ftl">