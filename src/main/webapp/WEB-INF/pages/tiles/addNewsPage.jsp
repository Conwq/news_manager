<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<style>
.create-news-page {
	max-width: 400px;
	margin: 20px auto;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 5px;
	background-color: #f9f9f9;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.create-news-page label {
	display: block;
	font-weight: bold;
	margin-bottom: 5px;
}

.create-news-page input[type="text"] {
	width: 100%;
	padding: 8px;
	margin-bottom: 10px;
	border: 1px solid #ddd;
	border-radius: 3px;
}

.create-news-page textarea {
	width: 100%;
	height: 150px;
	padding: 8px;
	margin-bottom: 10px;
	border: 1px solid #ddd;
	border-radius: 3px;
	resize: vertical;
}

.create-news-page .buttons-create-news-page-form {
	display: flex;
	justify-content: space-between;
}

.create-news-page .buttons-create-news-page-form button {
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.create-news-page .create-button {
	background-color: #007bff;
	color: #fff;
}

.create-news-page .back-button {
	background-color: #ccc;
	color: #333;
}

.create-news-page .create-button:hover, .create-news-page .back-button:hover
	{
	background-color: #0056b3;
}

.create-news-page .create-button:focus, .create-news-page .back-button:focus
	{
	outline: none;
}
</style>


<div class="create-news-page">
	<form:form modelAttribute="news"
		action="${pageContext.request.contextPath}/news/doAddNews">
		<label for="title">Enter title:</label>
		<form:input id="title" path="title" />

		<label for="brief">Enter brief:</label>
		<form:input id="brief" path="brief" />

		<label for="content">Enter content:</label>
		<form:textarea id="content" path="content"></form:textarea>

		<div class="buttons-create-news-page-form">
			<button class="create-button" type="submit">Create</button>
			<button class="back-button" onclick="window.history.back()"
				type="button">Back</button>
		</div>
	</form:form>
</div>