<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}"/>
<fmt:setBundle basename="locales.locale" var="loc"/>

<fmt:message bundle="${loc}" key="locale.registration_page.button.register" var="register_button"/>
<fmt:message bundle="${loc}" key="locale.registration_page.text.confirm_password" var="conf_password_text"/>
<fmt:message bundle="${loc}" key="locale.registration_page.text.country" var="country_text"/>
<fmt:message bundle="${loc}" key="locale.registration_page.text.email" var="email_text"/>
<fmt:message bundle="${loc}" key="locale.registration_page.text.login" var="login_text"/>
<fmt:message bundle="${loc}" key="locale.registration_page.text.password" var="password_text"/>
<fmt:message bundle="${loc}" key="locale.registration_page.text.registration_form" var="registration_form_text"/>
<fmt:message bundle="${loc}" key="locale.registration_page.text.rs" var="rs_text"/>
<fmt:message bundle="${loc}" key="locale.registration_page.text.us" var="us_text"/>
<fmt:message bundle="${loc}" key="locale.button.back" var="back_button"/>


<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/registrationPage.css"/>"/>

<div id="registrationForm">
    <h2>${registration_form_text}</h2>
    <form:form modelAttribute="user" action="${pageContext.request.contextPath}/news/doRegistrationUser" method="post">
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
        <input type="text" id="confirmPassword" name="confirmPassword"/>
        <br>

        <label for="localeName">${country_text}</label>
        <select id="localeName" name="localeName">
            <option value="ru_RU">${rs_text}</option>
            <option value="en_US">${us_text}</option>
        </select>
        <br>

        <button type="submit">${register_button}</button>
    </form:form>
    <a href="javascript:history.back()" class="back-button">${back_button}</a>
</div>
