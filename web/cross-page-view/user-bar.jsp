<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type = "text/javascript" src="${pageContext.request.contextPath}/js/googleLogin.js"></script>
<meta name="google-signin-client_id"
      content="263347824175-hug28ksnd327ufaafdfqmjm5bv9dg1ab.apps.googleusercontent.com">
<script type="text/javascript" src='<c:url value="/js/load-search-bar.js"/>'></script>

<script type="text/javascript" src='<c:url value="/js/responsive-layout.js"/>'></script>
<script type="text/javascript">
    window.addEventListener("load", function () {
        const toggleBox = document.querySelector("#toggle-down-box");
        const arrow = document.querySelector("#toggle-arrow");
        const toggleTrigger = document.querySelector("#toggle-trigger");
        toggleTrigger.addEventListener("click", toggleBar);
        let toggled = false;
        function toggleBar() {
            toggled = !toggled;
            arrow.classList.toggle("fa-angle-down");
            arrow.classList.toggle("fa-angle-up");
            toggleBox.classList.toggle("invisible");
        }
        document.querySelector("#search-icon").addEventListener("click", loadSearchBar);
    });

</script>

<div id="user-top-bar" class="top-bar">
    <div class="top-bar-home-link">
        <a href='<c:url value="/"/>'>
            <span id="home-icon"><i class="fas fa-home"></i></span>
            <span id="home-name">Blogaholic!</span>
        </a>
    </div>

    <div class="top-bar-links" id="user-top-bar-links">
        <span class="top-bar-span" id="search-span">
            <i class="fas fa-search" id="search-icon"></i>
        </span>
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
                <a href='<c:url value="/welcome-view.jsp"/>' id="welcome-link">
                    What's New
                </a>
            </span>
            <span class="toggle-down-span">
                <a href='<c:url value="/user-profile?user-id=${loggedUser.userID}"/>' id="profile-link">
                    My Profile
                </a>
            </span>
            <span class="toggle-down-span">
                <a href='<c:url value="/blog-view?authorID=${loggedUser.userID}"/>'>
                     My Blog
                </a>
            </span>
            <span class="toggle-down-span">
                <a href='<c:url value="/add-article-view.jsp"/>'>
                    Create new article
                </a>
            </span>
            <span class="toggle-down-span">
                <a href='<c:url value="/logout"/>' id="logout-link">
                    Log Out
                </a>
            </span>
        </div>

        <div id="search-box" class="invisible">
            <form action='<c:url value="/article-search"/>' method="post" id="search-form">
                <label for="search-keyword">Enter your keyword: </label>
                <input type="text" id="search-keyword" name="search-keyword" required>
                <button type="submit" id="search-button">Article Search</button>
            </form>
        </div>
    </div>
</div>