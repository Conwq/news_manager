<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" pageEncoding="UTF-8" %>

<c:set value="${sessionScope.role}" var="role"/>
<c:set value="${requestScope.news}" var="news"/>

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
        color: white;
        padding: 4px 8px;
        border: none;
        cursor: pointer;
        border-radius: 5px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 10px;
        margin: 4px 2px;
    }
</style>

<c:choose>
    <c:when test="${empty news}">
        No news.
    </c:when>

    <c:otherwise>
        <h2>Latest News</h2>
        <hr>
        <form action="${pageContext.request.contextPath}/news/doDeleteSomeNews" id="selectedNews" method="post">
            <c:forEach items="${news}" var="news">
                <div class="news-item">
                    <h3>${news.title}</h3>
                    <p class="published-date">Published on: ${news.publicationDate}</p>
                    <p>${news.brief}</p>

                    <a href="${pageContext.request.contextPath}/news/goToViewNews?id=${news.id}">
                        <button type="button" class="btn-style">View news</button>
                    </a>&nbsp;

                    <c:if test="${role eq 'admin'}">
                        <a href="${pageContext.request.contextPath}/news/goToEditNews?id=${news.id}">
                            <button type="button" class="btn-style">Edit news</button>
                        </a>&nbsp;
                        <a href="${pageContext.request.contextPath}/news/doDeleteNews?id=${news.id}">
                            <button type="button" class="btn-style">Delete news</button>
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
