<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="locale.manager_menu.button.add_news" var="add_news_button"/>
<spring:message code="locale.manager_menu.button.delete_selected_news" var="delete_selected_button"/>
<spring:message code="locale.manager_menu.button.go_to_news_list" var="news_list_button"/>
<spring:message code="locale.manager_menu.text.administration_menu" var="menu_text"/>
<spring:message code="locale.manager_menu.button.search" var="search_button"/>
<spring:message code="locale.manager_menu.text.enter_text_for_search" var="text_for_search"/>

<c:set value="${pageContext.request.contextPath}" var="contextPath"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/search-form.css"/>"/>

<h2>${menu_text}</h2>

<a href="${contextPath}/news/goToNewsList">
    <button id="allNewsListButton" type="button">${news_list_button}</button>
</a>

<security:authorize access="hasRole('ROLE_ADMIN')">
    <c:if test="${action eq 'newsList'}">
	    <a href="${contextPath}/news/admin/goToAddNewsPage">
	        <button id="addNewsButton" type="button">${add_news_button}</button>
	    </a>
	    
        <button id="deleteNewsButton" form="selectedNews" type="submit">${delete_selected_button}</button>
    </c:if>
</security:authorize>

<c:if test="${action eq 'newsList'}">
    <div class="search-form">
        <form action="${contextPath}/news/getSearchResult" method="get">
            <label>
                <input type="text" name="value" placeholder="${text_for_search}"/>
            </label>

            <button type="submit">${search_button}</button>
        </form>
    </div>
</c:if>
