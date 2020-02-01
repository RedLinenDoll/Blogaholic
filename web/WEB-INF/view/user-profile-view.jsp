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
<div class="head-container">
</div>
<div class="body-container">
    <h1>Welcome to ${profileOwner.username}'s profile</h1>
    ${profileOwner.username}
    ${profileOwner.dateOfBirth}

</div>


</body>
</html>
