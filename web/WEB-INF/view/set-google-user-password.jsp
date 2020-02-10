<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href='<c:url value="/images/icon.png"/>'>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Account Setting for ${loggedUser.username}</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href='<c:url  value="assets/cross-layout-style.css"/>'>
    <link rel="stylesheet" href='<c:url value="assets/layout2.css"/>'>
    <link rel="stylesheet" href='<c:url value="assets/authentication-page-style.css"/>'>
    <script type="text/javascript" src='<c:url value="js/account-setting-check.js"/>'></script>
    <script type="text/javascript">
        if (window.location.href.endsWith("#setting-failed")) {
            alert("Sorry, username and password did not match. Account setting failed!");
        }
        window.addEventListener("load", function () {
            const confirmNewPasswordInput = document.querySelector("#confirm-new-password");
            const newPasswordInput = document.querySelector("#new-password");
            const passwordWarn = document.querySelector("#not-matching-warning");

            confirmNewPasswordInput.addEventListener("input", function () {
                checkIfNewPasswordMatches(confirmNewPasswordInput, newPasswordInput, passwordWarn)
            });

        });
    </script>
</head>
<body>
<div class="page-content-container">
    <div class="head-container">
        <div class="page-h1-container" id="account-setting-welcome-container">
            <h1 id="account-setting-welcome">
                Welcome, ${newUser.username}, please set a password for your account
            </h1>
        </div>
    </div>
    <div class="body-container">
        <form id="account-setting" action='<c:url value="/account-setting"/>' method="post"
              onsubmit="return validateForm(this)">

            <input type="hidden" id="old-username" name="old-username" value="${newUser.username}">
            <input type="hidden" id="google-user-initiation" name="google-user-initiation" value="true">

            <label for="new-password">Password:</label>
            <input type="password" id="new-password" name="new-password" required minlength="6" maxlength="20">

            <label for="confirm-new-password">Confirm Password:</label>
            <input type="password" id="confirm-new-password" required minlength="6" maxlength="20">
            <p id="not-matching-warning" class="invisible warning-message">Password do not match!</p>
            <div class="operation-container" id="account-setting-operation-container">
                <button type="submit" id="submit">Submit Password</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
