<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ page isELIgnored="false" pageEncoding="UTF-8" %>

<style>
    .news-details {
        width: 95%;
        padding: 20px;
        border: 1px solid #ddd;
        border-radius: 5px;
        background-color: #fff;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        word-wrap: break-word;
        margin-left: 20px;
        margin-bottom: 20px;
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
        text-align: center;
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

<div class="news-details">
	<span class="publication-date">Publication Date: ${news.publicationDate}</span> <br>
    <hr>
    <h2 class="title">${news.title}</h2>
    <p class="brief">${news.brief}</p>
    <hr><br>
    <p class="content">${news.content}</p>
    <c:if test="${sessionScope.role eq 'admin'}">
        <div class="action-buttons">
            <a href="${pageContext.request.contextPath}/news/goToEditNews?id=${news.id}">
                <button class="edit">Edit news</button>
            </a>
            <a href="${pageContext.request.contextPath}/news/doDeleteNews?id=${news.id}">
                <button class="delete">Delete news</button>
            </a>
        </div>
    </c:if>
    <a href="javascript:history.back()" class="back-button">Back</a>
</div>

<c:import url="/WEB-INF/pages/tiles/comments.jsp" charEncoding="utf-8"/>