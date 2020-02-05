<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript" src='<c:url value="/js/load-search-bar.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/responsive-layout.js"/>'></script>
<script type="text/javascript">
    window.addEventListener("load", function () {
        document.querySelector("#search-icon").addEventListener("click", loadSearchBar);
    });
</script>

<br>
<div id="visitor-top-bar" class="top-bar">
    <div class="top-bar-home-link">
        <a href='<c:url value="/"/>'>
            <span id="home-icon"><i class="fas fa-home"></i></span>
            <span id="home-name">Blogaholic!</span>
        </a>
    </div>
    <div class="top-bar-links" id="visitor-top-bar-links">
        <span class="top-bar-span" id="search-span">
            <i class="fas fa-search" id="search-icon"></i>
        </span>|<span id="create-blog-span">
            <a href='<c:url value="/signup.html"/>' class="primary-link">
                Create My Blog
            </a>
        </span>
        <span id="log-in-span" style="padding-left:5px">
            <a href='<c:url value="/login.html"/>'>
                Log In
            </a>
        </span>

        <div id="search-box" class="invisible">
            <form action='<c:url value="/article-search"/>' method="get" id="search-form">
                <label for="search-keyword">Enter your keyword: </label>
                <input type="text" id="search-keyword" name="search-keyword" required>
                <button type="submit" id="search-button">Article Search</button>
            </form>
        </div>
    </div>
</div>