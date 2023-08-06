<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}"/>
<fmt:setBundle basename="locales.locale" var="loc"/>

<fmt:message bundle="${loc}" key="locale.manager_menu.text.welcome_in_news_manager" var="welcome_text"/>
<fmt:message bundle="${loc}" key="locale.manager_menu.text.registration" var="registration_text"/>
<fmt:message bundle="${loc}" key="locale.header.text.news_manager" var="news_manager_text"/>
<fmt:message bundle="${loc}" key="locale.footer.text.reserved" var="fot"/>

<c:set value="${sessionScope.active}" var="active"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>News</title>
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
            height: 80vh;
            overflow: auto;
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
            position: fixed;
            bottom: 0;
            left: 0;
            right: 0;
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
            <h2>${welcome_text}</h2>
            <a href="${pageContext.request.contextPath}/news/goToRegistrationPage">${registration_text}</a>
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
    <p>${fot}&copy;</p>
</footer>
</body>
</html>
