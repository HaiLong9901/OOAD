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
    
    public List<Equipment> getAllEquipment(){
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
		int price = result.getInt("price");	
                Equipment equipment = new Equipment(id, name, model, purchase, expiry, deid, price);
		equipmentList.add(equipment);
            }
	 } catch (SQLException e) {
            e.printStackTrace();
        }
 	return equipmentList;
    }


}