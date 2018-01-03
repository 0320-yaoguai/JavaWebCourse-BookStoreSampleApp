<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
        <h1><a href="/BookStore/list">Book Store</a></h1>
        <h2><a href="/BookStore/new">Add New Book</a></h2>
    </center>
            
            
<form name="book_form" method="post" action="/BookStore/insert">
  Book Title:<br>
  <input type="text" name="booktitle" value="">
  <br>
  Author:<br>
  <input type="text" name="bookauthor" value="">
  <br>
  Price:<br>
  <input type="text" name="bookauthor" value="">
  <br><br>
  <input type="submit" value="Submit">
</form>
</body>
</html>