<%@page import="org.springframework.ui.Model"%>
<%@page import="org.jboss.logging.Param"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ page isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}"/>
<fmt:setBundle basename="locales.locale" var="loc"/>

<c:set value="${sessionScope.user.login}" var="login"/>
<c:set value="${sessionScope.role}" var="role"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/comments.css"/>"/>

<div class="comments-container">

	<c:if test="${empty comments}">
		<div class="comment">
        	<p>No comments. Be first.</p>
    	</div>
	</c:if>
	
    <c:forEach items="${comments}" var="comment">
    	<div class="comment">
        	<div class="username">${comment.username}</div>
        	<div class="date">${comment.publicationDate}</div>
       		<div class="content">${comment.text}</div>
       		
       		<c:if test="${(role eq 'admin') or (comment.username eq login)}"> 
       			<form action="doDeleteComment?commentId=${comment.id}" method="post">
       				<input type="hidden" value="${news.id}" name="newsId"/>
    				<button class="delete_button" type="submit">Delete</button>
       			</form>
    		</c:if>
    		
    		<c:if test="${comment.username eq login}">
    			<form action="goToEditComment" method="post">
    				<input type="hidden" value="${comment.id}" name="commentId"/>
    				<input type="hidden" value="${news.id}" name="newsId"/>
    				
    				<button class="edit_button" type="submit">Edit</button>
    			</form>
    		</c:if>
    	</div>
    </c:forEach>
	
    <div class="comment-form">
	    <form action="doAddComment" method="post">
	    	<input type="hidden" value="${news.id}" name="newsId"/>
	    	
	    	<label for="new-comment">Enter your comment:</label>
        	<textarea id="new-comment" name="text"></textarea>
        	<button class="submit-button" type="submit">Submit</button>
	    </form>
    </div>
</div>