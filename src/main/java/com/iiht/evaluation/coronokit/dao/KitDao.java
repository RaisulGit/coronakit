package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.iiht.evaluation.coronokit.model.KitDetail;



public class KitDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public KitDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	public static final String INS_CONT_QRY = "INSERT INTO kitdetails(id,coronaKitId,productId,quantity,amount) VALUES(?,?,?,?,?)";
	public static final String UPD_CONT_QRY = "UPDATE kitdetails set coronaKitId=?,productId=?,quantity=?,amount WHERE id=?";
	public static final String DEL_CONT_QRY = "DELETE FROM kitdetails WHERE id=?";
	public static final String GET_CONT_BY_ID_QRY = "SELECT coronaKitId,productId,quantity,amount FROM kitdetails WHERE id=?";
	public static final String GET_ALL_CONTS_QRY = "SELECT id,coronaKitId,productId,quantity,amount FROM kitdetails";

	
	
	public KitDetail add(KitDetail kit) throws Exception {
		if (kit != null) {
			try (Connection con =connect();				
				PreparedStatement pst = con.prepareStatement(INS_CONT_QRY);) {

				pst.setInt(1, kit.getId());
				pst.setInt(2, kit.getCoronaKitId());
				pst.setInt(3, kit.getProductId());
				pst.setInt(4, kit.getAmount());
				pst.setInt(5, kit.getQuantity());
				pst.executeUpdate();
			} catch (SQLException exp) {
				throw new Exception("Adding kits failed!");
			}
		}

		return kit;
	}

	public KitDetail save(KitDetail kit) throws Exception {
		if (kit != null) {
			try (Connection con =connect();
					
				PreparedStatement pst = con.prepareStatement(UPD_CONT_QRY);) {
				pst.setInt(5, kit.getId());
				pst.setInt(1, kit.getCoronaKitId());
				pst.setInt(2, kit.getProductId());
				pst.setInt(3, kit.getAmount());
				pst.setInt(4, kit.getQuantity());
			} catch (SQLException exp) {
				throw new Exception("Saving kits failed!");
			}
		}

		return kit;
	}

	public boolean deleteById(int id) throws Exception {
		boolean isDeleted = false;
		try (Connection con =connect();
				PreparedStatement pst = con.prepareStatement(DEL_CONT_QRY);) {

			pst.setInt(1, id);

			int rowsCount = pst.executeUpdate();
			isDeleted = rowsCount > 0;

		} catch (SQLException exp) {
			throw new Exception("Deleting kits failed!");
		}
		return isDeleted;
	}

	public KitDetail getById(int id) throws Exception {
		KitDetail kit = null;

		try (Connection con =connect();
				PreparedStatement pst = con.prepareStatement(GET_CONT_BY_ID_QRY);) {

			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				pst.setInt(5, kit.getId());
				pst.setInt(1, kit.getCoronaKitId());
				pst.setInt(2, kit.getProductId());
				pst.setInt(3, kit.getAmount());
				pst.setInt(4, kit.getQuantity());
			}

		} catch (SQLException exp) {
			throw new Exception("Feteching kits failed!");
		}

		return kit;
	}

	public List<KitDetail> getAll() throws Exception {
		List<KitDetail> kits = new ArrayList<KitDetail>();
		try (Connection con =connect();				
				PreparedStatement pst = con.prepareStatement(GET_ALL_CONTS_QRY);) {
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				KitDetail kit = new KitDetail();
				kit.setId(rs.getInt("id"));
				kit.setCoronaKitId(rs.getInt("coronaKitId"));
				kit.setProductId(rs.getInt("productId"));
				kit.setQuantity(rs.getInt("quantity"));
				kit.setAmount(rs.getInt("amount"));
				kits.add(kit);
			}
			if(kits.isEmpty()) {
				kits=null;
			}

		} catch (SQLException exp) {
			throw new Exception("Feteching kits failed!");
		}
		
		return kits;
	}
	public int randomIdGenerator() {
		int randomNumber = (int) (Math.random()*90000);
		return randomNumber;
	}
}