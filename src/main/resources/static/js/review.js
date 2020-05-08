$(document).ready(() => {
    const $landlords = $("#landlords");
    const $properties = $("#properties");
    const $landlord_selector = $("#landlord-selected");
    const $property_selector = $("#property-selected");

    let properties_list;

    // Post suggestions
    $.post("/submit_review/data", jsonResponse => {
        const response = JSON.parse(jsonResponse);
        properties_list = response.properties;
        for (let i = 0, len = response.landlords.length; i < len; i++) {
            const landlord = response.landlords[i];
            const str = "<div class=\"dropdown-item\">" +
                landlord + "</div>";
            $landlords.append(str);
        }
    });

    let landlordSelected = document.getElementById('landlord-selected');
    let landlordElement = document.getElementById('landlords');
    let propertiesSelected = document.getElementById('property-selected');
    let propertiesElement = document.getElementById('properties');

    landlordElement.onclick = function(e) {
        let name = e.target.innerHTML;
        landlordSelected.innerHTML = name;
        propertiesSelected.innerHTML = "optional";
        return;
    }   
     $property_selector.click(function() {
        let landlordVal = $landlord_selector.text();
        $properties.empty();
        for (let i = 0;  i < properties_list.length; i++) {
            let land_name = properties_list[i][0];
            let prop_name = properties_list[i][1];

            if (landlordVal === land_name) {
                const stringy = "<div class=\"dropdown-item\">" +
                    prop_name + "</div>";
                $properties.append(stringy);
            }
        }
    });

    propertiesElement.onclick = function(e) {
        let name = e.target.innerHTML;
        propertiesSelected.innerHTML = name;
        return;
    };
});




let five = document.getElementById("five");
let four = document.getElementById("four");
let three = document.getElementById("three");
let two = document.getElementById("two");
let one = document.getElementById("one");
let stars = [one, two, three, four, five];

let rating = null;
//sets HTML on page load since star doesn't load properly normally
stars.forEach( function(element) {
    element.innerHTML = "☆"; 
});

function refreshStars(num){
    stars.forEach( function(element) {
        element.innerHTML = "☆"; 
    });
    for (let index = 0; index < num; index++) {
        stars[index].innerHTML = "★";
    }
}

function ffive(){
    rating = 5;
    refreshStars(rating);
}
function ffour(){
    rating = 4;
    refreshStars(rating);
}
function fthree(){
    rating = 3;
    refreshStars(rating);
}
function ftwo(){
    rating = 2;
    refreshStars(rating);
}
function fone(){
    rating = 1;
    refreshStars(rating);
}

function submitReview() {
    const elements = {
        landlord_name:$("#landlord-selected").text(),
        property:$("#property-selected").text(),
        rating:rating,
        text:$("#review-text").val(),
        email:user_email
    };


    $.post("/submit_review/submit", elements, jsonResponse => {
        const response = JSON.parse(jsonResponse);
        console.log(response);
    });
}
