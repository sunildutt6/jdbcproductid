package com.simplilearn;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductDetailsServlet
 */
@WebServlet("/productdetails")
public class ProductDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductDetailsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBConnection db = new DBConnection();
		Connection connection = db.getConnection();
		int id = Integer.parseInt(request.getParameter("id"));
		PrintWriter out = response.getWriter();
		out.println("<html><body>");

		try {
			PreparedStatement ps = connection.prepareStatement("select *  from eproduct where id =?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int productid = (Integer) rs.getInt(1);
				String productname = rs.getString(2);
				int productprice = (Integer) rs.getInt(3);
				Date date = rs.getDate(4);

				out.println("Product Id: " + productid + "<br/>");
				out.println("Product name: " + productname + "<br/>");
				out.println("Product Price: " + productprice + "<br/>");
				out.println("Product Date: " + date + "<br/>");
			} else {
				out.println("Product not found in database");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
