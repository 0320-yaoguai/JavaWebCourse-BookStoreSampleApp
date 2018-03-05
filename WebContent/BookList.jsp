<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.pluralsight.Book" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Book Store</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<%
	ArrayList<Book> books = 
	(ArrayList<Book>)request.getAttribute("books");
%>

<body>
    <div class="container">
	    <div class="links">
	        <h1><a href="list">Book Store</a></h1>
	        <h2><a href="new">Add New Book</a></h2>
	    </div>
	    <div class="booktable">
	        <table border="1" cellpadding="5">
	            <caption><h2>List of Books</h2></caption>
	            <tr>
	                <th>Title</th>
	                <th>Author</th>
	                <th>Price</th>
	            </tr>

	 			<c:forEach items="${books}" var="item">
	                <tr>
	                    <td> ${ item.getTitle() } </td>
	                    <td> ${ item.getAuthor() } </td>
	                    <td> ${ item.getPrice() } </td>
	                </tr>
	            </c:forEach>
	        </table>
	    </div>
    </div>
</body>
</html>