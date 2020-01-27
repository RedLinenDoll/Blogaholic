<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="user-top-bar" class="top-bar">
    <div class="top-bar-home-link">
        <a href='<c:url value="/"/>'>
            JAVA Team Blog
        </a>
    </div>
    <div class="top-bar-links" id="user-top-bar-links">
        <span id="my-blog-span" class="top-bar-span">
            <a class="primary-link" href='<c:url value="/blog-view?authorID=${loggedUser.userID}"/>'>
                <img class="inline-avatar" src='<c:url value="/images/avatar/${loggedUser.avatarPath}"/>'/>'
                     alt="${loggedUser.username}'s avatar">
                My Blog
            </a>
        </span>
        <span id="log-out-span" class="top-bar-span">
            <a href='<c:url value="/logout"/>'>
                Log Out
            </a>
        </span>
    </div>
</div>