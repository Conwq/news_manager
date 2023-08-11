<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}"/>
<fmt:setBundle basename="locales.locale" var="loc"/>

<fmt:message bundle="${loc}" key="locale.news_list.button.view_news" var="view_news_button"/>
<fmt:message bundle="${loc}" key="locale.news_list.text.latest_news" var="latest_news_text"/>
<fmt:message bundle="${loc}" key="locale.news_list.text.no_news" var="no_news_text"/>
<fmt:message bundle="${loc}" key="locale.news_list.text.published_on" var="published_text"/>
<fmt:message bundle="${loc}" key="locale.button.edit_news" var="edit_button"/>
<fmt:message bundle="${loc}" key="locale.button.delete_news" var="delete_button"/>

<c:set value="${sessionScope.role}" var="role"/>
<c:set value="${requestScope.news}" var="news"/>
<c:set value="${sessionScope.active}" var="active"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/newsList.css"/>"/>

<c:choose>
    <c:when test="${empty news}">
        ${no_news_text}
    </c:when>

    <c:otherwise>
        <h2>${latest_news_text}</h2>
        <hr>
        <form action="${pageContext.request.contextPath}/news/doDeleteSomeNews" id="selectedNews" method="post">
            <c:forEach items="${news}" var="news">
                <div class="news-item">
                    <h3>${news.title}</h3>
                    <p class="published-date">${published_text} ${news.publicationDate}</p>
                    <p>${news.brief}</p>

                    <c:if test="${active eq 'true'}">
                        <a href="${pageContext.request.contextPath}/news/goToViewNews?id=${news.id}">
                            <button type="button" class="btn-style">${view_news_button}</button>
                        </a>&nbsp;
                    </c:if>

                    <c:if test="${role eq 'admin'}">
                        <a href="${pageContext.request.contextPath}/news/goToEditNews?id=${news.id}">
                            <button type="button" class="btn-style">${edit_button}</button>
                        </a>&nbsp;
                        <a href="${pageContext.request.contextPath}/news/doDeleteNews?id=${news.id}">
                            <button type="button" class="btn-style">${delete_button}</button>
                        </a>&nbsp;
                        <label>
                            <input formaction="selected" type="checkbox" name="news" value="${news.id}"/>
                        </label>&nbsp;
                    </c:if>
                </div>
            </c:forEach>
        </form>
    </c:otherwise>
</c:choose>
