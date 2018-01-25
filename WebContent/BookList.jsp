<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.pluralsight.Book" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Book Store</title>
</head>
<%
	ArrayList<Book> books = 
	(ArrayList<Book>)request.getAttribute("books");
%>

<body>
    <center>
        <h1><a href="/BookStore/list">Book Store</a></h1>
        <h2><a href="/BookStore/new">Add New Book</a></h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Books</h2></caption>
            <tr>
                <th>Title</th>
                <th>Author</th>
                <th>Price</th>
            </tr>
 			
 			<c:forEach items="${books}" var="item">
                <tr>
                    <td> ${item.getTitle()  }  </td>
                    <td> ${item.getAuthor() } </td>
                    <td> ${item.getPrice()  } </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>