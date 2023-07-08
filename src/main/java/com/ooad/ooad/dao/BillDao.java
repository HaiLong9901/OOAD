package com.ooad.ooad.dao;

import com.ooad.ooad.entity.Bill;
import com.ooad.ooad.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDao {
    private Connection connection;
    private ResultSet result;
    private PreparedStatement prepare;

    public List<Department> getAllBill() {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.bill";
        ArrayList<Bill> billList = new ArrayList<>();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
		int equipid = result.getInt("equipid");
		int employeeid = result.getInt("employeeid");
                Timestamp createdat = result.getTimestamp("createdat");
                int total = result.getInt("total");
                Bill bill = new Bill(id, equipid, employeeid, createdat, total ,bill);
		billList.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billList;
    }
}