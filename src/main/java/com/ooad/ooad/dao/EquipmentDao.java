package com.ooad.ooad.dao;

import com.ooad.ooad.entity.Equipment;
import com.ooad.ooad.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDao {
    private Connection connection;
    private PreparedStatement prepare;
    private ResultSet result;

    public  List<Equipment> getAllEquipment() throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.equipment where isactive=true";
        List<Equipment> equipmentList = new ArrayList<>();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                String model = result.getString("model");
                Date purchase = result.getDate("purchase");
                Date expiry = result.getDate("expiry");
                int depid = result.getInt("depid");
                int price = result.getInt("price");
                Equipment equipment = new Equipment(id, name, model, purchase, expiry, depid, price);
                equipmentList.add(equipment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return equipmentList;
    }

    public void deleteEquipment(String equipId) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "update ooad.equipment set isactive=false where id='" + equipId + "'";
        try {
            prepare = connection.prepareStatement(sql);
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createEquipment(Equipment equipment) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "insert into ooad.equipment(id, name, model, purchase, expiry, depid, price) values(?,?,?,?,?,?,?)";
        try {
            prepare = connection.prepareStatement(sql);
            prepare.setString(1, equipment.getId());
            prepare.setString(2, equipment.getName());
            prepare.setString(3, equipment.getModel());
            prepare.setDate(4, (Date) equipment.getPurchase());
            prepare.setDate(5, (Date) equipment.getExpiry());
            prepare.setInt(6, equipment.getDepId());
            prepare.setLong(7, equipment.getPrice());
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Equipment getEquipmentById(String id) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.equipment where id='" + id + "'";
        Equipment equipment = new Equipment();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                String eid = result.getString("id");
                String name = result.getString("name");
                String model = result.getString("model");
                long price = result.getLong("price");
                Date purchase = result.getDate("purchase");
                Date expiry = result.getDate("expiry");
                int depid = result.getInt("depid");
                equipment.setDepId(depid);
                equipment.setId(eid);
                equipment.setPrice(price);
                equipment.setName(name);
                equipment.setExpiry(expiry);
                equipment.setPurchase(purchase);
                equipment.setModel(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return equipment;
    }

    public void updateEquipment(Equipment equipment) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "update ooad.equipment set name=?, model=?, price=?, purchase=?, expiry=? where id='" + equipment.getId() + "'";
        try {
            prepare = connection.prepareStatement(sql);
            prepare.setString(1, equipment.getName());
            prepare.setString(2, equipment.getModel());
            prepare.setLong(3, equipment.getPrice());
            prepare.setDate(4, (Date) equipment.getPurchase());
            prepare.setDate(5, (Date) equipment.getExpiry());
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Equipment> getEquipmentByDepId(int depId) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.equipment where depid="+depId + " and isactive=true";
        List<Equipment> equipmentList = new ArrayList<>();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                String model = result.getString("model");
                Date purchase = result.getDate("purchase");
                Date expiry = result.getDate("expiry");
                int depid = result.getInt("depid");
                int price = result.getInt("price");
                Equipment equipment = new Equipment(id, name, model, purchase, expiry, depid, price);
                equipmentList.add(equipment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return equipmentList;
    }
}
