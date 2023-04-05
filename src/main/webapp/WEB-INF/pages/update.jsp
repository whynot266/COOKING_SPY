<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
    <link href="<c:url value="/resources/css/create.css" />" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
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
            var index = "${food.ingredientRequests.size()}";

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
                    "name": "ingredientRequests[" + index + "].name",
                    "value": ""
                });
                row.find("input[type='number']").attr({
                    "path": "ingredientRequests[" + index + "].amount",
                    "name": "ingredientRequests[" + index + "].amount",
                    "value": "0.1"
                });
                row.find(".measure").attr({
                    "path": "ingredientRequests[" + index + "].measure",
                    "name": "ingredientRequests[" + index + "].measure",
                    "value": "1"
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
                 var oldLabels = [];
                // Loop through the old labels of the food
                <c:forEach var="label" items="${food.labelRequests}">
                    oldLabels.push("${label.name}");
                </c:forEach>

                // Loop through all checkboxes and pre-check any that match old labels
                var checkboxes = document.querySelectorAll('.custom-checkbox');
                checkboxes.forEach(function(checkbox) {
                    if (oldLabels.includes(checkbox.value)) {
                        console.log(checkbox.value);
                        checkbox.checked = true;
                    }
                });
                $('.custom-checkbox input[type="checkbox"]').click(function() {
                    if ($(this).is(':checked')) {
                      $(this).closest('label').css('background-color', 'blue');
                    } else {
                      $(this).closest('label').css('background-color', '');
                    }
                  });





        });


    </script>
</head>
<body>
   <div class="container">

    <h1>CHỈNH SỬA MÓN ĂN</h1>
    <mvc:form id="updateFoodForm" method="post" action="./update-process" modelAttribute="food" accept-charset="UTF-8">
        <mvc:input type="hidden" path="id" value="${id}"/>
        <div class="form-group">
            <label for="name">Tên món</label>
            <mvc:input type="text" class="form-control" id="name" path="name" value="${food.name}"/>
        </div>
        <div class="form-group">
            <label for="image">Hình ảnh</label>
            <mvc:input type="text" class="form-control" id="image" path="image" value="${food.image}"/>
        </div>
         <div class="form-group">
            <label for="des">Mô tả ngắn</label>
            <mvc:input type="text" class="form-control" id="des" path="description" value="${food.description}"/>
        </div>
        <div class="form-group">
            <label for="ingredients">Nguyên liệu:</label>
            <table id="ingredientsTable" class="table">
                <thead>
                    <tr>
                        <th>Tên nguyên lệu</th>
                        <th>Số lượng</th>
                        <th>Đơn vị</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>

                      <c:forEach var = "i" begin = '0' end = '${food.ingredientRequests.size()-1}'>
                             <tr>
                                <td>
                                    <mvc:input class="ingreName form-control" autocomplete="off" path="ingredientRequests[${i}].name" type="text" />
                                     <ul class="suggestions"></ul>
                                </td>
                                <td>
                                    <mvc:input type="number" class="form-control" path="ingredientRequests[${i}].amount" min="0.1" step="0.01" />
                                </td>
                                <td>
                                    <mvc:select class="form-control measure" path="ingredientRequests[${i}].measure">
                                        <option value="1">g</option>
                                        <option value="2">thìa cà phê</option>
                                        <option value="3">thìa canh</option>
                                    </mvc:select>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-danger removeIngredientBtn">Xóa</button>
                                </td>
                            </tr>
                      </c:forEach>

                </tbody>
            </table>
            <button type="button" id="addIngredientBtn" class="btn btn-primary">Thêm nguyên liệu</button>
        </div>
        <div class="form-group">
            <label for="labels">Nhãn:</label>
            <div class="checkbox-list" style="display:flex;flex-wrap:wrap;column-gap:15px;row-gap:25px;align-items:center;">
                <form:checkboxes path="labelRequests" items="${allLabels}" cssClass="custom-checkbox"/>
            </div>
        </div>

         <div class="form-group">
             <label for="inputText">Quy trình nấu:</label>
             <mvc:textarea class="form-control" id="inputText" path="tutorial" rows="5" value="${tutorial}"></mvc:textarea>
         </div>
        <button type="submit" class="btn btn-success">HOÀN TẤT CHỈNH SỬA</button>
    </mvc:form>


 </div>

</body>
</html>