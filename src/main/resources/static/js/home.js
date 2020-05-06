$(document).ready(() => {
	const $reviews = $("#reviews");
	$.post("/home/reviews", responseJSON => {
		const response = JSON.parse(responseJSON);
		for (let i = 0, len = response.reviews.length; i < len; i++) {
			const review = response.reviews[i]
			const str = "<div class=\"landlord\">"
			+ "<img src=\"../images/landlordicon.png\" alt=\"landlord icon\" class=\"landlord-icon\">"
			+ "<div class=\"landlord-content-wrapper\">"
			+ "<h3>" + review[8] + " " + review[9] + "</h3>"
			+ "<img src=\"../images/quotation.png\" alt=\"opening quotation mark\" class=\"quotation\">"
			+ "<div class=\"comment-wrapper\">"
			+ "<a>" + review[5] + "</a>"
			+ "</div></div></div>"
			$reviews.append(str);
		};
	 });
});
