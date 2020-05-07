$(document).ready(() => {
    const $landlords = $("#landlords");
    // Post suggestions
    $.post("/submit_review/data", jsonResponse => {
        const response = JSON.parse(jsonResponse);
        for (let i = 0, len = response.landlords.length; i < len; i++) {
            const landlord = response.landlords[i];
            console.log(response.landlords[i]);
            const str = "<div class=\"dropdown-item\">" +
                landlord + "</div>";
            $landlords.append(str);
        }
    });
});


