$(document).ready(function() {
    var inputBoxes = $(".ingreName.form-control");
    var suggestionsBoxes = $(".suggestions");

    // Handle keyup event on input boxes
    $(document).on("keyup", ".ingreName.form-control", function() {

        var inputBox = $(this);
        var suggestionsBox = inputBox.next('.suggestions');

        var query = inputBox.val();
        if (query.length >= 2) {
            // Make an AJAX call to the server to get the suggestion list
            $.ajax({
                type: "GET",
                url: "/project-final/autocomplete",
                data: {
                    query: query
                },
                success: function(data) {
                    var suggestions = "";
                    $.each(data, function(index, value) {
                        suggestions += "<li>" + value + "</li>";
                    });
                    suggestionsBox.html(suggestions);
                    suggestionsBox.show();
                }
            });
        } else {
            suggestionsBox.hide();
        }
    });

    // Handle click event on suggestions list
    $(document).on("click", ".suggestions li", function() {
        var value = $(this).text();
        console.log(value);

        var inputBox = $(this).closest("td").find('.ingreName.form-control');
        console.log(inputBox);
        inputBox.val(value);
        $(this).closest('.suggestions').hide();
    });

    // Handle blur event on input boxes
    $(document).on("blur", ".ingreName.form-control", function() {
        var suggestionsBox = $(this).next('.suggestions');
        setTimeout(function() {
            suggestionsBox.hide();
        }, 200);
    });

    // Define a variable to keep track of the current index
    var index = 1;

    // Attach a click event handler to the "Add Ingredient" button
    $("#addIngredientBtn").click(function() {

        // Create a new <tr> element
        var row = $("<tr/>");

        // Copy the contents of the initial <tr> element and append them to the new <tr> element
        var initialRow = $("#ingredientsTable tbody tr:first-child").clone();
        row.html(initialRow.html());

        // Update the "path" and "name" attributes of the <input> and <select> elements in the new <tr> element
        row.find(".ingreName").attr({
            "path": "ingredientRequests[" + index + "].name",
            "name": "ingredientRequests[" + index + "].name"
        });
        row.find("input[type='number']").attr({
            "path": "ingredientRequests[" + index + "].amount",
            "name": "ingredientRequests[" + index + "].amount"
        });
        row.find(".measure").attr({
            "path": "ingredientRequests[" + index + "].measure",
            "name": "ingredientRequests[" + index + "].measure"
        });

        // Append the new <tr> element to the <tbody> of the table
        $("#ingredientsTable tbody").append(row);

        // Increment the index for the next row
        index++;

    });
     $(document).on("click", ".removeIngredientBtn", function() {
        $(this).closest("tr").remove();
        index--;

     });





});
