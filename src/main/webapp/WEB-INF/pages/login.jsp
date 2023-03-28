
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Male_Fashion Template">
        <meta name="keywords" content="Male_Fashion, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Login Page</title>
        <link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>">    
    </head>
    <body>
       <div class="header" >
           <div class="sysbar">
               <a href="">COOKING SPY</a>
           </div>
       </div>
        <div class="container">
                 <!-- /login?error=true -->
                  <div class="textbox">
                        <c:if test="${message != null && message != ''}">
                            <p style="color: red;">${message}</p>
                        </c:if>
                   </div>
                <form action="<c:url value="j_spring_security_check"/>" method="post" class="card">
                    <h2 style="align-self: center;">ĐĂNG NHẬP</h2>

                    <div class="form-group">

                        <label for="name">Email</label>
                        <input type="email" name="username" placeholder="Username">
                    </div>
                    <div class="form-group">
                        <label for="image">Mật khẩu</label>
                        <input type="password" name="password"placeholder="Password">
                    </div>
                    <div class="res">
                        <span>Chưa có tài khoản?</span>
                        <a href="./registrationForm">Tạo tài khoản</a>
                    </div>
                    <input type="submit" value="Đăng nhập" style="width: 200px; padding:10px 20px 10px 20px;align-self: center; color:#fff;background-color: black;font-size: 1rem;">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>



            </div>

       
    </body>
</html>
