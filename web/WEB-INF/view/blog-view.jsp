<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href='<c:url value="/images/icon.png"/>'>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="blog: ${author.blogName}">
    <title>${author.blogName}</title>

    <jsp:include page="/cross-page-view/link-fonts.jsp"/>

    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'/>
    <link rel="stylesheet" href='<c:url value="/assets/layout${author.layoutID}.css"/>'/>

    <script src='<c:url value="/js/customized-styling.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/js/load-blog-articles.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/js/like-dislike.js"/>' type="text/javascript"></script>
    <script type="text/javascript" src='<c:url value="/js/draggable-element.js"/>'></script>
    <script type="text/javascript" src='<c:url value="/js/sort-articles-by-rules.js"/>'></script>
    <script type="text/javascript" src='<c:url value="/js/render-follow-option.js"/>'></script>
    <script type="text/javascript">
        window.addEventListener("load", function () {

            applyThemeColor(`${author.themeColor}`);
            applyLayoutSpecificStyling(`${author.layoutID}`, `${author.themeColor}`);
            loadArticleList(${author.userID});
            const sortController = document.querySelector("#sort-rule");
            sortController.addEventListener("change", resortArticles);
            <c:choose>
            <c:when test="${not empty loggedUser}">
            renderFollowSpan(${author.userID}, ${loggedUser.userID}, `${author.username}`);
            </c:when>
            <c:otherwise>
            renderFollowSpan(${author.userID}, -1, `${author.username}`);
            </c:otherwise>
            </c:choose>
        });


    </script>
    <style>
        .body-container {
            top: 1em;
        }

        #follow-span {
            margin-left: 10px;
            padding-left: 10px;
            border-left: 1px solid var(--gentle-black);
        }
    </style>

</head>

<body>

<c:choose>
    <c:when test="${loggedUser == null}">
        <jsp:include page="/cross-page-view/visitor-bar.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="/cross-page-view/user-bar.jsp"/>
    </c:otherwise>
</c:choose>

<div class="head-container">
    <div id="sort-option" class="draggable-container">
        <div id="sort-option-head">
            <label for="sort-rule"><span>Sort by</span></label>
        </div>
        <div id="sort-option-body">
            <select name="sort-rule" id="sort-rule">
                <option value="latest-first" selected>Latest Article First</option>
                <option value="most-liked-first">Most Liked First</option>
                <option value="article-title-a-z">Article Title (A-Z)</option>
                <option value="article-title-z-a">Article Title (Z-A)</option>
            </select>
        </div>
    </div>
    <div id="blog-name-container" class="page-h1-container">
        <h1 id="blog-name">
            ${author.blogName}
        </h1>
    </div>
    <div class="author-intro-container page-author-container">
        <span><a href='<c:url value="user-profile?user-id=${author.userID}"/>'> <img class="inline-avatar"
                                                                                     src='<c:url value="/images/avatar/${author.avatarPath}"/>'
                                                                                     alt="author avatar"></a> ${author.username}'s blog
        </span>
        <span id="follow-span" class="follow-span">
        </span>
    </div>
    <c:if test="${loggedUser.userID==author.userID}">
        <div class="blog-author-option-div">
            <a href='<c:url value="/add-article-view.jsp"/>'>
                <button id="add-article-button" class="link-button article-author-option-button">
                    Write New Article
                </button>
            </a>
            <a href='<c:url value="/user-option?user-request=blog-setup"/>'>
                <button id="blog-setting-button" class="link-button article-author-option-button">
                    Blog Settings
                </button>
            </a>

        </div>
    </c:if>
    <div id="blog-description-container" class="page-description-container">
        <p id="blog-description">${author.blogDescription}</p>
    </div>


</div>
<div class="body-container">
    <div id="article-list-container" class="page-item-container">
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
