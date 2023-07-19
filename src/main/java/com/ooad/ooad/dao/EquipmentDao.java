package com.ooad.ooad.dao;

import com.ooad.ooad.entity.Equipment;
import com.ooad.ooad.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDao{
    private Connection connection;
    private ResultSet result;
    private PreparedStatement prepare;
    
    public static List<Equipment> getAllEquipment() throws SQLException{
	connection = DBUtil.connectDB();
        String sql = "select * from ooad.equipment";
	ArrayList<Equipment> equipmentList = new ArrayList<>();
	try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String model = result.getString("model");
		Date purchase = result.getDate("purchase");
		Date expiry = result.getDate("expiry");
		int deid = result.getInt("deid");
		long price = rs.getLong("price");	
                Equipment equipment = new Equipment(id, name, model, purchase, expiry, deid, price);
		equipmentList.add(equipment);
            }
	 } catch (SQLException e) {
            e.printStackTrace();
        }
 	return equipmentList;
    }
    
    public static Equipment findEquipment(Connection conn, int id) throws  SQLException {
        String sql = "select * from equipment where id = ?";
        PreparedStatement prsm = conn.prepareStatement(sql);
        prsm.setInt(1, id);
        ResultSet rs = prsm.executeQuery();
        while (rs.next()) {
            String name = rs.getString("name");
            String model = rs.getString("model");
 	    Date purchase = rs.getDate("purchase");
	    Date expiry = result.getDate("expiry");
	    int deid = result.getInt("deid");	
	    long price = result.getLong("price");
             Equipment equipment = new Equipment(id, name, model, purchase, expiry, deid, price);
            return equipment;
        }
        return null;
    }

    public static void insertEquipment(Connection conn, Equipment equipment) throws SQLException {
        String sql = "insert into equipment(name, model, purchase, expiry, deid, price)\n" +
                "values (?,?,?,?,?,?)";
        PreparedStatement prsm = conn.prepareStatement(sql);
        prsm.setString(1, equipment.getName());
        prsm.setString(2, equipment.getModel());
        prsm.setDate(3, equipment.getPurchase());
        prsm.setDate(4, equipment.getExpiry());
        prsm.setInt(5, equipment.getDepId());
	prsm.setLong(6, equipment.getPrice());
        prsm.executeUpdate();
    }
	
    public static void updateEquipment(Connection conn, Equipment equipment) throws SQLException {
        String sql = "update equipment set name = ?, model = ?, purchase = ?, expiry = ?, deid = ?, price = ? where id = ?";
        PreparedStatement prsm = conn.prepareStatement(sql);
        prsm.setString(1, equipment.getName());
        prsm.setString(2, equipment.getModel());
        prsm.setDate(3, equipment.getPurchase());
        prsm.setDate(4, equipment.getExpiry());
        prsm.setInt(5, equipment.getDepId());
	prsm.setLong(6, equipment.getPrice());
        prsm.executeUpdate();
    }

   public static void deleteEquipment(Connection conn, String id) throws SQLException {
        String sql = "update equipment set isactive=false where id=?";
        PreparedStatement prsm = conn.prepareStatement(sql);
        int ID = 0;
        try {
            ID = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        prsm.setInt(1, ID);
        prsm.executeUpdate();
    }

}