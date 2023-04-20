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
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
    $(document).ready(function(){
        $('#filter-menu').click(function(){
            $('.navbar').toggleClass('show');
            $('#filter-menu').toggleClass('show');
        });
        $('#user-btn').click(function(){
            $('.navbar2').toggleClass('show');
            $('#user-btn').toggleClass('show');
        });
    });
</script>
<!-- Header Section Begin -->
<header style="position:fixed;width:100%;z-index: 100000;">
 <div class="header" >
          <div class="sysbar">
              <button id="filter-menu"><ion-icon name="menu"></ion-icon></button>
              <a href="/project-final/homeIndex" id="home-icon">COOKING SPY</a>
              <div class="user-bar">
                  <sec:authorize access="isAuthenticated()">
                      <sec:authorize access="hasRole('ROLE_ADMIN')">
                          <a class="tab" href="<c:url value="/createForm" />"style="font-size:medium;flex-grow: 0.1;">MÓN MỚI</a>
                          <a class="tab" href="<c:url value="/userList" />"style="font-size:medium;flex-grow: 0.1;">DANH SÁCH NGƯỜI DÙNG</a>
                      </sec:authorize>
                      <sec:authorize access="hasRole('ROLE_USER')">
                            <a id="today-diet" href="<c:url value="/todayDiet" />"><ion-icon name="restaurant"></ion-icon><span id="diet-count">${sessionScope.dietCount}</span></a>
                        </sec:authorize>
                      <button id="user-btn"><ion-icon name="person-circle"></ion-icon><br><span style="font-size:small;max-width:3vw;overflow:hidden;"><sec:authentication property="principal.username"/></span></button>
                  </sec:authorize>
              </div>
          </div>
      </div>
      <div class="navbar">
            <ul>
                <li class="nav-item" ><form:form action="/project-final/search" method="get" id="search-bar">

                   <button class="header-button" type="submit"><span class="icon"><ion-icon name="search-sharp"></ion-icon></span></button>
                   <input style="width:9vw" type="text" name="searchInput"/>
                 </form:form></li>
                <li class="nav-item"><a class="nav-link active" href="/project-final/filter/Celluloza">GIÀU CHẤT XƠ</a></li>
                <li class="nav-item"><a class="nav-link active" href="/project-final/filter/Protein">GIÀU ĐẠM</a></li>
                <li class="nav-item"><a class="nav-link active" href="/project-final/filter/Lipid">GIÀU CHẤT BÉO</a></li>
                <li class="nav-item"><a class="nav-link active" href="/project-final/filter/Glucid">GIÀU ĐƯỜNG BỘT</a></li>
                <li class="nav-item"><a class="nav-link active" href="/project-final/filter/Calo">MÓN GIÀU NĂNG LƯỢNG</a></li>
                <li class="nav-item"><a class="nav-link active" href="/project-final/filter/Water">MÓN NƯỚC</a></li>
            </ul>
        </div>
      <div class="navbar2">
          <ul>

                <sec:authorize access="hasRole('ROLE_USER')">
                      <li class="nav-item"><a class="nav-link active" href="/project-final/updateUserInfoForm"><ion-icon name="create"></ion-icon> CHỈNH SỬA TTCN</a></li>
                      <li class="nav-item"><a class="nav-link active" href="/project-final/friendList?searchInput=''"><ion-icon name="people"></ion-icon> BẠN BÈ</a></li>
                </sec:authorize>

              <li class="nav-item"><a class="nav-link active" href="<c:url value="/logout" />"><ion-icon name="arrow-undo-circle"></ion-icon> ĐĂNG XUẤT</a></li>
          </ul>
      </div>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</header>
<!-- Header Section End -->