<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}"/>
<fmt:setBundle basename="locales.locale" var="loc"/>

<fmt:message bundle="${loc}" key="locale.add_news.text.enter_title" var="title_text"/>
<fmt:message bundle="${loc}" key="locale.add_news.text.enter_brief" var="brief_text"/>
<fmt:message bundle="${loc}" key="locale.add_news.text.enter_content" var="content_text"/>
<fmt:message bundle="${loc}" key="locale.add_news.button.create" var="create_button"/>
<fmt:message bundle="${loc}" key="locale.button.back" var="back_button"/>
<fmt:message bundle="${loc}" key="locale.add_news.text.image" var="image_text"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/addNewsPage.css"/>"/>

<div class="create-news-page">
    <form:form modelAttribute="news" action="${pageContext.request.contextPath}/news/doAddNews" method="post" enctype="multipart/form-data">
        <label for="title">${title_text}</label>
        <form:input id="title" path="title"/>

        <label for="brief">${brief_text}</label>
        <form:input id="brief" path="brief"/>

        <label for="content">${content_text}</label>
        <form:textarea id="content" path="content"/>
        
        <label for="image">${image_text}</label>
        <input type="file" id="image" name="image" accept="image/*"/>

        <div class="buttons-create-news-page-form">
            <button class="create-button" type="submit">${create_button}</button>
            <button class="back-button" onclick="window.history.back()" type="button">${back_button}</button>
        </div>
    </form:form>
</div>