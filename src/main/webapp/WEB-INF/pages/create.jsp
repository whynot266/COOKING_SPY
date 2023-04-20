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
    <script type="text/javascript" src="resources/js/custom.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
   <div class="container">
                <a href="/project-final/home">COOKING SPY</a>
                <h1>TẠO MÓN ĂN MỚI</h1>
                <mvc:form id="createFoodForm" method="post" action="./createFood" modelAttribute="foodRequest" accept-charset="UTF-8" >
                    <div class="form-group">
                        <label for="name">Tên món</label>
                        <mvc:input type="text" class="form-control" id="name" path="name" />
                    </div>
                    <div class="form-group">
                        <label for="image">Hình ảnh</label>
                        <mvc:input type="text" class="form-control" id="image" path="image" />
                    </div>
                     <div class="form-group">
                        <label for="des">Mô tả ngắn</label>
                        <mvc:input type="text" class="form-control" id="des" path="description" />
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
                                <tr>
                                    <td>
                                        <mvc:input class="ingreName form-control" autocomplete="off" path="ingredientRequests[0].name" type="text"/>
                                        <ul class="suggestions"></ul>
                                    </td>
                                    <td>
                                        <mvc:input type="number" class="form-control" path="ingredientRequests[0].amount" min="0.1" step="0.01"/>
                                    </td>
                                    <td>
                                        <form:select class="form-control measure" path="ingredientRequests[0].measure">
                                            <option value="1">g</option>
                                            <option value="2">thìa cà phê</option>
                                            <option value="3">thìa canh</option>
                                        </form:select>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger removeIngredientBtn">Xóa</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <button type="button" id="addIngredientBtn" class="btn btn-primary">Thêm nguyên liệu</button>
                    </div>
                    <div class="form-group">
                        <label for="labels">Nhãn:</label>
                        <div class="checkbox-list" style="display:flex;flex-wrap:wrap;column-gap:15px;row-gap:25px;align-items:center;">
                            <form:checkboxes path="labelRequests" items="${allLabels}" cssClass="custom-checkbox" />
                        </div>
                    </div>
                     <div class="form-group">
                         <label for="inputText">Quy trình nấu:</label>
                         <mvc:textarea class="form-control" id="inputText" path="tutorial" rows="5"></mvc:textarea>
                     </div>
                    <button type="submit" class="btn btn-success">Tạo</button>
                </mvc:form>
   </div>
</body>
</html>