<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome, ${loggedUser.username}!</title>

    <jsp:include page="/cross-page-view/link-fonts.jsp"/>
    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'>
    <link rel="stylesheet" href='<c:url value="/assets/layout${loggedUser.layoutID}.css"/>'>

    <script src='<c:url value="/js/customized-styling.js"/>' type="text/javascript"></script>
    <script type="text/javascript" src='<c:url value="js/load-recent-articles.js"/>'></script>
    <script type="text/javascript">
        window.addEventListener("load", function () {
            loadRecentArticleList();
            applyThemeColor(`${loggedUser.themeColor}`);
            applyLayoutSpecificStyling(`${loggedUser.layoutID}`, `${loggedUser.themeColor}`);

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
    <div class="page-h1-container" id="welcome-message-container">
        <h1 id="welcome-message">
            Welcome, <img class="inline-avatar"
                          src='<c:url value="/images/avatar/${loggedUser.avatarPath}"/>'>${loggedUser.username}
        </h1>
        <button class="link-button">
            <a href='<c:url value="/blog-view?authorID=${loggedUser.userID}"/>'>
                Go to my blog &nbsp;&#8594;
            </a>
        </button>
    </div>
</div>

<div class="body-container" id="recent-article-container">
    <h2> What's new? </h2>
    <div id="recent-article-list-container" class="page-item-container">
        <%--    This part is for demoenstration of jsp structure, and will be cleared once loaded article --%>
        <div class="article-div page-item-div">

            <a class="full-article-link page-item-link" href='<c:url value="#"/>'>
                <div class="article-title-div page-item-title-div">
                    <h2 class="article-title page-item-title">
                        <%--            article title shows here  --%>
                    </h2>
                </div>
                <div class="article-brief-div page-item-brief-div">
                    <p class="article-brief page-item-brief">
                        <%--            article brief shows here --%>
                    </p>
                </div>
            </a>
            <div class="article-info-div page-item-info-div">
                <span class="article-info page-item-info">
                    <%--           creation date , likes and dislikes shows here --%>
                </span>
            </div>

        </div>
    </div>

</div>

</body>
</html>
