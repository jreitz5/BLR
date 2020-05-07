let user_email;

function signin() {

    const elements = {
        email:$("#email").val()
    };

    let checker = document.getElementById('check-response');

    $.post("/login/check", elements, responseJSON => {
        const response = JSON.parse(responseJSON);
        console.log(response);
        let success = response.success;
        if (success === "yes") {
            window.location.href = "/home";
        } else {
            alert("Email not registered, please try again or create an account");
        }
    });
}