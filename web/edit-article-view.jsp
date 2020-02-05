<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" href='<c:url value="/images/icon.png"/>'>

    <title>Edit article</title>
    <jsp:include page="/cross-page-view/link-fonts.jsp"/>
    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'>
    <link rel="stylesheet" href='<c:url value="/assets/layout${loggedUser.layoutID}.css"/>'>
    <script type="text/javascript" src='<c:url value="/js/customized-styling.js"/>'></script>
    <script type="text/javascript">
        <c:if test="${empty loggedUser}">
        window.location.replace(`/team-java_blogaholic/index.jsp`);
        </c:if>
        window.addEventListener("load", function () {
            applyThemeColor(`${loggedUser.themeColor}`);
            applyLayoutSpecificStyling(`${loggedUser.layoutID}`, `${loggedUser.themeColor}`);
        })
    </script>
    <link rel="stylesheet" href='<c:url value="/assets/profile-page-layout.css"/>'>
    <style>
        <c:if test="${loggedUser.layoutID == 2}">
        .body-container {
            position: relative;
            bottom: 150px;
        }

        </c:if>
        label {
            font-weight: bolder;
        }

        input[type="text"] {
            width: 80%;
            font-size: 1em;
        }

        .body-container {
            text-align: left;
        }
    </style>

    <script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
    <script>tinymce.init({
        selector: '#content',
        plugins: [
            "advlist autolink lists link image charmap print preview anchor",
            "searchreplace visualblocks code fullscreen",
            "insertdatetime media table paste image wordcount"
        ],
        toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
        images_upload_url: '/team-java_blogaholic/article-image-upload',
        automatic_uploads: true
    });
    </script>
</head>
<body>
<jsp:include page="/cross-page-view/user-bar.jsp"/>

<div class="head-container">
</div>

<div class="body-container">
    <h1>Editing Article ${oldArticle.articleTitle}</h1>
    <form id="edit-article" action="./edit-article" method="post">

        <input type="hidden" name="article-id" value="${oldArticle.articleID}">
        <label for="title">Title here:</label>
        <input type="text" id="title" name="title" value="${oldArticle.articleTitle}" required>
        <br>
        <br>
        <label for="content">
            Content here:
        </label>
        <textarea id="content" name="content" style="height: 600px">
            ${oldArticle.articleContent}
        </textarea>

        <button type="submit">Submit changes</button>
    </form>
</div>
</body>
</html>
