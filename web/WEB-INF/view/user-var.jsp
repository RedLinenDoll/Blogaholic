<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<br>
<%-- visitor's top bar --%>
<div id="visitor-top-bar" class="top-bar">
    <a href='<c:url value="/logout"/>'><span id="log-out-button">Log Out</span></a>
    <a href='<c:url value="/blog?authorID=${user.userID}"/>'><span id="my-blog-button">My Blog</span></a>
</div>