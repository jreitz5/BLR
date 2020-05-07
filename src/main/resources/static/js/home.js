$(document).ready(() => {
	const $reviews = $("#reviews");
	const $landlordList = $("#landlord-dropdown");
	$landlordList.append("<li>Select...</li>");
	const $propertyList = $("#property-dropdown");
	$propertyList.append("<li>Select...</li>");
	const $search = $("#search-input");
	let reviewsList = [];
	let filtered = [];
	let landlords = [];
	let properties = [];
	
	const updateReviewsList = function(list) {
		$reviews.empty();
		for (let i = 0, len = list.length; i < len; i++) {
			const reviewObj = list[i];
			let stars = determineStars(reviewObj.rating);
			console.log(stars);
			const str = generateReviewHTML(reviewObj.landlord_name, reviewObj.review, stars);
			$reviews.append(str);
		}
	}
	
	
	// post request to fetch the list of reviews from the database
	$.post("/home/reviews", responseJSON => {
		const response = JSON.parse(responseJSON);
		for (let i = 0, len = response.reviews.length; i < len; i++) {
			const review = response.reviews[i]
			// reviewObj is just for making reviews more accessible vs having to use indexing
			const reviewObj = {landlord_name: review[8] + " " + review[9], review: review[5], date: review[7], rating: review[4], address: review[10] };
			reviewsList.push(reviewObj);
			let stars = determineStars(reviewObj.rating);
			console.log(stars);
			const str = generateReviewHTML(reviewObj.landlord_name, reviewObj.review, stars);
			$reviews.append(str);
			if (false === Boolean(landlords.includes(reviewObj.landlord_name))) {
				landlords.push(reviewObj.landlord_name);
				$landlordList.append("<li>" + reviewObj.landlord_name + "</li>");
			}
			if (reviewObj.address != null) {
				if (false === Boolean(properties.includes(reviewObj.address))) {
					properties.push(reviewObj.address);
					$propertyList.append("<li>" + reviewObj.address + "</li>");
				}
			}
		}
	 });
	 
	 // sorts a list based on the elements of sort by
	 let sortElement = document.getElementById('sort-dropdown');
	 let sortSelected = document.getElementById('sort-selected');
	 let sortBy = function(list, method) {
		if (method === "Newest")  {
			list.sort(function(a,b){return new Date(b.date) - new Date(a.date)});
		} else if (method === "Oldest") {
			list.sort(function(a,b){return new Date(a.date) - new Date(b.date)});
		}
		if (method === "Highest Ratings") {
			list.sort(function(a,b){return b.rating - a.rating});
		}
		return list;
	 }
	 
	 // filters a list based on the landlord selected
	 let landlordElement = document.getElementById('landlord-dropdown');
	 let landlordSelected = document.getElementById('landlord-selected');
	 let selectLandlord = function(list, name) {
	 	if (name.trim() === "Landlord") {
	 		return list;
	 	}
	 	list = list.filter(r => r.landlord_name === name);
	 	return list;
	 }
	 
	 // filters a list based on the property
	 let propertyElement = document.getElementById('property-dropdown');
	 let propertySelected = document.getElementById('property-selected');
	 let selectProperty = function(list, address) {
	 	if (address.trim() === "Property") {
	 		return list;
	 	}
	 	list = list.filter(r => r.address === address);
	 	return list;
	 }
	 
	 let searchFunc = function(list, str) {
	 	const searchString = str.toLowerCase();
	 	console.log(searchString);
	 	if (!(searchString === "")) {
		 	list = list.filter(elt => elt.landlord_name.toLowerCase().includes(searchString) || elt.review.toLowerCase().includes(searchString));
	 	}
	 	return list;
	 }
	 
	 // carries out all the searching methods - to be called in each onclick function
	 // - the reason why I do this separately is so that you can cross search:
	 // as in sort all the "newest" reviews about "Will Smith's" "251 North Bristol Ave" apartment.
	 let sortWithAllMethods = function(list, sortbyMethod, landlord, property, searchTerm) {
	 	list = sortBy(list, sortbyMethod);
	 	list = selectLandlord(list, landlord);
	 	list = selectProperty(list, property);
	 	list = searchFunc(list, searchTerm);
	 	return list
	 }
	 
	 
	 landlordElement.onclick = function(e) {
	 	const name = e.target.innerHTML;
	 	filtered = reviewsList;
	 	if (name === "Select..." || name.includes("<li>")) {
	 		landlordSelected.innerHTML = "Landlord";
	 	} else {
	 		landlordSelected.innerHTML = name;
	 	}
	 	
	 	filtered = sortWithAllMethods(filtered, sortSelected.innerHTML, landlordSelected.innerHTML, propertySelected.innerHTML, $search.val());
	 	updateReviewsList(filtered);
	 }
	 
	 
	 sortElement.onclick = function(e) {
	 	const sortMethod = e.target.innerHTML;
	 	filtered = reviewsList;
	 	if (sortMethod === "Select..." || sortMethod.includes("<li>")) {
	 		sortSelected.innerHTML = "Sort by";
	 	} else {
	 		sortSelected.innerHTML = sortMethod;
	 	}
	 	
	 	filtered = sortWithAllMethods(filtered, sortSelected.innerHTML, landlordSelected.innerHTML, propertySelected.innerHTML, $search.val());
	 	updateReviewsList(filtered);
	 }
	 
	
	 propertyElement.onclick = function(e) {
	 	const address = e.target.innerHTML;
	 	filtered = reviewsList;
	 	if (address === "Select..." || address.includes("<li>")) {
	 		propertySelected.innerHTML = "Property";
	 	} else {
	 		propertySelected.innerHTML = address;
	 	}
	 	
	 	filtered = sortWithAllMethods(filtered, sortSelected.innerHTML, landlordSelected.innerHTML, propertySelected.innerHTML, $search.val());
	 	updateReviewsList(filtered);
	 }
	 
	 $search.keyup(e => {
	 	filtered = reviewsList;
	 	filtered = sortWithAllMethods(filtered, sortSelected.innerHTML, landlordSelected.innerHTML, propertySelected.innerHTML, $search.val());
	 	console.log(filtered);
	 	updateReviewsList(filtered);
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
	const str = "<div class=\"landlord\">"
			+ "<img src=\"../images/landlordicon.png\" alt=\"landlord icon\" class=\"landlord-icon\">"
			+ "<div class=\"landlord-content-wrapper\">"
			+ "<h3>" + landlordName + "</h3>"
			+ "<div class=\"rating\">" + stars + "</div>"
			+ "<img src=\"../images/quotation.png\" alt=\"opening quotation mark\" class=\"quotation\">"
			+ "<div class=\"comment-wrapper\">"
			+ "<a>" + review + "</a>"
			+ "</div></div></div>"
	return str;
}

