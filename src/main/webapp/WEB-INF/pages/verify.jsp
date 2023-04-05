
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
        <link rel="stylesheet" href="<c:url value="/resources/css/verify.css"/>">
    </head>
    <body>
       <div class="header" >
           <div class="sysbar">
               <a href="">COOKING SPY</a>
           </div>
       </div>
        <div class="container">

                 <div><h1>XÁC THỰC EMAIL</h1></div>
                 <div style="display:flex;flex-direction:column;"><div style:"text-align:center;font-size:medium;">Một mã xác thực đã được gửi đến địa chỉ email:</div>
                    <div style="text-align:center;color:blue;">${email}</div>
                 </div>
                 <div><p style="font-size:medium;">Vui lòng kiểm tra hòm thư và điền mã xác thực bạn nhận được vào khung bên dưới!</p></div>
                 <div><form action="./verify" method="post">
                     <input type="hidden" name="email" value="${email}">
                     <label for="verificationCode">Mã xác thực:</label>
                     <input style="height:50px; font-size:1.6rem;" type="text" name="verificationCode" id="verificationCode" required>
                     <br>
                     <button type="submit">Xác thực</button>
                 </form>
                  </div>



        </div>

       
    </body>
</html>
