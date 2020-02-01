<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anran
  Date: 1/02/20
  Time: 6:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Blogaholic ${profileOwner.username}'s Profile Page</title>
    <jsp:include page="../../cross-page-view/link-fonts.jsp"/>
    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'>
    <link rel="stylesheet" href='<c:url value="/assets/layout${profileOwner.layoutID}.css"/>'>
    <script type="text/javascript" src='<c:url value="/js/customized-styling.js"/>'></script>
    <script type="text/javascript">
        window.addEventListener("load", function () {
            applyThemeColor(`${profileOwner.themeColor}`);
            applyLayoutSpecificStyling(`${profileOwner.layoutID}`, `${profileOwner.themeColor}`);

        })
    </script>
    <link rel="stylesheet" href='<c:url value="/assets/profile-page-layout.css"/>'>

    <style>
        <c:if test="${profileOwner.layoutID == 2}">
        .body-container {
            position: relative;
            bottom: 150px;
        }

        </c:if>
    </style>
</head>
<c:choose>
    <c:when test="${loggedUser == null}">
        <jsp:include page="../../cross-page-view/visitor-bar.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="../../cross-page-view/user-bar.jsp"/>
    </c:otherwise>
</c:choose>

<body>
<c:set value="${loggedUser!=null && profileOwner.userID == loggedUser.userID}" var="isOwnProfile"/>
<c:set value="${isOwnProfile || profileOwner.shareRealNameInfo == true}" var="showRealNameInfo"/>
<div class="head-container">
</div>
<div class="body-container">
    <h1>
        <c:choose>
            <c:when test="${isOwnProfile}">
                Welcome to your profile, ${profileOwner.username}.
            </c:when>
            <c:otherwise>
                Welcome to ${profileOwner.username}'s profile
            </c:otherwise>
        </c:choose>
    </h1>

    <div id="account-info">
        <img src='<c:url value="/images/avatar/${profileOwner.avatarPath}"/>' alt=" "
             class="block-avatar profile-avatar">

        <c:if test="${isOwnProfile}">
            <a href='<c:url value="/user-option?user-request=change-avatar"/>'>
                <i class="fas fa-pen pen-icon-for-edit" title="change avatar" id="change-avatar"></i>
            </a>
        </c:if>

    <p>
        ${profileOwner.username}

    </p>
        <c:if test="${isOwnProfile}">
            <a href="/user-option?user-request=change-account-setting" class="profile-page-button"> <button>Account setting</button></a>
        </c:if>
    </div>
    <hr class="profile-page-hr">
    <c:if test="${showRealNameInfo}">
    <div class="real-name-info">

        <p><i class="far fa-user head-icon"></i> First Name: ${profileOwner.firstName}</p>
        <p><i class="far fa-user head-icon"></i> Last Name: ${profileOwner.lastName}</p>
       <p> <i class="fas fa-birthday-cake"></i>Birthday: ${profileOwner.dateOfBirth}</p>


    </div>
    </c:if>

</div>


</body>
</html>
