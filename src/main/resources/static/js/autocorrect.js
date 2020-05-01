$(document).ready(() => {
const $suggestions = $("#suggestions");
            $("#auto").keyup(event => {
                    const postParameters = {
                        auto: $("#auto").val()
                    };
                    console.log(postParameters.auto);
                    $.post("/suggestions", postParameters, responseJSON => {
                            $suggestions.empty();
                            const response = JSON.parse(responseJSON);
                            console.log(postParameters.auto + " " + response.suggestions);
                            for (let i = 0, len = response.suggestions.length; i < len; i++) {
                                $suggestions.append("<li>" + response.suggestions[i] + "</li>");
                            };
                    });
            });
     });
