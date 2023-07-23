package com.ooad.ooad.dao;

import com.ooad.ooad.entity.Employee;
import com.ooad.ooad.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Employee> getAllEmployee() throws SQLException{
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.employee where isactive=true";
        ArrayList<Employee> employeesList = new ArrayList<>();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String phone = result.getString("phone");
                String email = result.getString("email");
                String password = result.getString("password");
                Employee employee = new Employee(id, name, phone, email, password);
                employeesList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  employeesList;
    }
}
