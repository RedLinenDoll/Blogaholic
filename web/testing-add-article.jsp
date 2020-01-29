<%--
  Created by IntelliJ IDEA.
  User: apra984
  Date: 29/01/2020
  Time: 12:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<form id="test" action="./add-article" method="post">
    <label for="title">Title here:</label>
    <input type="text" id="title" name="title">
    <label for="content">
        Content here:
    </label>
    <input type="text" id="content" name="content">

    <button type="submit">Submit!</button>

</form>

</body>
</html>
