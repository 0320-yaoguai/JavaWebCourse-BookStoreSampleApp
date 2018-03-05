package com.pluralsight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/book_store";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	private Connection jdbcConnection;

	public void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
			Class.forName("com.mysql.jdbc.Driver");
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			System.out.println("Connection Established to MySQL Database");
			} catch (Exception e ) {
				System.err.println(e.getMessage());
			}
		}
	}

	public void disconnect() throws SQLException {
		if (jdbcConnection != null || !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public ArrayList<Book> listAllBooks() throws SQLException {
		ArrayList<Book> bookList = new ArrayList<>();

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM book;");

		while (resultSet.next()) {
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			float price = resultSet.getFloat("price");

			Book book = new Book(title, author, price);
			bookList.add(book);
		}

		resultSet.close();
		statement.close();
		disconnect();

		return bookList;
	}

	public boolean insertBook(Book book) throws SQLException {
		connect();
		String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?);";

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
