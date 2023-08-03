<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page isELIgnored="false" %>

<c:choose>
  <c:when test="${empty requestScope.news}">
    No news.
  </c:when>

  <c:otherwise>
  	<h2>Latest News</h2> <hr>
    <c:forEach items="${requestScope.news}" var="news">
      <div class="news-item">
        <h3>${news.title}</h3>
        <p>Published on: ${news.publicationDate}</p>
        <p>${news.brief}</p>

        <c:if test="${sessionScope.role eq 'admin'}">
          <a href="${pageContext.request.contextPath}/news/doEditNews?id=${news.id}">
            <button type="submit">Edit news</button>
          </a>
          <a href="${pageContext.request.contextPath}/news/goToViewNews?id=${news.id}">
            <button type="submit">View news</button>
          </a>
        </c:if>
      </div>
    </c:forEach>
  </c:otherwise>
</c:choose>
