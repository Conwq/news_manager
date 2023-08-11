<%@ page isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.localization}"/>
<fmt:setBundle basename="locales.locale" var="loc"/>

<fmt:message bundle="${loc}" key="locale.view_news.text.publication_date" var="publication_text"/>
<fmt:message bundle="${loc}" key="locale.button.back" var="back_button"/>
<fmt:message bundle="${loc}" key="locale.button.delete_news" var="delete_button"/>
<fmt:message bundle="${loc}" key="locale.button.edit_news" var="edit_button"/>
<fmt:message bundle="${loc}" key="locale.view_news.text.image_news" var="image_news"/>

<c:set value="${sessionScope.role}" var="role"/>


<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/viewNews.css"/>" />

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

    <c:if test="${role eq 'admin'}">
        <div class="action-buttons">
            <a href="${pageContext.request.contextPath}/news/goToEditNews?id=${news.id}">
                <button class="edit">${edit_button}</button>
            </a>
            
            <form action="${pageContext.request.contextPath}/news/doDeleteNews?id=${news.id}" method="post">
           		<button type="submit" class="delete">${delete_button}</button>
            </form>
        </div>
    </c:if>
    <a href="javascript:history.back()" class="back-button">${back_button}</a>
</div>

<c:import url="/WEB-INF/pages/tiles/comments.jsp" charEncoding="utf-8">
	<c:param name="news" value="${news}"/>
	<c:param name="comments" value="${comments}"/>
	<c:param name="text" value="${text}"/>
	<c:param name="commentId" value="${commentId}"/>
</c:import>