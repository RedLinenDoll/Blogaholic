<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href='<c:url value="/images/icon.png"/>'>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Blogaholic ${profileOwner.username}'s Profile Page</title>
    <jsp:include page="/cross-page-view/link-fonts.jsp"/>
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
        <jsp:include page="/cross-page-view/visitor-bar.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="/cross-page-view/user-bar.jsp"/>
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
            <a href='<c:url value="/user-option?user-request=change-account-setting"/>' class="profile-page-button">
                <button>Account setting</button>
            </a>
        </c:if>
    </div>

    <hr class="profile-page-hr">
    <p>${profileOwner.selfIntroduction}</p>

    <c:if test="${showRealNameInfo}">
    <div class="real-name-info">
        <table align="center">
            <tr>
                <td><i class="far fa-user profile-icon"></i> First Name:</td>
                <td>${profileOwner.firstName}</td>
            </tr>
            <tr>
                <td><i class="far fa-user profile-icon"></i> Last Name:</td>
                <td>${profileOwner.lastName}</td>
            </tr>
            <tr>
                <td><i class="fas fa-birthday-cake profile-icon"></i> Birthday:</td>
                <td>${profileOwner.dateOfBirth}</td>
            </tr>
        </table>
        <c:if test="${isOwnProfile}">

            <div style="margin: 15px 0; font-size: 0.8em; color: #777777">
                <c:choose>
                    <c:when test="${!profileOwner.shareRealNameInfo}">
                        You chose <u>NOT</u> to share these real name information with your visitors,
                        <br> and we will respect that
                    </c:when>
                    <c:otherwise>
                        You chose to share these real name information, <br> and you can change your mind any time.
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>

        <c:if test="${isOwnProfile}">
            <a href="<c:url value="/user-option?user-request=change-user-profile"/>" class="profile-page-button">
                <button>Change Personal Information</button>
            </a>
        </c:if>
        </c:if>
        <br><br>
        <a href="<c:url value="/blog-view?authorID=${profileOwner.userID}"/>" class="profile-page-button">
            <button>Go to ${profileOwner.username}'s blog: ${profileOwner.blogName}</button>
        </a>

    </div>


</div>


</body>
</html>
