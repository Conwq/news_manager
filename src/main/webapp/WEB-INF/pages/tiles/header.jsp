<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="locale.header.button.en" var="en_button"/>
<spring:message code="locale.header.button.ru" var="ru_button"/>
<spring:message code="locale.header.button.sign_in" var="sign_in_button"/>
<spring:message code="locale.header.button.sign_out" var="sign_out_button"/>
<spring:message code="locale.header.text.news_manager" var="news_manager_text"/>
<spring:message code="locale.header.text_form.enter_password" var="password_text"/>
<spring:message code="locale.header.text_form.enter_username" var="username_text"/>
<spring:message code="locale.header.text.error_message" var="error_message"/>

<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<c:set value="${pageContext.request.userPrincipal}" var="principal"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/header.css"/>"/>

<h1>${news_manager_text}</h1>

<c:if test="${principal != null}">
    <a href="${contextPath}/logout">
        <button>${sign_out_button}</button>
    </a>
</c:if>

<c:if test="${principal == null}">
    <form id="loginForm" action="${contextPath}/process-authorisation" method="post">
        <label>
            <input type="text" name="username" placeholder="${username_text}">
        </label>
        <label>
            <input type="text" name="password" placeholder="${password_text}">
        </label>

        <button type="submit">${sign_in_button}</button>
        <br>

        <c:if test="${param.error != null}">
            <div style="color:red">${error_message}</div>
        </c:if>
    </form>
</c:if>

<div id="languageButtons">
    <form action="${contextPath}/news/changeLocale" method="get">
        <input type="hidden" name="localization" value="en">
        <button type="submit">${en_button}</button>
    </form>
    <form action="${contextPath}/news/changeLocale" method="get">
        <input type="hidden" name="localization" value="ru"/>
        <button type="submit">${ru_button}</button>
    </form>
</div>