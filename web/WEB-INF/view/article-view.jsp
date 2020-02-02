<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="article: ${article.articleTitle}">
    <title>${article.articleTitle} by ${author.username}</title>

    <jsp:include page="../../cross-page-view/link-fonts.jsp"/>

    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'/>
    <link rel="stylesheet" href='<c:url value="/assets/layout${author.layoutID}.css"/>'/>
    <script src='<c:url value="/js/customized-styling.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/js/load-article-comments.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/js/like-dislike.js"/>' type="text/javascript"></script>

    <script type="text/javascript">
        window.addEventListener("load",async function () {
            applyThemeColor(`${author.themeColor}`);
            applyLayoutSpecificStyling(`${author.layoutID}`, `${author.themeColor}`);
            <c:choose>
            <c:when test="${loggedUser==null}">
            await loadCommentList(${article.articleID}, ${author.userID}, -1);
            </c:when>
            <c:otherwise>
            await loadCommentList(${article.articleID}, ${author.userID}, ${loggedUser.userID});
            </c:otherwise>
            </c:choose>
            listenForLikeDislike();
        })
    </script>

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
        <span>Article by <a href='<c:url value="user-profile?user-id=${author.userID}"/>'><img class="inline-avatar" src='<c:url value="/images/avatar/${author.avatarPath}"/>'
                                                                                           alt="author avatar"></a> ${author.username}</span>
    </div>

</div>

<div class="body-container">
    <c:if test="${loggedUser.userID==author.userID}">
        <div class="article-author-option-div">
            <button id="delete-article-button" class="link-button article-author-option-button" onclick="deleteArticle(${article.articleID})">
                Delete
            </button>
            <button id="edit-article" class="link-button article-author-option-button">
                <a href="./edit-article?articleID=${article.articleID}">
                    Edit
                </a>
            </button>
        </div>
    </c:if>

    <div id="article-content-container">
        <p>
            ${article.articleContent}
        </p>
    </div>

    <div id="article-options-container">
        <button class="link-button back-to-blog-button">
            <a href='<c:url value="/blog-view?authorID=${author.userID}"/>'>
                Back to ${author.username}'s blog &nbsp;&#8594;
            </a>
        </button>

    </div>

    <c:if test="${loggedUser!= null}">
        <div id="add-article-comment-container">
            <form id="add-article-comment" action='<c:url value="/add-comment"/>' method="post">
                <label for="add-comment-to-article" class="comment-info" style="font-size: 15px;">Add Comments: </label><br>
                <textarea id="add-comment-to-article" rows="4" maxlength="512" name="new-comment-body" placeholder="Share your thoughts on ${author.username}'s article" style="width: 100%; font-size: 15px; font-family:var(--primary-font)"></textarea>
                <input type="hidden" name="target-id" value="${article.articleID}">
                <input type="hidden" name="article-id" value="${article.articleID}">
                <input type="hidden" name="target-type" value="article">
                <button id="submit-comment-to-article" class="comment-option-button">Post</button>
            </form>
        </div>
    </c:if>


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
