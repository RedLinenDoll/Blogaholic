<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="icon" href='<c:url value="/images/icon.png"/>'>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Welcome to Blogaholic!</title>

    <jsp:include page="/cross-page-view/link-fonts.jsp"/>
    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'/>
    <c:choose>
        <c:when test="${not empty loggedUser}">
            <link rel="stylesheet" href='<c:url value="/assets/layout${loggedUser.layoutID}.css"/>'>
        </c:when>
        <c:otherwise>
            <link rel="stylesheet" href='<c:url value="/assets/layout1.css"/>'>
        </c:otherwise>
    </c:choose>

    <script src='<c:url value="/js/customized-styling.js"/>' type="text/javascript"></script>
    <script src='<c:url value="js/load-blog-list.js"/>'></script>
    <script type="text/javascript">
        window.addEventListener("load", function () {
            <c:choose>
            <c:when test="${not empty loggedUser}">
            applyThemeColor(`${loggedUser.themeColor}`);
            applyLayoutSpecificStyling(`${loggedUser.layoutID}`, `${loggedUser.themeColor}`);
            </c:when>
            <c:otherwise>
            applyThemeColor(`#3f99ae`);
            applyLayoutSpecificStyling(`1`, `#3f99ae`);
            </c:otherwise>
            </c:choose>
            loadAllBlogList();

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

    <c:if test="${loggedUser == null}">
        <a href='<c:url value="/signup.html"/>'>
            <button class="link-button">
                Create my blog &nbsp;&#8594;
            </button>
        </a>
    </c:if>

    <c:if test="${loggedUser != null}">
        <a href='<c:url value="/welcome-view.jsp"/>'>
            <button class="link-button">
                What's new&nbsp;&#8594;
            </button>
        </a>
    </c:if>

</div>
<h2 id="index-message">You will be sharing ideas with these blogaholics: </h2>

<div class="body-container" id="all-blog-container">


</div>


</body>
</html>