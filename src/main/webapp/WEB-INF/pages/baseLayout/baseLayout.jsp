<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<fmt:setLocale value="${sessionScope.localization}"/>
<fmt:setBundle basename="locales.locale" var="loc"/>

<fmt:message bundle="${loc}" key="locale.manager_menu.text.welcome_in_news_manager" var="welcome_text"/>
<fmt:message bundle="${loc}" key="locale.manager_menu.text.registration" var="registration_text"/>
<fmt:message bundle="${loc}" key="locale.header.text.news_manager" var="news_manager_text"/>
<fmt:message bundle="${loc}" key="locale.footer.text.reserved" var="fot"/>

<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<c:set value="${pageContext.request.userPrincipal}" var="principal"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>News</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/baseLayout.css"/>"/>
</head>

<body>
    <header>
        <c:import url="/WEB-INF/pages/tiles/header.jsp" charEncoding="utf-8"/>
    </header>

    <main>
        <div id="newsManagementMenu">
            <c:if test="${principal == null}">
                <h2>${welcome_text}</h2>
                <a href="${contextPath}/user/goToRegistrationPage">${registration_text}</a>
            </c:if>

            <c:if test="${principal != null}">
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
                    <c:param name="comments" value="${comments}"/>
                    <c:param name="text" value="${text}"/>
                    <c:param name="commentId" value="${commentId}"/>
                    <c:param name="tags" value="${tags}"/>
                </c:import>
            </c:if>

            <c:if test="${action eq 'addNewsPage'}">
                <c:import url="/WEB-INF/pages/tiles/addNewsPage.jsp" charEncoding="utf-8">
                    <c:param name="news" value="${news}"/>
                    <c:param name="tags" value="${tags}"/>
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
        ${text}
        <p>${fot}&copy;</p>
    </footer>
</body>
</html>
