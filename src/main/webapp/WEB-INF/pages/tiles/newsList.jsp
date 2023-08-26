<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ page isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="locale.news_list.button.view_news" var="view_news_button"/>
<spring:message code="locale.news_list.text.latest_news" var="latest_news_text"/>
<spring:message code="locale.news_list.text.no_news" var="no_news_text"/>
<spring:message code="locale.news_list.text.published_on" var="published_text"/>
<spring:message code="locale.button.edit_news" var="edit_button"/>
<spring:message code="locale.button.delete_news" var="delete_button"/>

<c:set value="${requestScope.news}" var="news"/>
<c:set value="${pageContext.request.contextPath }" var="contextPath"/>
<c:set value="${pageContext.request.userPrincipal}" var="principal"/>

<style>
    .news-item {
        margin-bottom: 15px;
        border-bottom: 1px solid #ddd;
        padding-bottom: 10px;
        word-wrap: break-word;
        position: relative;
    }

    .published-date {
        position: absolute;
        top: 0;
        right: 0;
        font-size: 12px;
        color: #888;
    }

    .btn-style {
        background-color: #333;
        color: #fff;
        padding: 5px 10px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        margin-top: -5px;
        margin-right: 5px;
    }

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

<c:choose>
    <c:when test="${empty news}">
        ${no_news_text}
    </c:when>

    <c:otherwise>
        <h2>${latest_news_text}</h2>
        <hr>
        <form action="${contextPath}/news/doDeleteSomeNews" id="selectedNews" method="post">
            <c:forEach items="${news}" var="news">
                <div class="news-item">
                    <h3>${news.title}</h3>
                    <p class="published-date">${published_text} ${news.publicationDate}</p>
                    <p>${news.brief}</p>

                    <c:if test="${not empty news.tags}">
                        <ul class="tag-list">
                            Tags:
                            <c:forEach items="${news.tags}" var="tag">
                                <li>${tag}</li>
                            </c:forEach>
                        </ul>
                    </c:if>

                    <c:if test="${principal != null}">
                        <a href="${contextPath}/news/goToViewNews?id=${news.id}">
                            <button type="button" class="btn-style">${view_news_button}</button>
                        </a>&nbsp;
                    </c:if>
                    
                    <security:authorize access="hasRole('ROLE_ADMIN') and #principal != null">
                    	<a href="${contextPath}/news/goToEditNews?id=${news.id}">
                            <button type="button" class="btn-style">${edit_button}</button>
                        </a>&nbsp;
                        <a href="${contextPath}/news/doDeleteNews?id=${news.id}">
                            <button type="button" class="btn-style">${delete_button}</button>
                        </a>&nbsp;
                        <label>
                            <input formaction="selected" type="checkbox" name="news" value="${news.id}"/>
                        </label>&nbsp;
                    </security:authorize>
                </div>
            </c:forEach>
        </form>
    </c:otherwise>
</c:choose>
