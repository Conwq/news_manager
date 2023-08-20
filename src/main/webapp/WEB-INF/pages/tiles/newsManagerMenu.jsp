<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}"/>
<fmt:setBundle basename="locales.locale" var="loc"/>

<fmt:message bundle="${loc}" key="locale.manager_menu.button.add_news" var="add_news_button"/>
<fmt:message bundle="${loc}" key="locale.manager_menu.button.delete_selected_news" var="delete_selected_button"/>
<fmt:message bundle="${loc}" key="locale.manager_menu.button.go_to_news_list" var="news_list_button"/>
<fmt:message bundle="${loc}" key="locale.manager_menu.text.administration_menu" var="menu_text"/>
<fmt:message bundle="${loc}" key="locale.manager_menu.button.search" var="search_button"/>
<fmt:message bundle="${loc}" key="locale.manager_menu.text.enter_text_for_search" var="text_for_search"/>

<c:set value="${sessionScope.role}" var="role"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/search-form.css"/>"/>

<c:set value="${sessionScope.role}" var="role"/>

<h2>${menu_text}</h2>
<a href="${pageContext.request.contextPath}/news/goToNewsList">
    <button id="allNewsListButton" type="button">${news_list_button}</button>
</a>

<c:if test="${role eq 'admin'}">
    <a href="${pageContext.request.contextPath}/news/goToAddNewsPage">
        <button id="addNewsButton" type="button">${add_news_button}</button>
    </a>
    <c:if test="${action eq 'newsList'}">
        <button id="deleteNewsButton" form="selectedNews" type="submit">${delete_selected_button}</button>
    </c:if>
</c:if>

<c:if test="${action eq 'newsList'}">
    <div class="search-form">
        <form action="${pageContext.request.contextPath}/news/getSearchResult" method="get">
            <label>
                <input type="text" name="value" placeholder="${text_for_search}"/>
            </label>

            <button type="submit">${search_button}</button>
        </form>
    </div>
</c:if>
