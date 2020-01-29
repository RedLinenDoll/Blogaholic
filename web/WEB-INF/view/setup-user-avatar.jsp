<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anran
  Date: 30/01/20
  Time: 11:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose your avatar</title>
</head>
<body>
<div class="head-container">
    <h1>
       An avatar will help your friends to recognize you.
    </h1>
</div>
<div class="body-container">
    <form id="avatar-form" method="post" action='<c:url value="/set-avatar"/>'>

    </form>
</div>

</body>
</html>
