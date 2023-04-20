<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <jsp:include page="header.jsp" />
    <div class="body-wrapper" style="padding-top:10vw;">
            <h2 style="text-align:center">DANH SÁCH NGƯỜI DÙNG</h2>
            <table class="table caption-top">
              <thead>
                <tr>
                  <th scope="col">Email</th>
                 <th scope="col">Thao tác</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${accountList}" var="account">
                    <tr>
                      <td>${account.email}</td>
                      <td>
                        <div style="display:flex;">
                             <div class="green-text-button" style="margin-right:30px; padding: 1px 5px 1px 5px;"><a style="text-decoration:none;color:green;" href="./deleteAccount/${account.email}">Xóa</a></div>
                        </div>
                      </td>
                    </tr>
                </c:forEach>
              </tbody>
            </table>
            <script src="<c:url value="resources/js/script.js"/>"></script>
    </div>
</body>
</html>