<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
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
    
  </header>

  <main>
    
    <div id="newsManagementMenu">
      	<c:if test="${not (sessionScope.action eq 'true')}"> 
      		<h2>Guest! Welcome in news manager!</h2>
      	</c:if>
      	
      	<c:if test="${sessionScope.action eq 'true'}">
      	  <h2>News Management Menu</h2>
	      <button id="allNewsListButton">Go to news list</button>
	      <c:if test="${sessionScope.role eq 'admin'}"> 
		      <button id="addNewsButton">Add News</button>
		      <button id="deleteNewsButton">Delete News</button>
		      <button id="listAllUsersButton">List All Users</button>
	      </c:if>
      	</c:if>
    </div>

    <div id="newsList">
      <h2>Latest News</h2> <hr>
      <div class="news-item">
        <h3>News Title 1</h3>
        <p>Published on: August 2, 2023</p>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
        <c:if test="${sessionScope.role eq 'admin'}">
	        <a href="news/doEditNews">
	        	<button type="submit">Edit</button>
	        </a>
	        <a href="news/viewNews">
	        	<button type="submit">View news</button>
	        </a>
        </c:if>
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
    </div>
  </main>
  
  <footer>
    <p>All privileges reserved@</p>
  </footer>
</body>
</html>
