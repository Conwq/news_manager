<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page isELIgnored="false" pageEncoding="UTF-8"%>

<head>
	<style>
	     .news-details {
		      max-width: 800px;
		      margin: 20px auto;
		      padding: 20px;
		      border: 1px solid #ddd;
		      border-radius: 5px;
		      background-color: #fff;
		      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
		    }
		
		    .news-details h2 {
		      margin-top: 0;
		      color: #333;
		    }
		
		    .news-details .publication-date {
		      font-size: 14px;
		      color: #777;
		    }
		
		    .news-details .title {
		      font-size: 20px;
		      margin-bottom: 10px;
		    }
		
		    .news-details .brief {
		      font-size: 16px;
		      margin-bottom: 20px;
		    }
		
		    .news-details .content {
		      font-size: 18px;
		      line-height: 1.6;
		      margin-bottom: 30px;
		    }
		
		    .news-details .action-buttons {
		      display: flex;
		      justify-content: space-between;
		    }
		
		    .news-details .action-buttons button {
		      padding: 10px 20px;
		      border: none;
		      border-radius: 5px;
		      cursor: pointer;
		    }
		
		    .news-details .action-buttons button.edit {
		      background-color: #333;
		      color: #fff;
		    }
		
		    .news-details .action-buttons button.edit:hover {
		      background-color: #555;
		    }
		
		    .news-details .action-buttons button.delete {
		      background-color: #c0392b;
		      color: #fff;
		    }
		
		    .news-details .action-buttons button.delete:hover {
		      background-color: #e74c3c;
		    }
		
		    .news-details .back-button {
		      display: inline-block;
		      background-color: #555;
		      color: #fff;
		      border: none;
		      padding: 10px 20px;
		      text-decoration: none;
		      border-radius: 5px;
		      margin-top: 10px;
		      cursor: pointer;
		    }
		
		    .news-details .back-button:hover {
		      background-color: #333;
		    }
	</style>
</head>

<body>
	<div class="news-details">
	  <span class="publication-date">Publication Date: ${news.publicationDate}</span>
	  <h2 class="title">${news.title}</h2>
	  <p class="brief">${news.brief}</p>
	  <p class="content">${news.content}</p>
	  <div class="action-buttons">
	    <button class="edit">Edit news</button>
	    <button class="delete">Delete news</button>
	  </div>
	  <a href="javascript:history.back()" class="back-button">Back</a>
	</div>
</body>