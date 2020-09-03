package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.OrderSummary;
import com.iiht.evaluation.coronokit.model.ProductMaster;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KitDao kitDAO;
	private ProductMasterDao productMasterDao;

	public void setKitDAO(KitDao kitDAO) {
		this.kitDAO = kitDAO;
	}

	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config. getServletContext().getInitParameter("jdbcPassword");
		
		this.kitDAO = new KitDao(jdbcURL, jdbcUsername, jdbcPassword);
		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String viewName = "";
		try {
			switch (action) {
			case "newuser":
				viewName = showNewUserForm(request, response);
				break;
			case "insertuser":
				viewName = insertNewUser(request, response);
				break;
			case "showproducts":
				viewName = showAllProducts(request, response);
				break;	
			case "addnewitem":
				viewName = addNewItemToKit(request, response);
				break;
			case "deleteitem":
				viewName = deleteItemFromKit(request, response);
				break;
			case "showkit":
				viewName = showKitDetails(request, response);
				break;
			case "placeorder":
				viewName = showPlaceOrderForm(request, response);
				break;
			case "saveorder":
				viewName = saveOrderForDelivery(request, response);
				break;	
			case "ordersummary":
				viewName = showOrderSummary(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;	
			}
		} catch (Exception ex) {
			
			throw new ServletException(ex.getMessage());
		}
			RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
			dispatch.forward(request, response);
	
	}

	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response) {
		OrderSummary orderSummaryObj = new OrderSummary();
		
		return "";
	}

	private String saveOrderForDelivery(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response) {
		String viewName = "";
		OrderSummary orderSummaryObj = new OrderSummary();
		
		viewName="placeorder.jsp";
		return viewName;
	}

	private String showKitDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName="";
		List<KitDetail> abc = kitDAO.getAll();
		request.setAttribute("abc", abc);
		viewName = "showkit.jsp";
		return viewName;
	}

	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = "";
		KitDetail kit = new KitDetail(); 		
		kit.setId(kitDAO.randomIdGenerator());		
		kit.setCoronaKitId(kitDAO.randomIdGenerator());		
		kit.setProductId((Integer.parseInt(request.getParameter("id"))));
		kit.setAmount(Integer.parseInt((String) request.getParameter("cost")));		
		kit.setQuantity(Integer.parseInt((String) request.getParameter(("itemcount"))));
		if(Integer.parseInt((String) request.getParameter(("itemcount")))!=0) {
			kitDAO.add(kit);
		}		
		viewName = showKitDetails(request, response);
		return viewName;
	}
	//Working fine
	private String showAllProducts(HttpServletRequest request, HttpServletResponse response) {
		String viewName = "";
		try {
			List<ProductMaster> product = productMasterDao.getAll();
			request.setAttribute("product", product);
			viewName = "showproductstoadd.jsp";
		} catch (Exception e) {
			request.setAttribute("errMsg", e.getMessage());
			viewName = "errorPage.jsp";
		}
		return viewName;
	}

	private String insertNewUser(HttpServletRequest request, HttpServletResponse response) {

		return "";
	}

	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userName=(String) request.getParameter("pname");
		String userEmail=(String) request.getParameter("pemail");
		String userContact=(String) request.getParameter("pcontact");
		insertNewUser(request, response);	
		String viewName="";
		viewName="newuser.jsp";
		return viewName;
	}
	
	
}