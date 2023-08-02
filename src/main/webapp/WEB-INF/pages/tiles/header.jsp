<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>News manager</h1>
<c:if test="${sessionScope.active eq 'true'}">
  <form action="${pageContext.request.contextPath}/news/doSignOut" id="signOutButton">
    <button type="submit">Sign Out</button>
  </form>
</c:if>

<c:if test="${not (sessionScope.active eq 'true')}">
  <form id="loginForm" action="${pageContext.request.contextPath}/news/doSignIn" method="post">
    <input type="text" name="username" placeholder="Enter username" required>
    <input type="text" name="password" placeholder="Enter password" required>
    <button type="submit">Sign In</button>
    <a href="${pageContext.request.contextPath}/news/goToRegistrationPage">If you not registered click here</a>
  </form>
</c:if>

<div id="languageButtons">
  <form action="#" method="get">
    <button>EN</button>
  </form>
  <form action="#" method="get">
    <button>RU</button>
  </form>
</div>