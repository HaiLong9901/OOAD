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

    public List<Department> getAllDepartment() {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depList;
    }
}
