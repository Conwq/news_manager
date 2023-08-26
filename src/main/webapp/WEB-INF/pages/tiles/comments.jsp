<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ page isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="locale.comment.button.delete" var="delete"/>
<spring:message code="locale.comment.button.edit" var="edit"/>
<spring:message code="locale.comment.button.enter_commit" var="enterCommit"/>
<spring:message code="locale.comment.button.submit" var="submit"/>
<spring:message code="locale.comment.text.no_comment" var="no_comment"/>
<spring:message code="locale.comment.text.write_comment" var="write_comment"/>

<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<c:set value="${pageContext.request.userPrincipal}" var="principal"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/comments.css"/>"/>

<div class="comments-container">
	<c:if test="${empty comments}">
		<div class="comment">
        	<p>${no_comment}</p>
    	</div>
	</c:if>
	
    <c:forEach items="${comments}" var="comment">
    	<div class="comment">
        	<div class="username">${comment.username}</div>
        	<div class="date">${comment.publicationDate}</div>
       		<div class="content">${comment.text}</div>
       		
       		<security:authorize access="(hasRole('ROLE_ADMIN')) or (#comment.username eq #principal.name)">
       			<form action="${contextPath}/news/doDeleteComment" method="post">
					<input type="hidden" value="${comment.id}" name="commentId"/>
       				<input type="hidden" value="${news.id}" name="newsId"/>
    				<button class="delete_button" type="submit">${delete}</button>
       			</form>
       		</security:authorize>
       		
    		<c:if test="${comment.username eq principal.name}">
    			<form action="${contextPath}/news/goToEditComment" method="get">
    				<input type="hidden" value="${comment.id}" name="commentId"/>
    				<input type="hidden" value="${news.id}" name="newsId"/>
    				
    				<button class="edit_button" type="submit">${edit}</button>
    			</form>
    		</c:if>
    	</div>
    </c:forEach>

	<c:if test="${not empty text}">
		<div class="comment-form">
			<form action="${contextPath}/news/doEditComment" method="post">
				<input type="hidden" value="${news.id}" name="newsId"/>
				<input type="hidden" value="${commentId}" name="commentId"/>

				<label for="new-comment">${enterCommit}</label>
				<textarea id="new-comment" name="text">${text}</textarea>

				<button class="submit-button" type="submit">${submit}</button>
			</form>
		</div>
	</c:if>

	<c:if test="${empty text}">
		<div class="comment-form">
			<form action="${contextPath}/news/doAddComment" method="post">
				<input type="hidden" value="${news.id}" name="newsId"/>
				<input type="hidden" value="${principal.name}" name="username"/>

				<label for="new-comment">${enterCommit}</label>
				<textarea id="new-comment" name="text" placeholder="${write_comment}"></textarea>

				<button class="submit-button" type="submit">${submit}</button>
			</form>
		</div>
	</c:if>
</div>