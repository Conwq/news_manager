<%@ page isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<spring:message code="locale.view_news.text.publication_date" var="publication_text"/>
<spring:message code="locale.button.back" var="back_button"/>
<spring:message code="locale.button.delete_news" var="delete_button"/>
<spring:message code="locale.button.edit_news" var="edit_button"/>
<spring:message code="locale.view_news.text.image_news" var="image_news"/>

<c:set value="${pageContext.request.contextPath }" var="contextPath"/>
<c:set value="${pageContext.request.userPrincipal}" var="principal"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/viewNews.css"/>" />

<style>
    .tag-list {
        list-style-type: none;
        margin: 0;
        padding: 0;
    }

    .tag-list li {
        display: inline-block;
        margin-right: 5px;
        background-color: #f2f2f2;
        padding: 3px 8px;
        border-radius: 3px;
    }
</style>

<div class="news-details">
	<span class="publication-date">${publication_text}: ${news.publicationDate}</span> <br>
    <hr>
    <h2 class="title">${news.title}</h2>
    <p class="brief">${news.brief}</p>
    <hr><br>
    <p class="content">${news.content}</p>
    
     <div class="image">
        <img src="<c:url value="${news.imagePath}"/>" alt="${image_news}">
    </div>

    <c:if test="${not empty tags}">
        <ul class="tag-list">
            Tags:
            <c:forEach items="${tags}" var="tag">
                <li>${tag.name}</li>
            </c:forEach>
        </ul>
    </c:if>
    
    <security:authorize access="hasRole('ROLE_ADMIN') and principal != null">
    	<div class="action-buttons">
            <a href="${contextPath}/news/admin/goToEditNews?id=${news.id}">
                <button class="edit">${edit_button}</button>
            </a>
            
            <form action="${contextPath}/news/admin/doDeleteNews?id=${news.id}" method="post">
           		<button type="submit" class="delete">${delete_button}</button>
            </form>
        </div>
    </security:authorize>

    <a href="javascript:history.back()" class="back-button">${back_button}</a>
</div>

<c:import url="/WEB-INF/pages/tiles/comments.jsp" charEncoding="utf-8">
	<c:param name="news" value="${news}"/>
	<c:param name="comments" value="${comments}"/>
	<c:param name="text" value="${text}"/>
	<c:param name="commentId" value="${commentId}"/>
</c:import>