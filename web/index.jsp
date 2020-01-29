<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <title>Welcome to BLOG</title>

    <jsp:include page="/cross-page-view/link-fonts.jsp"/>
    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'/>
    <link rel="stylesheet" href='<c:url value="/assets/layout1.css"/>'>
    <script src='<c:url value="/js/customized-styling.js"/>' type="text/javascript"></script>
    <script src='<c:url value="js/load-blog-list.js"/>'></script>
    <script type="text/javascript">
        window.addEventListener("load", function () {
            loadAllBlogList();
            applyThemeColor(`#3f99ae`);
            applyLayoutSpecificStyling(`1`, `#3f99ae`);
        })
    </script>


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

<div class="head-container">
    <div id="index-welcome-container" class="page-h1-container">
        <h1>
            Welcome to blogaholic, where ideas sparkle
        </h1>
    </div>
    <button class="link-button">
        <a href='<c:url value="/signup.html"/>'>
            Create my blog &nbsp;&#8594;
        </a>
    </button>
</div>

<div class="body-container" id="all-blog-container">


</div>


</body>
</html>