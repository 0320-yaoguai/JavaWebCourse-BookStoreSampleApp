package com.pluralsight;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.Statement;

public class BookDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/book_store";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    private Connection jdbcConnection;
     
     
    public void connect() throws SQLException, ClassNotFoundException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
            	Class.forName("com.mysql.jdbc.Driver");
            	jdbcConnection = DriverManager.getConnection(
                        jdbcURL, jdbcUsername, jdbcPassword);
            	
            	System.out.println("Connection Established to MySQL Database");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            
        }
    }
     
    public void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     
    public ArrayList<Book> listAllBooks() throws SQLException, ClassNotFoundException {
        ArrayList<Book> listBook = new ArrayList<>();
         
        String sql = "SELECT * FROM book";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");
             
            Book book = new Book(title, author, price);
            listBook.add(book);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listBook;
    }
    
    public boolean insertBook(Book book) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setFloat(3, book.getPrice());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

}
