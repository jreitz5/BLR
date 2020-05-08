function submitFeedback() {
    const elements = {
        landlord:$("#newlandlord-text").val(),
        property:$("#newproperty-text").val(),
        additional:$("#additional-text").val()
    };
    console.log(hi);
    console.log(elements.landlord);


    $.post("/submit_review/submit", elements, jsonResponse => {
        const response = JSON.parse(jsonResponse);
        console.log(response);
    });
}