package com.pluralsight;

import java.io.IOException;
import java.io.PrintWriter;
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
    private ArrayList<Book> books = new ArrayList<Book>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        
        // Add books to our ArrayList
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", 5.00f));
		books.add(new Book("1984", "George Orwell", 5.00f));
		books.add(new Book("Frankenstein", "author", 5.00f));
		books.add(new Book("Gone With the Wind", "author", 5.00f));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action.equals("/new")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
			dispatcher.forward(request, response);
		}
		else if (action.equals("/insert")) {
			String title = request.getParameter("booktitle");
			String author = request.getParameter("bookauthor");
			String priceString = request.getParameter("bookprice");
			
			Book newBook = new Book(title, author, Float.parseFloat(priceString));
			books.add(newBook);
			
			request.setAttribute("books", books);
			RequestDispatcher dispatcher = request.getRequestDispatcher("BookList.jsp");
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("books", books);
			RequestDispatcher dispatcher = request.getRequestDispatcher("BookList.jsp");
			dispatcher.forward(request, response);
		}
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
