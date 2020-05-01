$(document).ready(() => {
const $suggestionsSrc = $("#suggestionsSrc");
            $("#src").keyup(event => {
                    const postParameters = {
                        src: $("#src").val()
                    };
                    console.log("src: " + postParameters.src);
                    console.log("before post");
                    $.post("/baconSuggestions", postParameters, responseJSON => {
                            $suggestionsSrc.empty();
                            const response = JSON.parse(responseJSON);
                            console.log(postParameters.src + " " + response.suggestionsSrc);
                            for (let i = 0, len = response.suggestionsSrc.length; i < len; i++) {
                                $suggestionsSrc.append("<li>" + response.suggestionsSrc[i] + "</li>");
                            };
                    });
            });
const $suggestionsDst = $("#suggestionsDst");
            $("#dst").keyup(event => {
                    const postParameters = {
                        dst: $("#dst").val()
                    };
                    console.log(postParameters.dst);
                    $.post("/baconSuggestions", postParameters, responseJSON => {
                            $suggestionsDst.empty();
                            const response = JSON.parse(responseJSON);
                            console.log("hereeee");
                            console.log(postParameters.dst + " " + response.suggestionsDst);
                            for (let i = 0, len = response.suggestionsDst.length; i < len; i++) {
                                $suggestionsDst.append("<li>" + response.suggestionsDst[i] + "</li>");
                            };
                    });
            });
     });
