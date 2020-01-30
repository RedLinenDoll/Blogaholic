<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<br>
<div id="visitor-top-bar" class="top-bar">
    <div class="top-bar-home-link">
        <a href='<c:url value="/"/>'>
            Blogaholic!
        </a>
    </div>
    <div class="top-bar-links" id="visitor-top-bar-links">
        <span id="create-blog-span">
            <a href='<c:url value="/signup.html"/>' class="primary-link">
                Create My Blog
            </a>
        </span>
        <span id="log-in-span">
            <a href='<c:url value="/login.html"/>'>
                Log In
            </a>
        </span>
    </div>
</div>