<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- visitor's top bar --%>
<div id="visitor-top-bar" class="top-bar">

    <a href='<c:url value="/blog-view?authorID=${loggedUser.userID}"/>'> <span>
        <img lass="inline-avatar" src='<c:url value="/images/${loggedUser.avatarPath}"/>'
             alt="${loggedUser.userName}'s avatar">
    </span></a>
    <a href='<c:url value="/blog-view?authorID=${loggedUser.userID}"/>'><span id="my-blog-span">My Blog</span></a>|
    <a href='<c:url value="/logout"/>'><span id="log-out-span">Log Out</span></a>

</div>