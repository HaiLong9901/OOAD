package com.ooad.ooad.dao;

import com.ooad.ooad.entity.Employee;
import com.ooad.ooad.entity.Manager;
import com.ooad.ooad.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerDao {
    private Connection connection;
    private PreparedStatement prepare;
    private ResultSet result;

    public Manager getManagerByPhone(String phone) throws SQLException{
        System.out.println("phone in dao: " + phone);
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.manager where phone=" + phone;
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String phoneNum = result.getString("phone");
                String password = result.getString("password");
                Manager manager = new Manager(id, name, email, phoneNum, password);
                return  manager;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Manager> getAllManager() throws SQLException{
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.manager where isactive=true";
        ArrayList<Manager> managerList = new ArrayList<>();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String phone = result.getString("phone");
                String email = result.getString("email");
                Manager manager = new Manager(id, name, email, phone);
                managerList.add(manager);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return managerList;
    }

    public void createManagerAccount(Manager manager) throws SQLException{
        connection = DBUtil.connectDB();
        String sql = "insert into ooad.manager(name, phone, email, password) values(?,?,?,?)";
        try {
            prepare = connection.prepareStatement(sql);
            prepare.setString(1, manager.getName());
            prepare.setString(2, manager.getPhone());
            prepare.setString(3, manager.getEmail());
            prepare.setString(4, manager.getPassword());
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteManager(int managerId) throws SQLException{
        connection = DBUtil.connectDB();
        String sql = "update ooad.manager set isactive=false where id=" + managerId;
        try {
            prepare = connection.prepareStatement(sql);
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateManager(Manager manager) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "update ooad.manager set name=?, phone=?, password=?, email=? where id=?";
        try {
            prepare = connection.prepareStatement(sql);
            prepare.setString(1, manager.getName());
            prepare.setString(2, manager.getPhone());
            prepare.setString(3, manager.getPassword());
            prepare.setString(4, manager.getEmail());
            prepare.setInt(5, manager.getId());
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Manager getManagerById(int id) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.manager where id=" + id;
        Manager manager = new Manager();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int manId = result.getInt("id");
                String name = result.getString("name");
                String phone = result.getString("phone");
                String email = result.getString("email");
                String password = result.getString("password");
                manager.setId(id);
                manager.setName(name);
                manager.setEmail(email);
                manager.setPassword(password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return manager;
    }
}
