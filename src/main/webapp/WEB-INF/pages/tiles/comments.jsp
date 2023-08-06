<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ page isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization}"/>
<fmt:setBundle basename="locales.locale" var="loc"/>

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

    .comment-form {
        margin-top: 20px;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 5px;
        background-color: #f9f9f9;
    }

    .comment-form label {
        font-weight: bold;
        display: block;
        margin-bottom: 5px;
    }

    .comment-form textarea {
        width: 98%;
        height: 100px;
        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #ddd;
        border-radius: 3px;
        resize: vertical;
    }

    .comment-form .submit-button {
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        background-color: #007bff;
        color: #fff;
        cursor: pointer;
    }

    .comment-form .submit-button:hover {
        background-color: #0056b3;
    }

    .comment-form .submit-button:focus {
        outline: none;
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

    <div class="comment-form">
        <label for="new-comment">Enter your comment:</label>
        <textarea id="new-comment"></textarea>
        <button class="submit-button" type="submit">Submit</button>
    </div>
</div>