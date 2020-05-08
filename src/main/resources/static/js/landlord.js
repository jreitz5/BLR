$(document).ready( () => {
	const $reviewswrapper = $("#reviews-wrapper");
	const $propertiescontainer = $("#properties-container");
	let reviewsList = [];
	let currentLandlord = "Current Landlord";
	let ratings = [];
	let properties = [];

	var url = document.location.href,
	params = url.split('?')[1].split('&'),
	data = {}, tmp;
	for (var i = 0, l = params.length; i < l; i++) {
		tmp = params[i].split('=');
		data[tmp[0]] = tmp[1];
	}
	currentLandlord = this.decodeURIComponent(data.name);
	this.console.log(this.decodeURIComponent(data.name));
	document.getElementById("landlord-name").innerHTML = currentLandlord;

    // post request to fetch the list of reviews from the database
	$.post("/home/reviews", responseJSON => {
		const response = JSON.parse(responseJSON);
		for (let i = 0, len = response.reviews.length; i < len; i++) {
			const review = response.reviews[i]
			// reviewObj is just for making reviews more accessible vs having to use indexing
			const reviewObj = {landlord_name: review[8] + " " + review[9], review: review[5], date: review[7], rating: review[4], address: review[10] };
			reviewsList.push(reviewObj);
			if (currentLandlord === reviewObj.landlord_name){
				let stars = determineStars(reviewObj.rating);
				const str = generateReviewHTML(reviewObj.landlord_name, reviewObj.review, stars);
				$reviewswrapper.append(str);

				ratings.push(parseInt(reviewObj.rating));

				if(!properties.includes(reviewObj.address)){
					properties.push(reviewObj.address);
				}
				
			}
		}
		let total = 0;
		for (let index = 0; index < ratings.length; index++) {
			total = total + ratings[index];	
		}
		let average = total / ratings.length;
		let avgStars = determineStars(Math.round(average).toString());
		document.getElementById("stars").innerHTML = avgStars;

		for (let index = 0; index < properties.length; index++){
			$propertiescontainer.append("<a>&nbsp&nbsp" + properties[index] + "</a>")
		}
		
	 });
});


function determineStars(num) {
	if (num === "1") {
		return "<span>★</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span>";
	} else if (num === "2") {
		return "<span>★</span><span>★</span><span>☆</span><span>☆</span><span>☆</span>";
	} else if (num === "3") {
		return "<span>★</span><span>★</span><span>★</span><span>☆</span><span>☆</span>";
	} else if (num === "4") {
		return "<span>★</span><span>★</span><span>★</span><span>★</span><span>☆</span>";
	} else if (num === "5") {
		return "<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>";
	}
}

const generateReviewHTML = function(landlordName, review, stars) {
	const str = "<div class=\"review\">" +
                "<div class=\"landlord-content-wrapper\">" +
                    "<div class=\"row-left\">" +
                        "<div class=\"review-star-row\">" +
							"<div class=\"rating\">" + stars + "</div>" +
                        "</div>" +
                    "</div>" +
                  "<img src=\"../images/quotation.png\" alt=\"opening quotation mark\" class=\"quotation\">" +
                  "<div class=\"comment-wrapper\">" +
					"<a>" + review + "<a>" +
					"</div></div></div>"
	return str
}