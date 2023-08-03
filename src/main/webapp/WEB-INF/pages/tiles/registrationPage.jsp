<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    #registrationForm {
        max-width: 400px;
        padding: 20px;
        border: 1px solid #ddd;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        background-color: #fff;
        margin: 20px auto;
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
    <h2>Registration Form</h2>
    <form:form modelAttribute="user" action="${pageContext.request.contextPath}/news/doRegistrationUser" method="post">
        <label for="login">Enter login:</label>
        <form:input path="login" id="login"/>
        <br>
        <label for="email">Enter email:</label>
        <form:input path="email" id="email"/>
        <br>
        <label for="password">Enter password:</label>
        <form:input path="password" id="password"/>
        <br>
        <label for="confirmPassword">Confirm password:</label>
        <input type="text" id="confirmPassword" name="confirmPassword"/>
        <br>
        <button type="submit">Register</button>
    </form:form>
    <a href="javascript:history.back()" class="back-button">Back</a>
</div>
