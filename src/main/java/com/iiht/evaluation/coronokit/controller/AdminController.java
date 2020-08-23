package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.ProductMaster;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductMasterDao productMasterDao;
	
	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");

		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//HttpSession session = request.getSession();
		String action =  request.getParameter("action");
		if(action.contains("=")) {
			String[] arr = action.split("=");
			action=arr[0];
			request.setAttribute("pid",arr[1]);
		}
		String viewName = "";
		try {
			switch (action) {
			case "login" : 
				viewName=adminLogin(request, response);
				break;
			case "newproduct":
				viewName = showNewProductForm(request, response);
				break;
			case "insertproduct":
				viewName = insertProduct(request, response);
				break;
			case "deleteproduct":	
				viewName = deleteProduct(request, response);
				break;
			case "editproduct":
				viewName = showEditProductForm(request, response);
				break;
			case "updateproduct":
				viewName = updateProduct(request, response);
				break;
			case "list":
				viewName = listAllProducts(request, response);
				break;	
			case "logout":
				viewName = adminLogout(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		
		
	}

	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String listAllProducts(HttpServletRequest request, HttpServletResponse response) {
		String viewName = "";
		try {
			List<ProductMaster> product = productMasterDao.getAll();
			request.setAttribute("product", product);
			viewName = "listproducts.jsp";
		} catch (Exception e) {
			request.setAttribute("errMsg", e.getMessage());
			viewName = "errorPage.jsp";
		}
		return viewName;
	}

	private String updateProduct(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("inside update product");
		ProductMaster product = new ProductMaster();		
		product.setId(Integer.parseInt((String) request.getParameter(("pid"))));
		product.setProductName((String) request.getParameter("pname"));
		product.setCost((String) request.getParameter("pcost"));
		System.out.println("YYYYYYYY"+request.getParameter("cost"));
		product.setProductDescription((String) request.getParameter("pdescription"));
		String viewName = "";
		try {
			productMasterDao.save(product);
			viewName =listAllProducts(request, response);
		}catch (Exception e) {
			
		}
		return viewName;
	}

	private String showEditProductForm(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt((String) request.getAttribute("pid"));
		String viewName = "";

		try {
			ProductMaster product = productMasterDao.getById(id);
			request.setAttribute("product", product);
			request.setAttribute("isNew", false);
			viewName = "editproduct.jsp";
		} catch (Exception e) {
			request.setAttribute("errMsg", e.getMessage());
			viewName = "errorPage.jsp";
		}
		return viewName;
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt((String) request.getAttribute("pid"));
		String viewName = "";

		try {
			productMasterDao.deleteById(id);		
			request.setAttribute("msg","Product deleted");
			viewName = listAllProducts(request, response);
		} catch (Exception e) {
			request.setAttribute("errMsg", e.getMessage());
			viewName = "errorPage.jsp";
		}

		return viewName;
	}

	private String insertProduct(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("I am inside insert");
		ProductMaster product = new ProductMaster();
		product.setId(Integer.parseInt(request.getParameter("pid")));
		product.setProductName(request.getParameter("pname"));
		product.setCost(request.getParameter("pcost"));
		product.setProductDescription(request.getParameter("pdescription"));
		System.out.println(request.getParameter("pid"));
		System.out.println(request.getParameter("pname"));
		System.out.println(request.getParameter("pcost"));
		System.out.println(request.getParameter("pdescription"));
		String viewName = "";

		try {		
			productMasterDao.add(product);		
			request.setAttribute("msg", "Product Saved Successfully");
			viewName = listAllProducts(request, response);
		} catch (Exception e) {
			request.setAttribute("errMsg", e.getMessage());
			viewName = "errorPage.jsp";
		}
		return viewName;
	}

	private String showNewProductForm(HttpServletRequest request, HttpServletResponse response) {
		String viewName = "";
		viewName="newproduct.jsp";
		return viewName;
	}

	private String adminLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = "";
		String userName = request.getParameter("loginid");
		String password = request.getParameter("password");
		if(userName.equalsIgnoreCase("admin") && password.equals("admin")) {
			viewName=listAllProducts(request,response);
		}
		return viewName;
	}

	
}