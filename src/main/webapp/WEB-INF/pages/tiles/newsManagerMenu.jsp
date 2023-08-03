<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Admin Menu</h2>
<button id="allNewsListButton">Go to news list</button>
<c:if test="${sessionScope.role eq 'admin'}">
  <button id="addNewsButton">Add News</button>
  <button id="deleteNewsButton">Delete News</button>
  <button id="listAllUsersButton">List All Users</button>
</c:if>