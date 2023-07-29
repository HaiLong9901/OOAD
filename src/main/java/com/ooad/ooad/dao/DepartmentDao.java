package com.ooad.ooad.dao;

import com.ooad.ooad.entity.Department;
import com.ooad.ooad.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {
    private Connection connection;
    private ResultSet result;
    private PreparedStatement prepare;

    public List<Department> getAllDepartment() throws SQLException{
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.department";
        ArrayList<Department> depList = new ArrayList<>();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String address = result.getString("address");
                Department department = new Department(id, name, address);
                depList.add(department);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return depList;
    }

    public void createDepartment(Department department) throws SQLException{
        connection = DBUtil.connectDB();
        String sql = "insert into ooad.department(name, address) values(?,?)";
        try {
            prepare = connection.prepareStatement(sql);
            prepare.setString(1, department.getName());
            prepare.setString(2, department.getAddress());
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Department getDepartmentById(int id) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.department where id=" + id;
        Department department = new Department();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                String name = result.getString("name");
                String address = result.getString("address");
                department.setName(name);
                department.setAddress(address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return department;
    }
}
