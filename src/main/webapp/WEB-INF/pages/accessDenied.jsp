<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="locale.access_denied_page.text.access_denied" var="access_denied"/>
<spring:message code="locale.access_denied_page.text.permission_access" var="permission_access"/>
<spring:message code="locale.access_denied_page.text.contact_administrator" var="contact_administrator"/>
<spring:message code="locale.access_denied_page.text.come_back" var="come_back"/>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>${access_denied}</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      text-align: center;
      margin: 0;
      padding: 0;
      background-color: #f4f4f4;
    }

    .container {
      max-width: 600px;
      margin: 100px auto;
      padding: 20px;
      background-color: #ffffff;
      border: 1px solid #ccc;
      border-radius: 5px;
    }

    .error-message {
      font-size: 24px;
      color: #e74c3c;
      margin-bottom: 20px;
    }

    .return-button {
      display: inline-block;
      padding: 10px 20px;
      background-color: #3498db;
      color: #ffffff;
      text-decoration: none;
      border-radius: 5px;
      transition: background-color 0.3s ease;
    }

    .return-button:hover {
      background-color: #2980b9;
    }
  </style>
</head>
<body>
<div class="container">
  <h1 class="error-message">${access_denied}</h1>
  <p>${permission_access}</p>
  <p>${contact_administrator}</p>
  <a class="return-button" onclick="window.history.back()">${come_back}</a>
</div>
</body>
</html>
