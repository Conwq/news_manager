<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Administration Menu</h2>
<a href="${pageContext.request.contextPath}/news/goToNewsList">
    <button id="allNewsListButton" type="button">Go to news list</button>
</a>
<c:if test="${sessionScope.role eq 'admin'}">
    <a href="${pageContext.request.contextPath}/news/goToAddNewsPage">
        <button id="addNewsButton" type="button">Add News</button>
    </a>
    <c:if test="${action eq 'newsList'}">
        <button id="deleteNewsButton" form="selectedNews" type="submit">Delete Selected News</button>
    </c:if>
</c:if>