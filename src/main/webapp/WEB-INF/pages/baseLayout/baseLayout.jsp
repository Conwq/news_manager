<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<c:set value="${requestScope.action}" var="action"/>
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

    form {
      display: flex;
    }

    form label, form input {
      margin-right: 10px;
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

    .news-item {
      margin-bottom: 15px;
      border-bottom: 1px solid #ddd;
      padding-bottom: 10px;
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

    #languageButtons {
      display: flex;
    }

    #languageButtons button {
      margin-right: 5px;
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
<<<<<<< HEAD
      <c:import url="/WEB-INF/pages/tiles/header.jsp" charEncoding="utf-8"/>
=======
    <h1>News manager</h1>
    <c:if test="${sessionScope.active eq 'true'}">
    	<form action="news/doSignOut" id="signOutButton">
    		<button type="submit">Sign Out</button>
    	</form>
    </c:if>
    
    <c:if test="${not (sessionScope.active eq 'true')}"> 
	    <form id="loginForm" action="news/doSignIn" method="post">
	      <input type="text" name="username" placeholder="Enter username" required>
	      <input type="password" name="password" placeholder="Enter password" required>
	      <button type="submit">Sign In</button>
	      <a href="news/goToRegistrationPage">If you not registered click here</a>
	    </form>
    </c:if>
    
    <div id="languageButtons">
		<form id = "changeLocaleEn" action="#" method="get">
			<button id="changeLocaleEN" >EN</button>
		</form>    
		<form id = "changeLocaleEn" action="#" method="get">
			 <button id="changeLocaleRU">RU</button>
		</form>    
    </div>
    
>>>>>>> 52a4ebf7500afe41e91e70f38529019d964cb04d
  </header>

  <main>
    <div id="newsManagementMenu">
      	<c:if test="${not (active eq 'true')}">
      		<h2>Guest! Welcome in news manager!</h2>
      	</c:if>

      	<c:if test="${active eq 'true'}">
      	     <c:import url="/WEB-INF/pages/tiles/newsManagerMenu.jsp" charEncoding="utf-8"/>
      	</c:if>
    </div>

    <div id="newsList">
      <h2>Latest News</h2> <hr>
        <c:if test="${action eq 'newsList'}">
            <c:import url="/WEB-INF/pages/tiles/newsList.jsp" charEncoding="utf-8"/>
        </c:if>
<<<<<<< HEAD

        <c:if test="${action eq 'registrationPage'}">
            <c:import url="/WEB-INF/pages/tiles/registrationPage.jsp" charEncoding="utf-8"/>
        </c:if>

        <c:if test="${action eq 'viewNews'}">
            <c:import url="/WEB-INF/pages/tiles/viewNews.jsp" charEncoding="utf-8"/>
        </c:if>

=======
      </div>
      <div class="news-item">
        <h3>News Title 2</h3>
        <p>Published on: August 1, 2023</p>
        <p>Quisque sit amet ipsum quis elit finibus iaculis nec id ligula.</p>
        <a href="news/doEditNews">
        	<button type="submit">Edit</button>
        </a>
        <a href="news/viewNews">
        	<button type="submit">View news</button>
        </a>
      </div>
      <div class="news-item">
        <h3>News Title 2</h3>
        <p>Published on: August 1, 2023</p>
        <p>Quisque sit amet ipsum quis elit finibus iaculis nec id ligula.</p>
        <a href="news/doEditNews">
        	<button type="submit">Edit</button>
        </a>
        <a href="news/viewNews">
        	<button type="submit">View news</button>
        </a>
      </div>
      <div class="news-item">
        <h3>News Title 2</h3>
        <p>Published on: August 1, 2023</p>
        <p>Quisque sit amet ipsum quis elit finibus iaculis nec id ligula.</p>
        <a href="news/doEditNews">
        	<button type="submit">Edit</button>
        </a>
        <a href="news/viewNews">
        	<button type="submit">View news</button>
        </a>
      </div>
      <div class="news-item">
        <h3>News Title 2</h3>
        <p>Published on: August 1, 2023</p>
        <p>Quisque sit amet ipsum quis elit finibus iaculis nec id ligula.</p>
        <a href="news/doEditNews">
        	<button type="submit">Edit</button>
        </a>
        <a href="news/viewNews">
        	<button type="submit">View news</button>
        </a>
      </div>
      <div class="news-item">
        <h3>News Title 2</h3>
        <p>Published on: August 1, 2023</p>
        <p>Quisque sit amet ipsum quis elit finibus iaculis nec id ligula.</p>
        <a href="news/doEditNews">
        	<button type="submit">Edit</button>
        </a>
        <a href="news/viewNews">
        	<button type="submit">View news</button>
        </a>
      </div>
>>>>>>> 52a4ebf7500afe41e91e70f38529019d964cb04d
    </div>
  </main>

  <footer>
    <p>All privileges reserved@</p>
  </footer>
</body>
</html>
