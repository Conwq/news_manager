<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>


<c:set value="${sessionScope.active}" var="active"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>News manager</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        header h1 {
            margin: 0;
        }

        main {
            display: flex;
            padding: 20px;
        }

        #newsList {
            flex: 1;
            border: 1px solid #ddd;
            padding: 10px;
        }

        #newsManagementMenu {
            flex: 0.2;
            margin-right: 20px;
        }

        #newsManagementMenu h2 {
            margin-top: 0;
            color: #333;
        }

        #newsManagementMenu button {
            display: block;
            width: 100%;
            margin-bottom: 10px;
            padding: 8px 16px;
            border: none;
            background-color: #333;
            color: #fff;
            cursor: pointer;
        }

        #newsManagementMenu button:hover {
            background-color: #555;
        }

        footer {
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 10px;
        }
    </style>
</head>

<body>
<header>
    <c:import url="/WEB-INF/pages/tiles/header.jsp" charEncoding="utf-8"/>
</header>

<main>
    <div id="newsManagementMenu">
        <c:if test="${not (active eq 'true')}">
            <h2>Welcome in news manager!</h2>
        </c:if>

        <c:if test="${active eq 'true'}">
            <c:import url="/WEB-INF/pages/tiles/newsManagerMenu.jsp" charEncoding="utf-8"/>
        </c:if>
    </div>

    <div id="newsList">
        <c:if test="${action eq 'newsList'}">
            <c:import url="/WEB-INF/pages/tiles/newsList.jsp" charEncoding="utf-8"/>
        </c:if>

        <c:if test="${action eq 'registrationPage'}">
            <c:import url="/WEB-INF/pages/tiles/registrationPage.jsp" charEncoding="utf-8">
                <c:param name="user" value="${user}"/>
            </c:import>
        </c:if>

        <c:if test="${action eq 'viewNews'}">
            <c:import url="/WEB-INF/pages/tiles/viewNews.jsp" charEncoding="utf-8">
                <c:param name="news" value="${news}"/>
            </c:import>
        </c:if>

        <c:if test="${action eq 'addNewsPage'}">
            <c:import url="/WEB-INF/pages/tiles/addNewsPage.jsp" charEncoding="utf-8">
                <c:param name="news" value="${news}"/>
            </c:import>
        </c:if>

        <c:if test="${action eq 'editNews'}">
            <c:import url="/WEB-INF/pages/tiles/editNews.jsp" charEncoding="utf-8">
                <c:param name="news" value="news"/>
            </c:import>
        </c:if>
    </div>
</main>

<footer>
    <p>All rights reserved &copy;</p>
</footer>
</body>
</html>
