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
<c:set var="changing" value="${not empty existingProfileOwner}"/>
<div class="page-content-container">

    <div class="head-container">
        <h1>
            ${newUser.username}, We would love to know better about you!
        </h1>
    </div>
    <div class="body-container">
        <form id="user-profile-form" method="post" action='<c:url value="/edit-user"/>'
              style="width: 80%; align-items: stretch; justify-content: stretch">

            <input type="hidden" name="user-id" value="${newUser.userID}">
            <label for="first-name">
                Your first name:
            </label>
            <input type="text" id="first-name" name="first-name" required
                   <c:if test="${changing}">value="${existingProfileOwner.firstName}"</c:if>>
            <label for="last-name">
                Your last name:
            </label>
            <input type="text" id="last-name" name="last-name" required
                   <c:if test="${changing}">value="${existingProfileOwner.lastName}"</c:if>>

            <label for="date-of-birth">
                Your date of birth:
            </label>
            <input id="date-of-birth" name="date-of-birth" type="date" value="1990-01-01"
                   <c:if test="${changing}">value="${existingProfileOwner.dateOfBirth}"</c:if>
                   min="1900-01-01">
            <script type="text/javascript">
                document.querySelector("#date-of-birth").max = new Date().toISOString().split("T")[0];
            </script>

            <span style="margin: 3px; padding-bottom: 5px"><label for="share-real-name-info" class="non-primary-label">Share real name and date of birth with visitors</label>
            <input type="checkbox" name="share-real-name-info" id="share-real-name-info"
                   class="non-primary-input"
            <c:if test="${changing && existingProfileOwner.shareRealNameInfo}"> checked</c:if> ></span>
            <div style="height: 1px; width: 60%; border-bottom: 2px dotted var(--median-gray)">
            </div>


            <label for="self-intro-input" style="margin: 5px">Tell your blog visitors a bit about yourself:</label>
            <textarea rows="3" maxlength="256" cols="45" id="self-intro-input" name="self-introduction"
                      required><c:choose><c:when
                    test="${!changing}">Hi there, I'm ${newUser.username}, your fellow blogaholic!</c:when><c:otherwise>${existingProfileOwner.selfIntroduction}</c:otherwise></c:choose></textarea>

            <div class="operation-container">
                <button type="submit">Yes that's me</button>

            </div>

        </form>
    </div>
</div>
</body>
</html>
