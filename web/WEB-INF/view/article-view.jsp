<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="article: ${article.articleTitle}">
    <title>${article.articleTitle} by ${author.username}</title>

    <jsp:include page="link-fonts.jsp"/>

    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'/>
    <link rel="stylesheet" href='<c:url value="/assets/layout${author.layoutID}.css"/>'/>


    <script src='<c:url value="/js/customized-styling.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/js/load-article-comments.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/js/like-or-dislike.js"/>' type="text/javascript"></script>
    <script type="text/javascript">
        window.addEventListener("load", function () {
            applyThemeColor(`${author.themeColor}`);
            loadCommentList(${article.articleID});
        })
    </script>
    <c:if test="${author.layoutID!=2}">
        <style>
            .head-container {
                background: url("./images/layout-decor/layout1-${author.themeColor.substring(1)}.jpg") no-repeat center center fixed;
            }
        </style>
    </c:if>
</head>

<body>

<c:choose>
    <c:when test="${loggedUser == null}">
        <jsp:include page="../../cross-page-view/visitor-bar.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="../../cross-page-view/user-bar.jsp"/>
    </c:otherwise>
</c:choose>

<div class="head-container">
    <div id="article-title-container" class="page-h1-container">
        <h1 id="article-title">${article.articleTitle}</h1>
    </div>
    <div class="author-intro-container page-author-container">
        <span>by <img class="inline-avatar" src='<c:url value="/images/avatar/${author.avatarPath}"/>'
                      alt="author avatar"> ${author.username}</span>
    </div>
</div>

<div class="body-container">

    <div id="article-content-container">
        <p>
            ${article.articleContent}
        </p>
    </div>

    <div id="article-options-container">
        <button class="link-button">
            <a href='<c:url value="/blog-view?authorID=${author.userID}"/>'>
                Back to ${author.username}'s blog &nbsp;&#8594;
            </a>
        </button>
    </div>

    <div id="all-comments-container">
        <div class="root-comment-div comment-div">
            <div class="block-avatar-div">
            </div>
            <div class="comment-info-div">
            </div>
            <div class="comment-body-div">
            </div>
            <div class="comment-options-div">
            </div>

            <div class="child-comment-div comment-div">
                <div class="comment-body-div">
                </div>
                <div class="comment-info-div">
                </div>

                <%--            more layers of comment-div  --%>
            </div>

        </div>
    </div>

</div>
</body>
</html>
