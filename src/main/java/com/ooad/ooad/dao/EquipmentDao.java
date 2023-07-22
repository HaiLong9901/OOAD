package com.ooad.ooad.dao;
import com.ooad.ooad.entity.Equipment;
import com.ooad.ooad.utils.DBUtil;

import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class EquipmentDao {
    private Connection connection;
    private ResultSet result;
    private PreparedStatement prepare;

    public EquipmentDao() {
        connection = DBUtil.connectDB();
    }

    public  List<Equipment> getEquipmentByDepId(int id) throws SQLException {
        ArrayList<Equipment> lstEquipment = new ArrayList<>();
        String sql = "SELECT * FROM ooad.equipment WHERE depid = ? AND isActive = true";
        try {
            prepare = connection.prepareStatement(sql);
            prepare.setInt(1, id);
            result = prepare.executeQuery();
            while (result.next()) {
                int equipid = result.getInt("id");
                String name = result.getString("name");
                String model = result.getString("model");
                Date purchase = result.getDate("purchase");
                Date expiry  = result.getDate("expiry");
                Long price = result.getLong("price");
                Equipment equipment = new Equipment( equipid,name, model, purchase,expiry, id, price);
                lstEquipment.add(equipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstEquipment;
        }

    }
