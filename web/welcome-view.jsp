<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome, ${loggedUser.username}!</title>
    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'>
</head>
<body>

<c:choose>
    <c:when test="${loggedUser == null}">
        <jsp:include page="cross-page-view/visitor-bar.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="cross-page-view/user-bar.jsp"/>
    </c:otherwise>
</c:choose>

<%--TODO  Once login is achieved, create a welcome page, feeding recent articles. --%>
<div class="head-container">
<div class="page-h1-container" id="welcome-message-container">
    <h1 id="welcome-message">
Welcome, <img class="inline-avatar" src='<c:url value="/images/avatar/${loggedUser.avatarPath}"/>'>${loggedUser.username}
    </h1>
    <button class="link-button">
        <a href='<c:url value="/blog-view?authorID=${loggedUser.userID}"/>'>
        Go to my blog &nbsp;&#8594;
        </a>
    </button>
</div>
</div>

</body>
</html>
