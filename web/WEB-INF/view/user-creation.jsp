<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anran
  Date: 29/01/20
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome in, ${newUser.username}!</title>
    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'>
    <link rel="stylesheet" href='<c:url value="/assets/layout2.css"/>'>
    <link rel="stylesheet" href='<c:url value="/assets/authentication-page-style.css"/>'>
</head>
<body>
<div class="page-content-container">

    <div class="head-container">
        <h1>
            Welcome in, ${newUser.username}
        </h1>
        <p>We would like to know more about you!</p>
    </div>
    <div class="body-container">
        <form id="user-profile-form">
            <%--        TODO submit it to user profile servlet --%>

            <label for="first-name">
                Your first name:
            </label>
            <input type="text" id="first-name" name="first-name" required>
            <label for="last-name">
                Your last name:
            </label>
            <input type="text" id="last-name" name="last-name" required>

            <label for="date-of-birth">
                Your date of birth:
            </label>
            <input id="date-of-birth" name="date-of-birth" type="date">
            <div class="operation-container">
                <button type="submit">Yes that's me</button>

            </div>

        </form>
    </div>
</div>
</body>
</html>
