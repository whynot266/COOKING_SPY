
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
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
        <link rel="stylesheet" href="<c:url value="/resources/css/registration.css"/>">
    </head>
    <body>

        <div class="header" >
               <div class="sysbar">
                   <a href="">COOKING SPY</a>
               </div>
           </div>
           <div class="container">
               <mvc:form id="registrationForm" method="post" action="./register" modelAttribute="userRequest" accept-charset="UTF-8" class="card">
                   <h2 style="align-self: center;">ĐĂNG KÝ</h2>
                       <div class="form-group">
                           <label for="name">Email</label>
                           <mvc:input type="email" class="form-control" id="name" path="accountRequest.email" />
                       </div>
                       <div class="form-group">
                           <label for="image">Mật khẩu</label>
                           <input type="password" class="form-control" id="password" name="accountRequest.password" required>
                       </div>
                       <div class="form-group">
                           <label for="des">Tuổi</label>
                           <input type="number" class="form-control" min="1" step="1" name="age" style="max-width:100px;"required>
                           <label for="des">Giới tính</label>
                           <select class="form-control" name="gender" required>
                               <option value="1">Nam</option>
                               <option value="2">Nữ</option>
                           </select>
                           <label for="des">Trạng thái</label>
                           <select class="form-control" name="status" required>
                               <option value="1">Bình thường</option>
                               <option value="2">Muốn tăng cân</option>
                               <option value="3">Muốn giảm cân</option>
                           </select>
                       </div>
                       <div class="form-group">
                           <label for="des">Chiều cao(cm)</label>
                           <input type="number" class="form-control" min="0" step="0.01" name="height" required>
                           <label for="des">Cân nặng(kg)</label>
                           <input type="number" class="form-control" min="5" step="0.1" name="weight"required>
                       </div>

                       <div class="form-group">
                           <label for="des">Mức độ hoạt động</label>
                           <select class="form-control" name="activityLevel" required>
                               <option value="1">Người ít vận động (làm việc văn phòng, chỉ ăn và ngủ)</option>
                               <option value="2">Người vận động nhẹ (luyện tập thể dục 1 – 3 lần/tuần)</option>
                               <option value="3">Người vận động vừa (tập luyện 3 – 5 lần/tuần, vận động mỗi ngày)</option>
                               <option value="4">Người vận động nặng (thường xuyên vận động , chơi thể dục thể thao và tập luyện 6 – 7 lần/tuần)</option>
                               <option value="5">Người vận động rất nặng (tập luyện thể dục 2 lần/ngày, lao động phổ thông)</option>
                           </select>

                       </div>
                       <div class="form-group">


                       </div>
                    <input type="submit" value="Đăng kí" style="width: 200px; padding:10px 20px 10px 20px;align-self: center; color:#fff;background-color: black;font-size: 1rem;">
               </mvc:form>

           </div>
       
    </body>
</html>
