<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page isELIgnored="false" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
	<style>
		.edit-news-form {
      max-width: 600px;
      margin: 20px auto;
      padding: 20px;
      border: 1px solid #ddd;
      border-radius: 5px;
      background-color: #fff;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    .edit-news-form h2 {
      margin-top: 0;
      color: #333;
    }

    .edit-news-form label {
      display: block;
      margin-bottom: 8px;
      color: #333;
    }

    .edit-news-form input[type="text"],
    .edit-news-form textarea {
      width: 100%;
      padding: 12px;
      margin-bottom: 15px;
      border: 1px solid #ddd;
      border-radius: 5px;
      box-sizing: border-box;
    }

    .edit-news-form textarea {
      resize: vertical;
      height: 150px;
    }

    .edit-news-form button {
      padding: 10px 20px;
      background-color: #333;
      color: #fff;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }

    .edit-news-form button:hover {
      background-color: #555;
    }
	</style>
</head>

<body>
	<div class="edit-news-form">
	  <h2>Edit News</h2>
	  <form:form action="${pageContext.request.contextPath}/news/doEditNews" method="post" modelAttribute="news">
	  	<form:hidden path="id" value="${news.id}"/>
	  	
	    <label for="title">Title:</label>
	    <form:input path="title" id="title" value="${news.title}"/>
	
	    <label for="brief">Brief news description:</label>
	    <form:input path="brief" id="brief" value="${news.brief}"/>
	
	    <label for="content">Content:</label>
	    <form:textarea path="content" id="content" value="${news.content}"/>
		
	    <button type="submit">Save Changes</button>
	  </form:form>
	  <a href="javascript:history.back()" class="back-button">Back</a>
	</div>
</body>
