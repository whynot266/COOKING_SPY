
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link rel="stylesheet" href="<c:url value="/resources/css/friendList.css"/>">
        <script>
             $(document).ready(function(){
                $('.flip-btn').click(function(){
                    console.log("asd")
                   $('.flip-card-inner').toggleClass('flip');

                });
                $('.add-friend-btn').click(function() {

                    var button = $(this);
                    var friendId = button.data('user-id');
                    console.log(friendId);
                    button.addClass('clicked')
                    button.prop('disabled', true);
                    $.ajax({
                      url: '/project-final/sendInvitation',
                      method: 'POST',
                      data: { id: friendId },
                      success: function(response) {

                      },

                    });
                });

             });

            let slideIndex = 1;
            showSlides(slideIndex);


            // Thumbnail image controls
            function currentSlide(n) {
                showSlides(slideIndex = n);
            }

            function showSlides(n) {
                let i;
                let slides = document.getElementsByClassName("section");
                let buttons = document.getElementsByClassName("section-btn");
                if (n > slides.length) { slideIndex = 1 }
                if (n < 1) { slideIndex = slides.length }
                for (i = 0; i < slides.length; i++) {
                    slides[i].style.display = "none";

                }

                slides[slideIndex - 1].style.display = "block";

            }




        </script>
    </head>
    <body>

        <div class="header" >
               <div class="sysbar">
                   <a href="/project-final/">COOKING SPY</a>
               </div>
           </div>
        <div class="container">
            <div class="flip-card-inner">
                <div class="flip-card-front">
                    <div><form:form action="./friendList" method="get" id="search-bar">
                                                <input type="text" name="searchInput"/>
                                                <button class="header-button" type="submit">TÌM</button>
                                              </form:form></div>


                                         <div class="find-list">
                                             <div style="overflow-y: scroll;">
                                                   <table class="table caption-top">
                                                        <thead>
                                                          <tr>
                                                            <th scope="col">Email</th>
                                                           <th scope="col">Thao tác</th>
                                                          </tr>
                                                        </thead>
                                                        <tbody>
                                                          <c:forEach items="${listOfAccounts}" var="account">
                                                              <tr>
                                                                <td>${account.email}</td>
                                                                <td>

                                                                   <button class="add-friend-btn" data-user-id="${account.id}">Add Friend</button>

                                                                </td>
                                                              </tr>
                                                          </c:forEach>
                                                        </tbody>
                                                     </table>

                                             </div>


                                         </div>
                     <div><button class="flip-btn" style="width:100%;border:none;background: lightblue;">Xem danh sách bạn bè</button></div>
                </div>
                <div class="flip-card-back">
                    <div class="section-btn-box">
                        <button onclick="currentSlide(1)" class="section-btn">
                            <div class="table-name" >
                                DANH SÁCH BẠN BÈ
                            </div>
                        </button>
                        <button onclick="currentSlide(2)" class="section-btn">
                            <div class="table-name" >
                                LỜI MỜI KẾT BẠN (${friendRequests.size()})
                            </div>
                        </button>
                    </div>

                    <div class="sliding-sections">
                        <div class="section left" style="overflow-y: scroll;">
                              <table class="table caption-top">
                                   <thead>
                                     <tr>
                                       <th scope="col">Email</th>
                                      <th scope="col">Thao tác</th>
                                     </tr>
                                   </thead>
                                   <tbody>
                                     <c:forEach items="${connectedFriends}" var="friend">
                                         <tr>
                                           <td>${friend.friendAccount.email}</td>
                                           <td>
                                             <div style="display:flex;">
                                                  <div class="green-text-button" style="margin-right:30px; padding: 1px 5px 1px 5px;">
                                                  <a style="text-decoration:none;color:green;margin-right:5vw;" href="./addFriendToDiet/${friend.friendAccount.id}">Dùng bữa</a>
                                                  <a style="text-decoration:none;color:green;" href="./unfriend/${friend.friendAccount.id}">Hủy kết bạn</a>
                                                  </div>
                                             </div>
                                           </td>
                                         </tr>
                                     </c:forEach>
                                   </tbody>
                                </table>

                        </div>
                        <div class="section right">
                            <div class="tutorial" style="overflow-y: scroll;">
                               <table class="table caption-top">
                                  <thead>
                                    <tr>
                                      <th scope="col">Email</th>
                                     <th scope="col">Thao tác</th>
                                    </tr>
                                  </thead>
                                  <tbody>
                                    <c:forEach items="${friendRequests}" var="friendRequest">
                                        <tr>
                                          <td>${friendRequest.userAccount.email}</td>
                                          <td>
                                            <div style="display:flex;">
                                                 <div class="green-text-button" style="margin-right:30px; padding: 1px 5px 1px 5px;"><a style="text-decoration:none;color:green;" href="./acceptInvitation/${friendRequest.userAccount.id}">Đồng ý</a></div>
                                            </div>
                                          </td>
                                        </tr>
                                    </c:forEach>
                                  </tbody>
                               </table>
                            </div>
                        </div>

                    </div>
                    <button class="flip-btn" style="width:100%;border:none;background: lightblue;">Tìm bạn</button>
                </div>

            </div>

        </div>
    </body>
</html>
