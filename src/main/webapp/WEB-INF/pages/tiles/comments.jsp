<%@page import="org.springframework.ui.Model"%>
<%@page import="org.jboss.logging.Param"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ page isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}"/>
<fmt:setBundle basename="locales.locale" var="loc"/>

<style>
    .comments-container {
        flex: 0.3;
        margin-left: 20px;
    }

    .comment {
        background-color: #f9f9f9;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 5px;
        margin-bottom: 15px;
        position: relative; 
    }

    .comment .username {
        font-weight: bold;
        margin-bottom: 5px;
    }

    .comment .date {
        font-size: 12px;
        color: #888;
        margin-bottom: 5px;
    }

    .comment .content {
        word-wrap: break-word;
    }

    .comment-form {
        margin-top: 20px;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 5px;
        background-color: #f9f9f9;
    }

    .comment-form label {
        font-weight: bold;
        display: block;
        margin-bottom: 5px;
    }

    .comment-form textarea {
        width: 98%;
        height: 100px;
        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #ddd;
        border-radius: 3px;
        resize: vertical;
    }

    .comment-form .submit-button {
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        background-color: #007bff;
        color: #fff;
        cursor: pointer;
    }

    .comment-form .submit-button:hover {
        background-color: #0056b3;
    }

    .comment-form .submit-button:focus {
        outline: none;
    }
    
    .delete_button {
        background-color: #c0392b;
        color: #fff;
        padding: 5px 10px; 
        border: none;
        border-radius: 5px;
        cursor: pointer;
        float: right; 
        margin-top: -5px; 
    }

    .delete_button:hover {
        background-color: #e74c3c;
    }

    .delete_button:focus {
        outline: none;
    }
    
    .edit_button {
        background-color: #333;
        color: #fff;
        padding: 5px 10px; 
        border: none;
        border-radius: 5px;
        cursor: pointer;
        float: right;
        margin-top: -5px;
        margin-right: 5px;
    }

    .edit_button:hover {
        background-color: #555;
    }

    .edit_button:focus {
        outline: none;
    }
</style>

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
       		
       		<c:if test="${(sessionScope.role eq 'admin') or (comment.username eq sessionScope.user.login)}"> 
       			<form action="doDeleteComment?commentId=${comment.id}" method="post">
       				<input type="hidden" value="${news.id}" name="newsId"/>
    				<button class="delete_button" type="submit">Delete</button>
       			</form>
    		</c:if>
    		
    		<c:if test="${comment.username eq sessionScope.user.login}">
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
        	<textarea id="new-comment" name="text">
				
        	</textarea>
        	<button class="submit-button" type="submit">Submit</button>
	    </form>
    </div>
</div>