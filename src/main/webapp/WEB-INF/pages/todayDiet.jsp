<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <link href="<c:url value="/resources/css/today-diet.css" />" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/details.js" />"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp" />
<div class="foodinfo-calculator-envaluate-box">
        <div class="foodinfo">
            <div class="name">THỰC ĐƠN HÔM NAY</div>
        </div>
        <div class="calculator">
            <div class="food-list">
                <div class="food">
                    <div class="food-img"
                    style="background-image: url(https://iamafoodblog.b-cdn.net/wp-content/uploads/2021/05/pesto-eggs-3650w-2048x1366.webp);background-position:center;background-size:cover;background-repeat: no-repeat;">
                    <div class="image"></div>
                    </div>
                    <div class="info">
                        <div class="name">Trứng chiên</div>
                        <div class="coeff">
                            <div class="title">Hệ số:</div>
                            <input type="number" max="2" min="0.1" step="0.1" value="1" style="display: block;">
                        </div>
                    </div>
                    <button class="del-btn">
                        <ion-icon name="close-circle"></ion-icon>
                    </button>
                </div>


            </div>

            <div class="ingredient-list">
                <div class="list-name">Nguyên liệu</div>
                <div class="list">
                     <c:forEach items="${food.foodsIngredientsEntityList}" var="foodIngre">
                        <div class="ingre">
                            <div class="ingre-name">${foodIngre.ingredient.name}</div>
                            <div class="amount-measure">
                                <span style="font-weight:600">${foodIngre.amount}</span>
                                <span>${foodIngre.measure}</span>
                            </div>

                        </div>
                     </c:forEach>

                </div>
                  <sec:authorize access="isAuthenticated()">
                      <sec:authorize access="hasRole('ROLE_ADMIN')">
                          <a href="<c:url value="/deleteFood/${food.id}" />"class="del-btn">XÓA MÓN</a>
                          <a href="<c:url value="/updateFood/${food.id}" />"class="del-btn">SỬA MÓN</a>
                      </sec:authorize>
                   </sec:authorize>
            </div>
            <div class="calculator-tutorial-container">
                <div class="section-btn-box">
                    <button onclick="currentSlide(1)" class="section-btn">
                        <div class="table-name" >Hàm lượng dinh dưỡng đáp ứng
                            <p class="cap">Dựa trên tiêu chuẩn 1 ngày của bạn</p>
                        </div>
                    </button>
                    <button onclick="currentSlide(2)"class="section-btn">Hướng dẫn chế biến</button>
                </div>
                <div class="sliding-sections">
                    <div class="section left">
                        <div class="nutrition-list">
                            <div class="nutrition">
                                <div class="nutri-name">Năng Lượng</div>
                                <div class="nutri-amount">
                                    <div class="amount-bar" style="background: linear-gradient(#DA4453, #fc6767); width:${food.getCalorieSummary()/user.getAmr()*100}%">
                                        <span class="amount"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="nutrition">
                                <div class="nutri-name">Bột Đường</div>
                                <div class="nutri-amount">
                                    <div class="amount-bar" style="background: linear-gradient(#D66D75, #E29587); width:${food.getGlucidSummary()/user.getGlucidNeeded()*100}%">
                                        <span class="amount"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="nutrition">
                                <div class="nutri-name">Đạm</div>
                                <div class="nutri-amount">
                                    <div class="amount-bar" style="background: linear-gradient(#ff7e5f, #eea849); width:${food.getProteinSummary()/user.getProteinNeeded()*100}%">
                                        <span class="amount"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="nutrition">
                                <div class="nutri-name">Chất Béo</div>
                                <div class="nutri-amount">
                                    <div class="amount-bar" style="background: linear-gradient(#ffb347, #ffcc33); width:${food.getLipidSummary()/user.getLipidNeeded()*100}%">
                                        <span class="amount"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="nutrition">
                                <div class="nutri-name">Chất Xơ</div>
                                <div class="nutri-amount">
                                    <div class="amount-bar" style="background: linear-gradient(#56ab2f, #45B649); width:${food.getCellulozaSummary()/user.getCellulozaNeeded()}%">
                                        <span class="amount"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="nutrition">
                                <div class="nutri-name">Nước</div>
                                <div class="nutri-amount">
                                    <div class="amount-bar" style="background: linear-gradient(#2980B9, #1488CC); width:${food.getWaterSummary()/user.getWaterNeeded()*100}%">
                                        <span class="amount"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="section right">
                        <div class="tutorial" style="max-width:900px;min-height:470px;max-height: 470px;overflow-y: scroll;">
                            <p>
                                ${food.tutorial}
                            </p>
                        </div>
                    </div>

                </div>

            </div>

        </div>


    </div>
</body>
</html>