<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anran
  Date: 2/02/20
  Time: 8:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
            const usernameWarn = document.querySelector("#duplicate-warning");
            const newUsernameInput = document.querySelector("#new-username");
            const confirmNewPasswordInput = document.querySelector("#confirm-new-password");
            const newPasswordInput = document.querySelector("#new-password");
            const passwordWarn = document.querySelector("#not-matching-warning");
            const oldUsername = `${loggedUser.username}`;
            newUsernameInput.addEventListener("input", function () {
                checkIfNewUsernameExists(newUsernameInput, oldUsername, usernameWarn)
            });
            confirmNewPasswordInput.addEventListener("input", function () {
                checkIfNewPasswordMatches(confirmNewPasswordInput, newPasswordInput, passwordWarn)
            });

            const cancelButton = document.querySelector("#cancel-button");
            const deleteAccountButton = document.querySelector("#delete-account-button");
            cancelButton.addEventListener("click", function () {
                window.location.replace(`<c:url value="/user-profile?user-id=${loggedUser.userID}"/>`);
            });
            deleteAccountButton.addEventListener("click", function () {
                const bodyContainer = document.querySelector(".body-container");
                document.querySelector("#account-setting-operation-container").removeChild(deleteAccountButton);
                loadConfirmDeleteAccountForm(bodyContainer, `${loggedUser.username}`, `${loggedUser.userID}`);
            });

        });
    </script>
</head>
<body>
<div class="page-content-container">
    <div class="head-container">
        <div class="page-h1-container" id="account-setting-welcome-container">
            <h1 id="account-setting-welcome">
                Account Setting for ${loggedUser.username}
            </h1>
        </div>
    </div>
    <div class="body-container">
        <form id="account-setting" action='<c:url value="/account-setting"/>' method="post"
              onsubmit="return validateForm(this)">

            <label for="old-username">Old Username:</label>
            <input type="text" id="old-username" name="old-username" value="${loggedUser.username}">
            <label for="old-password">Old Password:</label>
            <input type="password" id="old-password" name="old-password" required>
            <br>

            <label for="new-username">New Username:</label>
            <input type="text" id="new-username" name="new-username" required>
            <p id="duplicate-warning" class="invisible warning-message">Username already exists!</p>
            <br>
            <label for="new-password">New Password:</label>
            <input type="password" id="new-password" name="new-password" required>
            <br>
            <label for="confirm-new-password">Confirm NewPassword:</label>
            <input type="password" id="confirm-new-password" required>
            <p id="not-matching-warning" class="invisible warning-message">Password do not match!</p>
            <div class="operation-container" id="account-setting-operation-container">
                <button type="submit" id="submit">Submit Change</button>
                <br>
                <button type="reset" id="cancel-button">Cancel
                    Change
                </button>
                <br>
                <button type="button" id="delete-account-button" style="background-color: red">
                    Delete Account
                </button>

            </div>
        </form>
    </div>
</div>

</body>
</html>
