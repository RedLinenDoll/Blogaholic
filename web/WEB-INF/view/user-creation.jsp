<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <form id="user-profile-form" method="post" action='<c:url value="/edit-user"/>'>

            <input type="hidden" name="user-id" value="${newUser.userID}">
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
            <input id="date-of-birth" name="date-of-birth" type="date" required>

            <label for="share-real-name-info" class="non-primary-label">Share real name and date of birth with visitors</label>
            <input type="checkbox" name="share-real-name-info" id="share-real-name-info" class="non-primary-input">

            <label for="self-intro-input">Tell your blog visitors a bit about yourself:</label>
            <textarea rows="3" maxlength="256" cols="36" id="self-intro-input" name="self-intro" required></textarea>

            <div class="operation-container">
                <button type="submit">Yes that's me</button>

            </div>

        </form>
    </div>
</div>
</body>
</html>