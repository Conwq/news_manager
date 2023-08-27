<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ page isELIgnored="false" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="locale.edit_news.button.save" var="save_button" />
<spring:message code="locale.edit_news.text.brief_news" var="brief_text"/>
<spring:message code="locale.edit_news.text.content" var="content_text"/>
<spring:message code="locale.edit_news.text.edit_news" var="edit_text"/>
<spring:message code="locale.edit_news.text.image" var="image_text"/>
<spring:message code="locale.edit_news.text.title" var="title_text"/>
<spring:message code="locale.button.back" var="back_button"/>

<c:set value="${pageContext.request.contextPath}" var="contextPath"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/editNews.css"/>"/>

<div class="edit-news-form">
    <h2>${edit_text}</h2>
    <form:form action="${contextPath}/news/admin/doEditNews" method="post" modelAttribute="news"
               enctype="multipart/form-data">
        <form:hidden path="id" value="${news.id}"/>
        <form:hidden path="publicationDate" value="${news.publicationDate}"/>
        <form:hidden path="imagePath" value="${news.imagePath}"/>

        <label for="title">${title_text}</label>
        <form:input path="title" id="title" value="${news.title}"/>

        <label for="brief">${brief_text}</label>
        <form:input path="brief" id="brief" value="${news.brief}"/>

        <label for="content">${content_text}</label>
        <form:textarea path="content" id="content" value="${news.content}"/>

        <label for="image">${image_text}</label>
        <input type="file" id="image" name="image" accept="image/*"/> <br> <br>

        <button type="submit">${save_button}</button>
    </form:form>
    <a href="javascript:history.back()" class="back-button">${back_button}</a>
</div>
