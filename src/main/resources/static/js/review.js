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
            console.log(response.landlords[i]);
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
    };


    $property_selector.click(function() {
        let landlordVal = $landlord_selector.text();
        console.log("Selected landlord: " + landlordVal);
        console.log(properties_list);
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




