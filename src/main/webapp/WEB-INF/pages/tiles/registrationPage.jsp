<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form modelAttribute="">
    Enter login: <br>
    <form:input path="login" id="login"/> <br><br>

    Enter email: <br>
    <form:input path="email" id="email"/> <br><br>

    Enter password: <br>
    <form:input path="password" id="password"/> <br><br>

    <input type="submit" value="Submit"/>
</form:form>