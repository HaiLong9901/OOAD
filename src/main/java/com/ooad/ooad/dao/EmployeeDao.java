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

    private Connection connection;
    private ResultSet result;
    private PreparedStatement prepare;
    public List<Employee> getAllEmployee() {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.employee";
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
                Boolean isActive = result.getBoolean("isActive");
                String devision = result.getString("devision");
                Employee employee = new Employee( name, phone, email, password, devision);
                employee.setId(id);
                employee.setActive(isActive);
                employeesList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeesList;
    }


    public Employee findEmployee(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM employee WHERE id = ?";
        PreparedStatement prsm = conn.prepareStatement(sql);
        prsm.setInt(1, id);
        ResultSet rs = prsm.executeQuery();
        while (rs.next()) {
            String name = rs.getString("name");
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String devision = rs.getString("devision");

            Employee employee = new Employee( name, phone, email, password, devision);
            return employee;
        }
        return null;
    }

    public void insertEmployee(Connection conn, Employee employee) throws SQLException {
        String sql = "INSERT INTO employee(name, phone, email, password, devision)\n" +
                "VALUES (?,?,?,?,?)";
        PreparedStatement prsm = conn.prepareStatement(sql);
        prsm.setString(1, employee.getName());
        prsm.setString(2, employee.getPhone());
        prsm.setString(3, employee.getEmail());
        prsm.setString(4, employee.getPassword());
        prsm.setString(5, employee.getDevision());

        prsm.executeUpdate();
    }

    public void updateEmployee(Connection conn, Employee employee) throws SQLException {
        String sql = "UPDATE employee SET name=?, phone=?, email=?, password=?, devision=? WHERE id=?";
        PreparedStatement prsm = conn.prepareStatement(sql);
        prsm.setString(1, employee.getName());
        prsm.setString(2, employee.getPhone());
        prsm.setString(3, employee.getEmail());
        prsm.setString(4, employee.getPassword());
        prsm.setString(5, employee.getDevision());
        prsm.setInt(6, employee.getId());

        prsm.executeUpdate();
    }

    public void deleteEmployee(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM employee WHERE id=?";
        PreparedStatement prsm = conn.prepareStatement(sql);
        prsm.setInt(1, id);
        prsm.executeUpdate();
    }

}
