console.log("PLEASE");
$(document).ready(() => {
    console.log("poooooooo");
    const $suggestions1 = $("#suggestions1");
    // console.log("poopy");
    //     const elements = {
    //         landlord: 1,
    //         property: 0
    //     };

        // get landlords
        jQuery.get("/data", function() {

            const response = JSON.parse(jsonResponse);
            console.log("hi hey hello");
            console.log(response);
        });
    console.log("fu");
});


