
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib  uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!-- Google Font -->
<link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
      rel="stylesheet">
<!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">-->
<link href="<c:url value="/resources/css/header.css" />" rel="stylesheet">
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<!-- Header Section Begin -->
<header style="position:fixed;width:100%;z-index: 100000;">

 <div class="header" >
          <div class="sysbar">
              <a href="/project-final/home">COOKING SPY</a>

               <form:form action="./search" method="get">
                  <input type="text" name="searchInput"/>
                  <button class="header-button" type="submit"><span class="icon"><ion-icon name="search-sharp"></ion-icon></span></button>
                </form:form>

              <sec:authorize access="isAuthenticated()">
                  <sec:authorize access="hasRole('ROLE_ADMIN')">
                      <a href="<c:url value="/createForm" />"style="font-size:medium;flex-grow: 0.1;">MÓN MỚI</a>
                      <a href="<c:url value="/listFood" />"style="font-size:medium;flex-grow: 0.1;">DANH SÁCH MÓN</a>
                  </sec:authorize>
                  <sec:authorize access="hasRole('ROLE_USER')">
                        <a href="<c:url value="/admin/home" />"style="font-size:medium;flex-grow: 0.1;">THỰC ĐƠN HÔM NAY</a>
                        <a href="<c:url value="/admin/home" />"style="font-size:medium;flex-grow: 0.1;">THỰC ĐƠN 1 TUẦN</a>
                    </sec:authorize>
                  <a href="<c:url value="/logout" />"style="font-size:medium;">ĐĂNG XUẤT</a>
              </sec:authorize>

              <!-- If No login then will show Login Page -->
              <sec:authorize access="!isAuthenticated()">
                  <a href="" style="font-size:medium;flex-grow: 0.1;"></a>
                  <a href="" style="font-size:medium;flex-grow: 0.1;"></a>
                  <a href="<c:url value="/login" />" style="font-size:medium;">ĐĂNG NHẬP</a>
              </sec:authorize>

          </div>


          <div class="navbar">
              <ul class="nav">
                  <li class="nav-item"><a class="nav-link active" href="/project-final/filterCelluloza">GIÀU CHẤT XƠ</a></li>
                  <li class="nav-item"><a class="nav-link active" href="/project-final/filterProtein">GIÀU ĐẠM</a></li>
                  <li class="nav-item"><a class="nav-link active" href="/project-final/filterLipid">GIÀU CHẤT BÉO</a></li>
                  <li class="nav-item"><a class="nav-link active" href="/project-final/filterGlucid">GIÀU ĐƯỜNG BỘT</a></li>
                  <li class="nav-item"><a class="nav-link active" href="/project-final/filterCalo">MÓN GIÀU NĂNG LƯỢNG</a></li>
                  <li class="nav-item"><a class="nav-link active" href="/project-final/filterWater">MÓN NƯỚC</a></li>

              </ul>
          </div>
      </div>

</header>
<!-- Header Section End -->