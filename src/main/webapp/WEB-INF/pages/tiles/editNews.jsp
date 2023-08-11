<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ page isELIgnored="false" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}"/>
<fmt:setBundle basename="locales.locale" var="loc"/>

<fmt:message bundle="${loc}" key="locale.edit_news.button.save" var="save_button"/>
<fmt:message bundle="${loc}" key="locale.edit_news.text.brief_news" var="brief_text"/>
<fmt:message bundle="${loc}" key="locale.edit_news.text.content" var="content_text"/>
<fmt:message bundle="${loc}" key="locale.edit_news.text.edit_news" var="edit_text"/>
<fmt:message bundle="${loc}" key="locale.edit_news.text.title" var="title_text"/>
<fmt:message bundle="${loc}" key="locale.button.back" var="back_button"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/editNews.css"/>"/>

<div class="edit-news-form">
    <h2>${edit_text}</h2>
    <form:form action="${pageContext.request.contextPath}/news/doEditNews" method="post" modelAttribute="news">
        <form:hidden path="id" value="${news.id}"/>
        <form:hidden path="publicationDate" value="${news.publicationDate}"/>

        <label for="title">${title_text}</label>
        <form:input path="title" id="title" value="${news.title}"/>

        <label for="brief">${brief_text}</label>
        <form:input path="brief" id="brief" value="${news.brief}"/>

        <label for="content">${content_text}</label>
        <form:textarea path="content" id="content" value="${news.content}"/>

        <button type="submit">${save_button}</button>
    </form:form>
    <a href="javascript:history.back()" class="back-button">${back_button}</a>
</div>
