$(document).ready(() => {
	const $reviews = $("#reviews");
	const $sortBy = $("#sort-by");
	$sortBy.append("<li>Select...</li>");
	const $landlordList = $("#landlord-dropdown");
	$landlordList.append("<li>Select...</li>");
	let reviewsList = [];
	let filtered = [];
	
	const updateReviewsList = function(list) {
		$reviews.empty();
		for (let i = 0, len = list.length; i < len; i++) {
			const reviewObj = list[i];
			const str = generateReviewHTML(reviewObj.landlord_name, reviewObj.review);
			$reviews.append(str);
		}
	}
	
	$.post("/home/reviews", responseJSON => {
		const response = JSON.parse(responseJSON);
		for (let i = 0, len = response.reviews.length; i < len; i++) {
			const review = response.reviews[i]
			// reviewObj is just for making reviews more accessible vs having to use indexing
			const reviewObj = {landlord_name: review[8] + " " + review[9], review: review[5], date: review[7] };
			reviewsList.push(reviewObj);
			const str = generateReviewHTML(reviewObj.landlord_name, reviewObj.review);
			$reviews.append(str);
			$landlordList.append("<li>" + reviewObj.landlord_name + "</li>");
		}
	 });
	 
	 let landlordElement = document.getElementById('landlord-dropdown');
	 let landlordSelected = document.getElementById('landlord-selected');
	 landlordElement.onclick = function(e) {
	 	const name = e.target.innerHTML;
	 	if (name === "Select...") {
	 		updateReviewsList(reviewsList);
	 		filtered = reviewsList;
	 		landlordSelected.innerHTML = "Landlord";
	 		return;
	 	}
	 	filtered = reviewsList.filter(r => r.landlord_name === name);
	 	landlordSelected.innerHTML = name;
	 	updateReviewsList(filtered);
	 }
	 
	 

});

const generateReviewHTML = function(landlordName, review) {
	const str = "<div class=\"landlord\">"
			+ "<img src=\"../images/landlordicon.png\" alt=\"landlord icon\" class=\"landlord-icon\">"
			+ "<div class=\"landlord-content-wrapper\">"
			+ "<h3>" + landlordName + "</h3>"
			+ "<img src=\"../images/quotation.png\" alt=\"opening quotation mark\" class=\"quotation\">"
			+ "<div class=\"comment-wrapper\">"
			+ "<a>" + review + "</a>"
			+ "</div></div></div>"
	return str;
}

