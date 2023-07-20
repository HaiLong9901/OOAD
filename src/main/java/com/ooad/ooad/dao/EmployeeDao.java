package com.ooad.ooad.dao;

import com.ooad.ooad.entity.Employee;
import com.ooad.ooad.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDao {
    Connection connection;
    PreparedStatement prepare;
    ResultSet result;

    public void createEmployee(Employee employee) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "insert into ooad.employee(name, phone, email, password) values(?,?,?,?)";
        try {
            prepare = connection.prepareStatement(sql);
            prepare.setString(1,employee.getName());
            prepare.setString(2, employee.getPhone());
            prepare.setString(3, employee.getEmail());
            prepare.setString(4, employee.getPassword());
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
