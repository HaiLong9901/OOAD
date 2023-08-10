package com.ooad.ooad.dao;

import com.ooad.ooad.entity.Bill;
import com.ooad.ooad.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDao {
    Connection connection;
    PreparedStatement prepare;
    ResultSet resultSet;

    public int createBill(Bill bill) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "insert into ooad.bill(employeeid, equipid, total) values(?,?,?)";
        int id = -1;
        try {
            prepare = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepare.setInt(1, bill.getEmpId());
            prepare.setString(2, bill.getEqipId());
            prepare.setLong(3, bill.getTotal());
            prepare.executeUpdate();
            resultSet = prepare.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
        return id;
    }

    public List<Bill> getAllBillByEmployeeId(int id) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.bill where employeeid=" + id + " order by createdat desc";
        List<Bill> list = new ArrayList<>();
        try {
            prepare = connection.prepareStatement(sql);
            resultSet = prepare.executeQuery();
            while (resultSet.next()) {
                int billId = resultSet.getInt("id");
                String equipId = resultSet.getString("equipId");
                Date createdAt = resultSet.getDate("createdat");
                long total = resultSet.getLong("total");
                Bill bill = new Bill(billId, id, equipId, createdAt, total);
                list.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
        return list;
    }

    public List<Bill> getAllBill() throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.bill order by createdat desc";
        List<Bill> list = new ArrayList<>();
        try {
            prepare = connection.prepareStatement(sql);
            resultSet = prepare.executeQuery();
            while (resultSet.next()) {
                int billId = resultSet.getInt("id");
                String equipId = resultSet.getString("equipId");
                Date createdAt = resultSet.getDate("createdat");
                int empId = resultSet.getInt("employeeId");
                long total = resultSet.getLong("total");
                Bill bill = new Bill(billId, empId, equipId, createdAt, total);
                list.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
        return list;
    }
}
