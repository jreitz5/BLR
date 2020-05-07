function registerUser() {

    const elements = {
        email:$("#email").val(),
        first:$("#f_name").val(),
        last:$("#l_name").val()
    };

    $.post("/create/register", elements, responseJSON => {
        console.log("poo");
        const response = JSON.parse(responseJSON);
        console.log(response);
        let success = response.success;
        if (success === "yes") {
            window.location.href = "/home";
            user_email = elements.email;
        } else {
            alert("User already registered.");
        }
    });
}