<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="locale.registration_page.button.register" var="register_button"/>
<spring:message code="locale.registration_page.text.confirm_password" var="conf_password_text"/>
<spring:message code="locale.registration_page.text.country" var="country_text"/>
<spring:message code="locale.registration_page.text.email" var="email_text"/>
<spring:message code="locale.registration_page.text.login" var="login_text"/>
<spring:message code="locale.registration_page.text.password" var="password_text"/>
<spring:message code="locale.registration_page.text.registration_form" var="registration_form_text"/>
<spring:message code="locale.registration_page.text.rs" var="rs_text"/>
<spring:message code="locale.registration_page.text.us" var="us_text"/>
<spring:message code="locale.button.back" var="back_button"/>

<c:set value="${pageContext.request.contextPath }" var="contextPath"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/registrationPage.css"/>"/>

<style>
    .error-block {
        color: #000;
        background-color: #ffEEEE;
        border: 3px solid #ff0000;
        padding: 8px;
        margin: 16px;
    }
</style>

<div id="registrationForm">
    <h2>${registration_form_text}</h2>
    <form:form modelAttribute="user" action="${contextPath}/user/doRegistrationUser" method="post">
        <form:errors path="*" element="div" cssClass="error-block"/>
        <label for="login">${login_text}</label>
        <form:input path="login" id="login"/>
        <br>
        <label for="email">${email_text}</label>
        <form:input path="email" id="email"/>
        <br>
        <label for="password">${password_text}</label>
        <form:input path="password" id="password"/>
        <br>
        <label for="confirmPassword">${conf_password_text}</label>
        <form:input path="confirmPassword" id="confirmPassword"/>
        <br>

        <label for="localeName">${country_text}</label>
        <form:select path="localeId" id="localeName" name="localeName">
            <form:option value="1" >${rs_text}</form:option>
            <form:option value="2">${us_text}</form:option>
        </form:select>
        <br>

        <button type="submit">${register_button}</button>
    </form:form>
    <a href="javascript:history.back()" class="back-button">${back_button}</a>
</div>
