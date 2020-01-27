<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome, ${loggedUser.username}!</title>
    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'>
</head>
<body>
<%--TODO  Once login is achieved, create a welcome page, feeding recent articles. --%>
Welcome, <img class="inline-avatar" src='<c:url value="/images/avatar/${loggedUser.avatarPath}"/>'>${loggedUser.username}
</body>
</html>
