<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script>
    window.addEventListener("load", function () {
        const toggleBox = document.querySelector("#toggle-down-box");
        const arrow = document.querySelector("#toggle-arrow");
        const toggleTrigger = document.querySelector("#toggle-trigger");
        toggleTrigger.addEventListener("click", toggleBar);

        function toggleBar() {
            arrow.classList.toggle("fa-angle-down");
            arrow.classList.toggle("fa-angle-right");
            toggleBox.classList.toggle("invisible");
        }
    });

</script>

<div id="user-top-bar" class="top-bar">
    <div class="top-bar-home-link">
        <a href='<c:url value="/"/>'>
            Blogaholic!
        </a>
    </div>

    <div class="top-bar-links" id="user-top-bar-links">
        <span class="top-bar-span" id="toggle-trigger">
            <i id="toggle-arrow" class="fas fa-angle-down"
               style="font-size: 1.5em; position:relative; top: 0.2em; right: 0.2em;"></i>
                <img class="inline-avatar" src='<c:url value="/images/avatar/${loggedUser.avatarPath}"/>'
                     alt=" ">
        <span id="user-name-span" style="margin-right: 10px">
            ${loggedUser.username}
        </span>

        </span>


        <div id="toggle-down-box" class="invisible">
            <span class="toggle-down-span">
                <a href='<c:url value="/user-profile?userID=${loggedUser.userID}"/>' id="profile-link">
                    My Profile
                </a>
            </span>
            <span class="toggle-down-span">
                <a href='<c:url value="/blog-view?authorID=${loggedUser.userID}"/>'>
                     My Blog
                </a>
            </span>
            <span class="toggle-down-span">
                <a href='<c:url value="/testing-add-article.jsp"/>'>
                    New article
                </a>
            </span>
            <span class="toggle-down-span">
                <a href='<c:url value="/logout"/>'>
                    Log Out
                </a>
            </span>
        </div>
    </div>
</div>