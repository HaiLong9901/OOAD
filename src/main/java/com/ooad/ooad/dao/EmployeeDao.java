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
        connection.close();
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
                System.out.println(employee.toString());
                employeesList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
        return  employeesList;
    }

    public void deleteEmployee(int employeeId) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "update ooad.employee set isactive=false where id=" + employeeId;
        try {
            prepare = connection.prepareStatement(sql);
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
    }

    public Employee getEmployeeById(int id) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.employee where id=" + id;
        Employee employee = new Employee();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int empId = result.getInt("id");
                String name = result.getString("name");
                String phone = result.getString("phone");
                String email = result.getString("email");
                String password = result.getString("password");
                employee.setId(id);
                employee.setName(name);
                employee.setEmail(email);
                employee.setPassword(password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
        return employee;
    }

    public Employee getEmployeeByPhone(String phone) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.employee where phone=" + phone;
        Employee employee = new Employee();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int empId = result.getInt("id");
                String name = result.getString("name");
                String empPhone = result.getString("phone");
                String email = result.getString("email");
                String password = result.getString("password");
                employee.setId(empId);
                employee.setName(name);
                employee.setEmail(email);
                employee.setPassword(password);
                employee.setPhone(empPhone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
        return employee;
    }

    public void updateEmployee(Employee employee) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "update ooad.employee set name=?, phone=?, password=?, email=? where id=?";
        try {
            prepare = connection.prepareStatement(sql);
            prepare.setString(1, employee.getName());
            prepare.setString(2, employee.getPhone());
            prepare.setString(3, employee.getPassword());
            prepare.setString(4, employee.getEmail());
            prepare.setInt(5, employee.getId());
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
    }
}
