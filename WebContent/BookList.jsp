<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Book Store</title>
</head>
<%
	java.util.ArrayList<com.pluralsight.Book> books = 
	(java.util.ArrayList<com.pluralsight.Book>)request.getAttribute("books");
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
 
 			<% for (int i = 0; i<books.size(); i++) { %>
                <tr>
                    <td><%= books.get(i).getTitle() %> </td>
                    <td><%= books.get(i).getAuthor() %> </td>
                    <td><%= books.get(i).getPrice() %> </td>
                </tr>
            <% } %>
        </table>
    </div>   
</body>
</html>