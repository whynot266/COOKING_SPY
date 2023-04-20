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
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</head>
<body>
<jsp:include page="header.jsp" />
<div class="foodinfo-calculator-envaluate-box">
        <div class="foodinfo">
            <div class="name">THỰC ĐƠN HÔM NAY</div>
            <div class="user-list">
                <div style="font-size:small;font-weight:900;">THÀNH VIÊN: </div>
                <c:forEach items="${todayDiet.todayDietUsersEntityList}" var="dietUser">
                    <div class="user">
                            <a class="remove-user-btn" style="align-self:flex-end;font-size:0.9rem;position:absolute;" href="./removeUser/${dietUser.user.id}"><ion-icon name="remove-circle"></ion-icon></a>
                            <div class="icon"><ion-icon name="person"></ion-icon></div>
                            <div class="name">${dietUser.user.account.email}</div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="calculator">
            <div class="food-list">
                <c:forEach var="dietFood" items="${todayDiet.todayDietFoodsEntityList}">
                    <div class="food">
                        <a href="./details/${dietFood.food.id}">
                            <div class="image">
                                <img src="${dietFood.food.image}" alt="" >
                            </div>
                            <div class="info">
                                <div class="name">${dietFood.food.name}</div>
                                <div class="quantity">
                                    <form action="./decrease" method="post">
                                        <input name="foodId" type="text" value="${dietFood.food.id}"
                                               style="display: none;">
                                        <button class="decrease">
                                            <ion-icon name="caret-back-outline"></ion-icon>
                                        </button>
                                    </form>
                                    <input class="coeff" type="number" disabled role="spinbutton"
                                           value="${dietFood.coefficient}">
                                    <form action="./increase" method="post">
                                        <input name="foodId" type="text" value="${dietFood.food.id}"
                                               style="display: none;">
                                        <button class="increase">
                                            <ion-icon name="caret-forward-outline"></ion-icon>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </a>
                        <a class="remove-btn" href="/project-final/removeFood?id=${dietFood.food.id}">
                                                        <ion-icon name="close-circle"></ion-icon>
                                                    </a>
                    </div>
                </c:forEach>
            </div>
            <div class="ingredient-list">
                <div class="list-name">Tổng nguyên liệu</div>
                <div class="list">
                     <c:forEach items="${ingreList}" var="foodIngre">
                        <div class="ingre">
                            <div class="ingre-name">${foodIngre.name}</div>
                            <div class="amount-measure">
                                <span style="font-weight:600">${foodIngre.amount}</span>
                                <span>${foodIngre.measureName}</span>
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
                </div>
                <div class="sliding-sections">
                    <div class="section left">
                        <div class="nutrition-list">
                            <div class="nutrition" onmouseover="showPopup(this)" onmouseout="hidePopup(this)">
                                <div class="nutri-name">Năng Lượng</div>
                                <div class="nutri-amount">
                                    <div class="amount-bar" style="background: linear-gradient(#DA4453, #fc6767); width:${todayDiet.getCalorieSummary()/todayDiet.getAmrSummary()*100}%">
                                        <span class="amount"></span>
                                    </div>
                                </div>
                                <div class="popup">${todayDiet.getCalorieSummary()}/${todayDiet.getAmrSummary()}</div>
                            </div>
                            <div class="nutrition" onmouseover="showPopup(this)" onmouseout="hidePopup(this)">
                                <div class="nutri-name">Bột Đường</div>
                                <div class="nutri-amount">
                                    <div class="amount-bar" style="background: linear-gradient(#D66D75, #E29587); width:${todayDiet.getGlucidSummary()/todayDiet.getGlucidNeededSummary()*100}%">
                                        <span class="amount"></span>
                                    </div>
                                </div>
                                <div class="popup">${todayDiet.getGlucidSummary()}/${todayDiet.getGlucidNeededSummary()}</div>
                            </div>
                            <div class="nutrition" onmouseover="showPopup(this)" onmouseout="hidePopup(this)">
                                <div class="nutri-name">Đạm</div>
                                <div class="nutri-amount">
                                    <div class="amount-bar" style="background: linear-gradient(#ff7e5f, #eea849); width:${todayDiet.getProteinSummary()/todayDiet.getProteinNeededSummary()*100}%">
                                        <span class="amount"></span>
                                    </div>
                                </div>
                                <div class="popup">${todayDiet.getProteinSummary()}/${todayDiet.getProteinNeededSummary()}</div>
                            </div>
                            <div class="nutrition" onmouseover="showPopup(this)" onmouseout="hidePopup(this)">
                                <div class="nutri-name">Chất Béo</div>
                                <div class="nutri-amount">
                                    <div class="amount-bar" style="background: linear-gradient(#ffb347, #ffcc33); width:${todayDiet.getLipidSummary()/todayDiet.getLipidNeededSummary()*100}%">
                                        <span class="amount"></span>
                                    </div>
                                </div>
                                <div class="popup">${todayDiet.getLipidSummary()}/${todayDiet.getLipidNeededSummary()}</div>
                            </div>
                            <div class="nutrition" onmouseover="showPopup(this)" onmouseout="hidePopup(this)">
                                <div class="nutri-name">Chất Xơ</div>
                                <div class="nutri-amount">
                                    <div class="amount-bar" style="background: linear-gradient(#56ab2f, #45B649); width:${todayDiet.getCellulozaSummary()/todayDiet.getCellulozaNeededSummary()*100}%">
                                        <span class="amount"></span>
                                    </div>
                                </div>
                                <div class="popup">${todayDiet.getCellulozaSummary()}/${todayDiet.getCellulozaNeededSummary()}</div>
                            </div>
                            <div class="nutrition" onmouseover="showPopup(this)" onmouseout="hidePopup(this)">
                                <div class="nutri-name">Nước</div>
                                <div class="nutri-amount">
                                    <div class="amount-bar" style="background: linear-gradient(#2980B9, #1488CC); width:${todayDiet.getWaterSummary()/todayDiet.getWaterNeededSummary()*100}%">
                                        <span class="amount"></span>
                                    </div>
                                </div>
                                <div class="popup">${todayDiet.getWaterSummary()}/${todayDiet.getWaterNeededSummary()}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>