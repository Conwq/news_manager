<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
    <form id="loginForm">
      <label for="username"></label>
      <input type="text" id="username" name="username" placeholder="Enter username" required>
      <label for="password"></label>
      <input type="password" id="password" name="password" placeholder="Enter password" required>
      <button type="submit">Login</button>
      <a href="#" id="">If you not registered click here</a>
    </form>
    
    <div id="languageButtons">
      <button id="changeLocaleEN">EN</button>
      <button id="changeLocaleRU">RU</button>
    </div>
  </header>

  <main>
    <div id="newsManagementMenu">
      <h2>News Management Menu</h2>
      <button id="addNewsButton">Add News</button>
      <button id="deleteNewsButton">Delete News</button>
      <button id="listAllUsersButton">List All Users</button>
    </div>

    <div id="newsList">
      <h2>Latest News</h2> <hr>
      <div class="news-item">
        <h3>News Title 1</h3>
        <p>Published on: August 2, 2023</p>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
      </div>
      <div class="news-item">
        <h3>News Title 2</h3>
        <p>Published on: August 1, 2023</p>
        <p>Quisque sit amet ipsum quis elit finibus iaculis nec id ligula.</p>
      </div>
      <div class="news-item">
        <h3>News Title 2</h3>
        <p>Published on: August 1, 2023</p>
        <p>Quisque sit amet ipsum quis elit finibus iaculis nec id ligula.</p>
      </div>
      <div class="news-item">
        <h3>News Title 2</h3>
        <p>Published on: August 1, 2023</p>
        <p>Quisque sit amet ipsum quis elit finibus iaculis nec id ligula.</p>
      </div>
      <div class="news-item">
        <h3>News Title 2</h3>
        <p>Published on: August 1, 2023</p>
        <p>Quisque sit amet ipsum quis elit finibus iaculis nec id ligula.</p>
      </div>
      <div class="news-item">
        <h3>News Title 2</h3>
        <p>Published on: August 1, 2023</p>
        <p>Quisque sit amet ipsum quis elit finibus iaculis nec id ligula.</p>
      </div>
    </div>
  </main>
  
  <footer>
    <p>All privileges reserved@</p>
  </footer>

</body>
</html>
