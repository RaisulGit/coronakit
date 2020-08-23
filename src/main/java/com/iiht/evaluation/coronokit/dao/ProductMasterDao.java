package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.model.ProductMaster;

public class ProductMasterDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public ProductMasterDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

	protected Connection connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
		return jdbcConnection;
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public static final String INS_CONT_QRY = "INSERT INTO productdetails(id,productname,cost,productdesc) VALUES(?,?,?,?)";
	public static final String UPD_CONT_QRY = "UPDATE productdetails set productname=?,cost=?,productdesc=? WHERE id=?";
	public static final String DEL_CONT_QRY = "DELETE FROM productdetails WHERE id=?";
	public static final String GET_CONT_BY_ID_QRY = "SELECT id,productname,cost,productdesc FROM productdetails WHERE id=?";
	public static final String GET_ALL_CONTS_QRY = "SELECT id,productname,cost,productdesc FROM productdetails";

	public ProductMaster add(ProductMaster product) throws Exception {

		if (product != null) {
			try (Connection con =connect();					
				PreparedStatement pst = con.prepareStatement(INS_CONT_QRY);) {
				
				pst.setInt(1, product.getId());
				pst.setString(2, product.getProductName());
				pst.setString(3, product.getCost());
				pst.setString(4, product.getProductDescription());
				pst.executeUpdate();
			} catch (SQLException exp) {
				throw new Exception("Adding Product failed!");
			}
		}

		return product;
	}

	public ProductMaster save(ProductMaster product) throws Exception {
		if (product != null) {
			try (Connection con =connect();					
				PreparedStatement pst = con.prepareStatement(UPD_CONT_QRY);) {
				pst.setInt(4, product.getId());
				pst.setString(1, product.getProductName());;
				pst.setString(2, product.getCost());
				pst.setString(3, product.getProductDescription());
				pst.executeUpdate();
			} catch (SQLException exp) {
				throw new Exception("Saving Product failed!");
			}
		}

		return product;
	}

	public boolean deleteById(int id) throws Exception {
		boolean isDeleted = false;
		try (Connection con =connect();
				PreparedStatement pst = con.prepareStatement(DEL_CONT_QRY);) {

			pst.setInt(1, id);

			int rowsCount = pst.executeUpdate();
			isDeleted = rowsCount > 0;

		} catch (SQLException exp) {
			throw new Exception("Deleting Product failed!");
		}
		return isDeleted;
	}

	public ProductMaster getById(int id) throws Exception {
		ProductMaster product = null;

		try (Connection con =connect();
				PreparedStatement pst = con.prepareStatement(GET_CONT_BY_ID_QRY);) {

			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				product = new ProductMaster();
				product.setId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setCost(rs.getString(3));
				product.setProductDescription(rs.getString(4));
			}

		} catch (SQLException exp) {
			throw new Exception("Feteching Product failed!");
		}

		return product;
	}

	public List<ProductMaster> getAll() throws Exception {
		List<ProductMaster> products = new ArrayList<ProductMaster>();
		try (Connection con =connect();
				PreparedStatement pst = con.prepareStatement(GET_ALL_CONTS_QRY);) {
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				ProductMaster product = new ProductMaster();
				product.setId(rs.getInt("id"));
				product.setProductName(rs.getString("productname"));
				product.setCost(rs.getString("cost"));
				product.setProductDescription(rs.getString("productdesc"));	
				products.add(product);
			}
			if(products.isEmpty()) {
				products=null;
			}

		} catch (SQLException exp) {
			throw new Exception("Feteching Product failed!");
		}
		
		return products;
	}

}