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

<style>
    #registrationForm {
        max-width: 400px;
        padding: 20px;
        border: 1px solid #ddd;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        background-color: #fff;
        margin: 15px auto;
    }

    #registrationForm h2 {
        margin-top: 0;
        margin-bottom: 20px;
        color: #333;
    }

    #registrationForm label {
        display: block;
        margin-bottom: 8px;
        color: #333;
    }

    #registrationForm select {
        width: 100%;
        padding: 12px;
        margin-bottom: 15px;
        border: 1px solid #ddd;
        border-radius: 5px;
        display: block;
        box-sizing: border-box;
    }

    #registrationForm input[type="text"],
    #registrationForm input[type="email"],
    #registrationForm input[type="password"] {
        width: 100%;
        padding: 12px;
        margin-bottom: 15px;
        border: 1px solid #ddd;
        border-radius: 5px;
        display: block;
        box-sizing: border-box;
    }

    #registrationForm button {
        display: block;
        width: 100%;
        padding: 10px;
        background-color: #333;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    #registrationForm button:hover {
        background-color: #555;
    }

    #registrationForm .back-button {
        display: inline-block;
        background-color: #555;
        color: #fff;
        border: none;
        padding: 10px 20px;
        text-decoration: none;
        border-radius: 5px;
        margin-top: 10px;
    }

    #registrationForm .back-button:hover {
        background-color: #333;
    }
</style>

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

        <label for="country">${country_text}</label>
        <select id="country" name="country">
            <option value="ru">${rs_text}</option>
            <option value="us">${us_text}</option>
        </select>
        <br>

        <button type="submit">${register_button}</button>
    </form:form>
    <a href="javascript:history.back()" class="back-button">${back_button}</a>
</div>
