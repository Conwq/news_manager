<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}"/>
<fmt:setBundle basename="locales.locale" var="loc"/>

<fmt:message bundle="${loc}" key="locale.header.button.en" var="en_button"/>
<fmt:message bundle="${loc}" key="locale.header.button.ru" var="ru_button"/>
<fmt:message bundle="${loc}" key="locale.header.button.sign_in" var="sign_in_button"/>
<fmt:message bundle="${loc}" key="locale.header.button.sign_out" var="sign_out_button"/>
<fmt:message bundle="${loc}" key="locale.header.text.news_manager" var="news_manager_text"/>
<fmt:message bundle="${loc}" key="locale.header.text_form.enter_password" var="password_text"/>
<fmt:message bundle="${loc}" key="locale.header.text_form.enter_username" var="username_text"/>

<c:set value="${sessionScope.active}" var="active"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/header.css"/>"/>

<h1>${news_manager_text}</h1>
<c:if test="${active eq 'true'}">
    <form id="signOutButton" action="${pageContext.request.contextPath}/user/doSignOut" method="post">
        <button type="submit">${sign_out_button}</button>
    </form>
</c:if>

<c:if test="${not (active eq 'true')}">
    <form id="loginForm" action="${pageContext.request.contextPath}/user/doSignIn" method="post">
        <label>
            <input type="text" name="username" placeholder="${username_text}">
        </label>
        <label>
            <input type="text" name="password" placeholder="${password_text}">
        </label>

        <button type="submit">${sign_in_button}</button>
    </form>
</c:if>

<div id="languageButtons">
    <form action="${pageContext.request.contextPath}/news/changeLocale" method="get">
        <input type="hidden" name="localization" value="en">
        <button type="submit">${en_button}</button>
    </form>
    <form action="${pageContext.request.contextPath}/news/changeLocale" method="get">
        <input type="hidden" name="localization" value="ru"/>
        <button type="submit">${ru_button}</button>
    </form>
</div>