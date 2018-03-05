package com.pluralsight;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */

public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    public void init() {
    		bookDAO = new BookDAO();
    		
    		try {
			bookDAO.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bookDAO.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public ControllerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException  {
		String action = request.getServletPath();
		String info = request.getPathInfo();
		PrintWriter output = response.getWriter();
		output.println("action = " + action + ", info = " + info);
		
		
		
		try {
			if (info.equals("/new")) {
				addBook(request, response);
			}
			else if (info.equals("/insert")) {
				insertBook(request, response);
			}
			else {
				listBooks(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void listBooks(HttpServletRequest request, HttpServletResponse response) 
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		ArrayList<Book> bookList = bookDAO.listAllBooks();
		
		request.setAttribute("books", bookList);
		//Missing leading forward slash caused infinite loop issues with the * wildcard in web.xml
		RequestDispatcher dispatcher = request.getRequestDispatcher("/BookList.jsp");
		dispatcher.forward(request, response);
	}
	
	private void addBook(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/BookForm.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertBook(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		String title = request.getParameter("booktitle");
		String author = request.getParameter("bookauthor");
		String priceString = request.getParameter("bookprice");
		
		Book newBook = new Book(title, author, Float.parseFloat(priceString));
		
		bookDAO.insertBook(newBook);
		
		response.sendRedirect("list");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("This is the doPost() method!");
		doGet(request, response);
		
	}

}
