<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 03.05.2017
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование книги</title>
</head>
<body>
<section>
    <h2>
        <a href="index.html">Home</a>
        <a href="books">Back</a>
    </h2>
    <h2>${param.action == 'create' ? 'Create book' : 'Edit book'}</h2>
    <hr>
    <jsp:useBean id="book" type="company.model.Book" scope="request"/>
    <form method="post" action="books">
        <input type="hidden" name="id" value="${book.id}">
        <d1>
            <dt>Name Book:</dt>
            <dd><input type="text" value="${book.name}" name="book_name"></dd>
        </d1>
        <d1>
            <dt>Author:</dt>
            <dd><input type="text" value="${book.author}" name="author"></dd>
        </d1>
        <d1>
            <dt>Description:</dt>
            <dd><input type="text" value="${book.desc}" name="description"></dd>
        </d1>
        <p>
            <button type="submit">Save</button>
            <button onclick="window.history.back()">Cancel</button>
        </p>
    </form>
</section>
</body>
</html>
