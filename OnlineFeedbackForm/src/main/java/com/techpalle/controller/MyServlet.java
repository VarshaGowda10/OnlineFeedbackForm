package com.techpalle.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.techpalle.bean.Admin;
import com.techpalle.bean.User;

@WebServlet("/")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		switch(path)
		{
		case"/validateadmin":validateAdmin(req,res);
		break;
		case"/registeradmin":registerAdmin(req,res);
		break;
		case"/openfeedback":openFeedback(req,res);
		break;
		case"/add":addUser(req,res);
		break;
		case"/home":homePage(req,res);
		break;
		
		}
	}

	

	private void homePage(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s= sf.openSession();
		Transaction t = s.beginTransaction();
		
		Criteria c=s.createCriteria(User.class);
		java.util.List<User> user_list=c.list();
		req.setAttribute("userlist", user_list);
	    RequestDispatcher rd =  req.getRequestDispatcher("adminhome.jsp");
	    try {
			rd.forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addUser(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		String name=req.getParameter("txtName");
		String email = req.getParameter("txtEmail");
		String comments = req.getParameter("txtComments");
		User u1= new User(name, email, comments);
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		s.save(u1);
		t.commit();
		s.close();
		sf.close();
		
		try {
			res.sendRedirect("feedback.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.setContentType("text/html");
		PrintWriter out; 
		try {
			out= res.getWriter();
			out.println("<h1>"+ "User Feedback Submitted successfully"+"<h1>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void openFeedback(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		try {
			res.sendRedirect("feedback.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void registerAdmin(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		String name=req.getParameter("txtName");
		String email = req.getParameter("txtEmail");
		String password = req.getParameter("txtPassword");
		
		Admin a= new Admin(name, email, password);
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s= sf.openSession();
		Transaction t = s.beginTransaction();
		s.save(a);
		t.commit();
		s.close();
		sf.close();
		try {
			res.sendRedirect("adminlogin.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void validateAdmin(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s= sf.openSession();
		Transaction t = s.beginTransaction();
		
		Criteria c = s.createCriteria(Admin.class);  
		java.util.List<Admin> l= c.list();
		
		Criteria c1 = s.createCriteria(User.class);
		java.util.List<User> u1=c1.list(); 
		req.setAttribute("userlist", u1);
		
		String email = req.getParameter("txtEmail");
		String password = req.getParameter("txtPassword");
		boolean isDataPresent = false;
		for(Admin a:l)
		{
			if(email.equals(a.getEmail()) && password.equals(a.getPasword()))
			{
				isDataPresent = true;
				break;
			}
		}
		if(isDataPresent)
		{
			RequestDispatcher rd = req.getRequestDispatcher("adminhome.jsp");
			try {
				rd.forward(req, res);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			req.setAttribute("message", "Invalid Admin");
			RequestDispatcher rd = req.getRequestDispatcher("adminlogin.jsp");
		}
	}

}
