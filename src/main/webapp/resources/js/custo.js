$(document).ready(function() {
    var inputBoxes = $(".ingreName.form-control");
    var suggestionsBoxes = $(".suggestions");
    console.log("asdasd")
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
    var model = ${food};
    var index = model.ingredientRequests.size();
    console.log(index);
    $("#addIngredientBtn").click(function() {
        var row = $("<tr/>");
        row.append($("<td/>").html('<input type="text" autocomplete="off" class="ingreName form-control" name="ingredientRequests[' + index + '].name"><ul class="suggestions"></ul>'));
        row.append($("<td/>").html('<input type="number" class="form-control" name="ingredientRequests[' + index + '].amount" min="0" step="0.01">'));
        row.append($("<td/>").html('<select class="form-control" name="ingredientRequests[' + index + '].measure"><option value="1">g</option><option value="2">thìa cà phê</option><option value="3">thìa canh</option></select>'));
        row.append($("<td/>").html('<button type="button" class="btn btn-danger removeIngredientBtn">Xóa</button>'));
        $("#ingredientsTable tbody").append(row);
        index++;
    });
    $(document).on("click", ".removeIngredientBtn", function() {
        $(this).closest("tr").remove();
        index--;
    });
});
