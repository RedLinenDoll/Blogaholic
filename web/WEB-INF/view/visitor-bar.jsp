<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<br>
<%-- visitor's top bar --%>
<div id="visitor-top-bar" class="top-bar">
    <a href='<c:url value="/signup"/>'><span id="create-blog-button">Create My Blog</span></a>|
    <a href='<c:url value="/login"/>'><span id="log-in-button">Log In</span></a>
</div>