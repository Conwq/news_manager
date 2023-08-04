<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page isELIgnored="false" pageEncoding="UTF-8"%>

<style>
.comments-container {
	flex: 0.3; 
	margin-left: 20px; 
}

.comment {
	background-color: #f9f9f9;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 5px;
	margin-bottom: 15px;
}

.comment .username {
	font-weight: bold;
	margin-bottom: 5px;
}

.comment .date {
	font-size: 12px;
	color: #888;
	margin-bottom: 5px;
}

.comment .content {
	word-wrap: break-word;
}
</style>

<div class="comments-container">
	<div class="comment">
		<div class="username">John Doe</div>
		<div class="date">2023-08-02</div>
		<div class="content">
			Lorem ipsum dolor sit amet, consectetur
			adipiscing elit. Nulla sit amet nisl justo.
		</div>
	</div>

	<div class="comment">
		<div class="username">Jane Smith</div>
		<div class="date">2023-08-03</div>
		<div class="content">
			Aenean faucibus a quam quis congue. Sed
			euismod, nunc a congue euismod, ex massa suscipit justo, sit amet
			semper metus quam ut felis.
		</div>
	</div>
</div>