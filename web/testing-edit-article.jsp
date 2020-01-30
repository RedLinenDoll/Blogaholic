<%--
  Created by IntelliJ IDEA.
  User: apra984
  Date: 30/01/2020
  Time: 1:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editing article</title>
</head>
<body>
<form id="test" action="./edit-article" method="post">
    <label for="title">Title here:</label>
    <input type="text" id="title" name="title">
    <label for="content">
        Content here:
    </label>
    <input type="text" id="content" name="content">

    <button type="submit">Submit changes</button>

</form>

</body>
</html>
