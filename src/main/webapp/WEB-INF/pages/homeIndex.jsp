<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/resources/css/homeindex.css" />" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
     <jsp:include page="header.jsp" />
    <div class="container">
      <div class="food1"
          style="background-image: url(${listOfFoods.get(0).image})">
          <a href="./details/${listOfFoods.get(0).id}">
              <span class="info">
                    <c:if test = "${listOfFoods.get(0).foodsLabelsEntityList.size() != 0}">
                          <span class="label">${listOfFoods.get(0).foodsLabelsEntityList.get(0).label.name}</span>
                      </c:if>
                    <span class="name">${listOfFoods.get(0).name}</span>
              </span>
          </a>
      </div>
      <div class="food2"
            style="background-image: url(${listOfFoods.get(1).image})">
            <a href="./details/${listOfFoods.get(1).id}">
                <span class="info">
                      <c:if test = "${listOfFoods.get(1).foodsLabelsEntityList.size() != 0}">
                            <span class="label">${listOfFoods.get(1).foodsLabelsEntityList.get(0).label.name}</span>
                        </c:if>
                      <span class="name">${listOfFoods.get(1).name}</span>
                </span>
            </a>
        </div>
      <div class="food3"
          style="background-image: url(${listOfFoods.get(2).image})">
          <a href="./details/${listOfFoods.get(2).id}">
              <span class="info">
                    <c:if test = "${listOfFoods.get(2).foodsLabelsEntityList.size() != 0}">
                                                <span class="label">${listOfFoods.get(2).foodsLabelsEntityList.get(0).label.name}</span>
                                            </c:if>
                    <span class="name">${listOfFoods.get(2).name}</span>
              </span>
          </a>
      </div>
      <div class="food4"
            style="background-image: url(${listOfFoods.get(3).image})">
            <a href="./details/${listOfFoods.get(3).id}">
                <span class="info">
                       <c:if test = "${listOfFoods.get(3).foodsLabelsEntityList.size() != 0}">
                                                                      <span class="label">${listOfFoods.get(3).foodsLabelsEntityList.get(0).label.name}</span>
                                                                  </c:if>
                      <span class="name">${listOfFoods.get(3).name}</span>
                      <div style="width:16vw;" class="des">${listOfFoods.get(3).description}</div>
                </span>
            </a>
        </div>
         <div class="food5"
            style="background-image: url(${listOfFoods.get(4).image})">
            <a href="./details/${listOfFoods.get(4).id}">
                <span class="info">
                       <c:if test = "${listOfFoods.get(4).foodsLabelsEntityList.size() != 0}">
                                                                      <span class="label">${listOfFoods.get(4).foodsLabelsEntityList.get(0).label.name}</span>
                                                                  </c:if>
                      <span class="name">${listOfFoods.get(4).name}</span>
                </span>
            </a>
        </div>
         <div class="food67">
              <div class="food6"
                 style="background-image: url(${listOfFoods.get(5).image})">
                 <a href="./details/${listOfFoods.get(5).id}">
                     <span class="info">
                            <c:if test = "${listOfFoods.get(5).foodsLabelsEntityList.size() != 0}">
                                                                           <span class="label">${listOfFoods.get(5).foodsLabelsEntityList.get(0).label.name}</span>
                                                                       </c:if>
                           <span class="name">${listOfFoods.get(5).name}</span>
                           <div style="width:18vw;" class="des">${listOfFoods.get(5).description}</div>
                     </span>
                 </a>
             </div>
             <div class="food7"
                  style="background-image: url(${listOfFoods.get(6).image})">
                  <a href="./details/${listOfFoods.get(6).id}">
                      <span class="info">
                            < <c:if test = "${listOfFoods.get(6).foodsLabelsEntityList.size() != 0}">
                                                                             <span class="label">${listOfFoods.get(6).foodsLabelsEntityList.get(0).label.name}</span>
                                                                         </c:if>
                            <span class="name">${listOfFoods.get(6).name}</span>
                      </span>
                  </a>
              </div>
         </div>
        <div class="food8"
              style="background-image: url(${listOfFoods.get(7).image})">
              <a href="./details/${listOfFoods.get(7).id}">
                  <span class="info">
                         <c:if test = "${listOfFoods.get(7).foodsLabelsEntityList.size() != 0}">
                                                                        <span class="label">${listOfFoods.get(7).foodsLabelsEntityList.get(0).label.name}</span>
                                                                    </c:if>
                        <span class="name">${listOfFoods.get(7).name}</span>
                        <div style="width:18vw;" class="des">${listOfFoods.get(7).description}</div>
                  </span>
              </a>
          </div>
          <div class="food9"
                style="background-image: url(${listOfFoods.get(8).image})">
                <a href="./details/${listOfFoods.get(8).id}">
                    <span class="info">
                           <c:if test = "${listOfFoods.get(8).foodsLabelsEntityList.size() != 0}">
                                                                          <span class="label">${listOfFoods.get(8).foodsLabelsEntityList.get(0).label.name}</span>
                                                                      </c:if>
                          <span class="name">${listOfFoods.get(8).name}</span>
                    </span>
                </a>
            </div>
          <div class="food1011">
                <div class="food10"
                   style="background-image: url(${listOfFoods.get(9).image})">
                   <a href="./details/${listOfFoods.get(9).id}">
                       <span class="info">
                             <c:if test = "${listOfFoods.get(9).foodsLabelsEntityList.size() != 0}">
                                                                              <span class="label">${listOfFoods.get(9).foodsLabelsEntityList.get(0).label.name}</span>
                                                                          </c:if>
                             <span class="name">${listOfFoods.get(9).name}</span>
                       </span>
                   </a>
               </div>
               <div class="food11"
                    style="background-image: url(${listOfFoods.get(10).image})">
                    <a href="./details/${listOfFoods.get(10).id}">
                        <span class="info">
                               <c:if test = "${listOfFoods.get(10).foodsLabelsEntityList.size() != 0}">
                                                                              <span class="label">${listOfFoods.get(10).foodsLabelsEntityList.get(0).label.name}</span>
                                                                          </c:if>
                              <span class="name">${listOfFoods.get(10).name}</span>
                        </span>
                    </a>
                </div>
           </div>
          <div class="food12"
              style="background-image: url(${listOfFoods.get(11).image})">
              <a href="./details/${listOfFoods.get(11).id}">
                  <span class="info">
                         <c:if test = "${listOfFoods.get(11).foodsLabelsEntityList.size() != 0}">
                                                                        <span class="label">${listOfFoods.get(11).foodsLabelsEntityList.get(0).label.name}</span>
                                                                    </c:if>
                        <span class="name">${listOfFoods.get(11).name}</span>
                        <div style="width:18vw;" class="des">${listOfFoods.get(11).description}</div>
                  </span>
              </a>
          </div>
    </div>
</body>
</html>