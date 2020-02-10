<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href='<c:url value="/images/icon.png"/>'>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Welcome, ${loggedUser.username}!</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>

    <jsp:include page="/cross-page-view/link-fonts.jsp"/>
    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'>
    <link rel="stylesheet" href='<c:url value="/assets/layout${loggedUser.layoutID}.css"/>'>

    <script src='<c:url value="/js/customized-styling.js"/>' type="text/javascript"></script>
    <script type="text/javascript" src='<c:url value="js/load-feed-articles.js"/>'></script>
    <script src='<c:url value="/js/like-dislike.js"/>' type="text/javascript"></script>

    <script type="text/javascript">
        window.addEventListener("load", async function () {
            applyThemeColor(`${loggedUser.themeColor}`);
            applyLayoutSpecificStyling(`${loggedUser.layoutID}`, `${loggedUser.themeColor}`);
            showGreeting();
            await loadFeedArticleList(${loggedUser.userID});
            listenForLikeDislike();
        });

        function showGreeting() {
            const greetingSpan = document.querySelector("#greeting-span");
            let today = new Date();
            let currentHour = today.getHours();
            if (currentHour < 12) {
                greetingSpan.innerText = "Good morning, ";
            } else if (currentHour < 18) {
                greetingSpan.innerText = "Good afternoon, ";
            } else {
                greetingSpan.innerText = "Good evening, ";
            }
        }
    </script>
    <style>
        .article-information-div {
            margin: 0;
            position: relative;
            bottom: 1em;
            font-size: 0.8em;
            text-align: right;
        }

        .finish-following-div, .finish-all-div {
            text-align: center;
            font-weight: bold;
            background-color: var(--theme-color);
            color: white;
            padding: 5px 10px;
            margin-bottom: 4px;
        }

        #feed-more-button {
            margin-right: 8px;
        }
    </style>
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
            <span id="greeting-span"></span><a href='<c:url value="user-profile?user-id=${loggedUser.userID}"/>'> <img
                class="inline-avatar" src='<c:url value="/images/avatar/${loggedUser.avatarPath}"/>'
                alt=" "></a>${loggedUser.username}
        </h1>
        <a href='<c:url value="/blog-view?authorID=${loggedUser.userID}"/>'>
            <button class="link-button">
                Go to my blog &nbsp;&#8594;
            </button>
        </a>

    </div>
</div>

<div class="body-container" id="recent-article-container">
    <h2><u>What's new?</u></h2>
    <div id="feed-article-list-container" class="page-item-container">
        <%--        This is where we put all the articles --%>

        <button id="feed-more-button" class="link-button">Load more articles</button>
        <a href="#" id="go-top-link">
            <button class="link-button">Go to top of page</button>
        </a>
    </div>
</div>
</body>
</html>
