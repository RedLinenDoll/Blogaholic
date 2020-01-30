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
    <link rel="stylesheet" href='<c:url value="/assets/cross-layout-style.css"/>'>
    <link rel="stylesheet" href='<c:url value="/assets/layout2.css"/>'>
    <link rel="stylesheet" href='<c:url value="/assets/authentication-page-style.css"/>'>
    <script type="text/javascript">
        window.addEventListener("load", function () {
            const containers = document.querySelectorAll(".avatar-selection");
            const radios = document.querySelectorAll(".avatar-radio");
            radios.forEach(radio => {
                addEventListener("click", highlightAvatar)
            });

            function highlightAvatar(e) {
                const avatarNum = e.target.value;
                containers.forEach(container => {
                    container.style.border=0;
                });
                containers[avatarNum - 1].style.border = "var(--theme-color) dotted 2px";
            }
        });


    </script>
</head>
<body>
<div class="page-content-container">
    <div class="head-container">
        <h1>
            An avatar will help your friends to recognize you, ${newUser.username}
        </h1>
    </div>
    <div class="body-container">
        <div id="avatar-set-container">
            <p id="info">You can choose either to select from an existing avatar, or upload your own one.</p>
            <form id="select-avatar-form" method="get" action='<c:url value="/set-avatar"/>'>
                <div id="select-avatar-container">
                    <div class="avatar-selection" id="avatar-selection1">
                        <label class="avatar-label" for="avatar1"><img class="block-avatar select-avatar"
                                                                       src='<c:url value="/images/avatar/avatar1.jpg"/>'
                                                                       alt="panda"></label>
                        <input type="radio" id="avatar1" class="avatar-radio" name="chosen-avatar" value="1">
                    </div>
                    <div class="avatar-selection" id="avatar-selection2">
                        <label class="avatar-label" for="avatar2"><img class="block-avatar select-avatar"
                                                                       src='<c:url value="/images/avatar/avatar2.jpg"/>'
                                                                       alt="bunny"></label>
                        <input type="radio" id="avatar2" class="avatar-radio" name="chosen-avatar" value="2">
                    </div>
                    <div class="avatar-selection" id="avatar-selection3">
                        <label class="avatar-label" for="avatar3"><img class="block-avatar select-avatar"
                                                                       src='<c:url value="/images/avatar/avatar3.jpg"/>'
                                                                       alt="whale"></label>
                        <input type="radio" id="avatar3" class="avatar-radio" name="chosen-avatar" value="3">
                    </div>
                    <div class="avatar-selection" id="avatar-selection4">
                        <label class="avatar-label" for="avatar4"><img class="block-avatar select-avatar"
                                                                       src='<c:url value="/images/avatar/avatar4.jpg"/>'
                                                                       alt="chick"></label>
                        <input type="radio" id="avatar4" class="avatar-radio" name="chosen-avatar" value="4">
                    </div>
                    <div class="avatar-selection" id="avatar-selection5">
                        <label class="avatar-label" for="avatar5"><img class="block-avatar select-avatar"
                                                                       src='<c:url value="/images/avatar/avatar5.jpg"/>'
                                                                       alt="bear"></label>
                        <input type="radio" id="avatar5" class="avatar-radio" name="chosen-avatar" value="5">
                    </div>
                    <div class="avatar-selection" id="avatar-selection6">
                        <label class="avatar-label" for="avatar6"><img class="block-avatar select-avatar"
                                                                       src='<c:url value="/images/avatar/avatar6.jpg"/>'
                                                                       alt="fox"></label>
                        <input type="radio" id="avatar6" class="avatar-radio" name="chosen-avatar" value="6">
                    </div>
                    <div class="avatar-selection" id="avatar-selection7">
                        <label class="avatar-label" for="avatar7"><img class="block-avatar select-avatar"
                                                                       src='<c:url value="/images/avatar/avatar7.jpg"/>'
                                                                       alt="lion"></label>
                        <input type="radio" id="avatar7" class="avatar-radio" name="chosen-avatar" value="7">
                    </div>
                    <div class="avatar-selection" id="avatar-selection8">
                        <label class="avatar-label" for="avatar8"><img class="block-avatar select-avatar"
                                                                       src='<c:url value="/images/avatar/avatar8.jpg"/>'
                                                                       alt="frog"></label>
                        <input type="radio" id="avatar8" class="avatar-radio" name="chosen-avatar" value="8">
                    </div>
                    <div class="avatar-selection" id="avatar-selection9">
                        <label class="avatar-label" for="avatar9"><img class="block-avatar select-avatar"
                                                                       src='<c:url value="/images/avatar/avatar9.jpg"/>'
                                                                       alt="hippo"></label>
                        <input type="radio" id="avatar9" class="avatar-radio" name="chosen-avatar" value="9">
                    </div>
                </div>
                <button class="link-button" id="submit-selected-avatar">Use selected avatar</button>
            </form>

            <form id="avatar-upload-form" method="post" action='<c:url value="/set-avatar"/>'>
                <div id="upload-avatar-container">
                    <label for="file-upload">Upload your own avatar here</label>
                    <input type="file" id="file-upload">

                </div>
                <button class="link-button" id="submit-uploaded-avatar">Use uploaded avatar</button>
            </form>


        </div>
    </div>
</div>

</body>
</html>
