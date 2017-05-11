<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 28.04.2017
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Список книг</title>
    <style type="text/css">
        TD, TH {
            padding: 5px;
            text-align: center;
        }

        #ID {
            font-style: italic;
        }
    </style>
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h2>Список книг</h2>
    <table border="1">

        <p><a href="books?action=create">Add Book</a></p>
        <tr>
            <th>ID</th>
            <th>Book Name</th>
            <th>Author</th>
            <th>Description</th>
            <th colspan="2">Action</th>
        </tr>
        <c:forEach items="${books}" var="book">

            <jsp:useBean id="book" scope="page" type="company.model.Book"/>

            <tr>
                <td id="ID">${book.id}</td>
                <td>${book.name}</td>
                <td>${book.author}</td>
                <td>${book.desc}</td>
                <td><a href="books?action=update&id=${book.id}">Update</a></td>
                <td><a href="books?action=delete&id=${book.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
