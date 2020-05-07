$(document).ready(() => {
    const $landlords = $("#landlords");
    let rating = null;
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

    const $properties = $("#properties");


    let landlordSelected = document.getElementById('landlord-selected');
    let landlordElement = document.getElementById('landlords');
    landlordElement.onclick = function(e) {
        let name = e.target.innerHTML;
        landlordSelected.innerHTML = name;
        return;
    }
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