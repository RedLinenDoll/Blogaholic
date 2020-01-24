<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="blog: ${author.blogName}">
    <title>${author.blogName}</title>
</head>

<body>
<div id="head-container">
    <div id="blog-name-container">
        <h1 id="blog-name">${author.blogName}</h1>
    </div>
    <div id="blog-description-container">
        <p id="blog-description" >${author.blogDescription}</p>
    </div>
</div>

<div id="post-list-container">
<%-- load posts with javascript here--%>
</div>

</body>
</html>
