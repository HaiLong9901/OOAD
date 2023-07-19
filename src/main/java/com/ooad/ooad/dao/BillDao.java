package com.ooad.ooad.dao;

import com.ooad.ooad.entity.Bill;
import com.ooad.ooad.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public  class BillDao {
    private Connection connection;
    private ResultSet result;
    private PreparedStatement prepare;

    public static  List<Bill> getAllBill() throws SQLException {
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
                Bill bill = new Bill(id, equipid, employeeid, createdat, total);
		billList.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billList;
    }

   public static Bill findBill(Connection conn, int id) throws  SQLException {
        String sql = "select * from bill where id = ?";
        PreparedStatement prsm = conn.prepareStatement(sql);
        prsm.setInt(1, id);
        ResultSet rs = prsm.executeQuery();
        while (rs.next()) {
            int equipid = result.getInt("equipid");
            int employeeid = result.getInt("employeeid");
 	    Timestamp createdat = result.getTimestamp("createdat");
	    int total = result.getInt("total");
            Bill bill = new Bill(id, equipid, employeeid, createdat, total);
            return bill;
        }
        return null;
    }
    public static void insertBill(Connection conn, Bill bill) throws SQLException {
        String sql = "insert into bill( equipid, employeeid, createdat, total)\n" +
                "values (?,?,?,?,?)";
        PreparedStatement prsm = conn.prepareStatement(sql);
        prsm.setInt(1, bill.getEqipId());
        prsm.setInt(2, bill.getEmpId());
        prsm.setTimestamp(3, bill.getCreatedAt());
        prsm.setLong(4, bill.getTotal());
    
        prsm.executeUpdate();
    }
   public static void updateBill(Connection conn, Bill bill) throws SQLException {
        String sql = "update bill set equipid = ?, employeeid = ?, createdat = ?, total = ? where id = ?";
        PreparedStatement prsm = conn.prepareStatement(sql);
        prsm.setInt(1, bill.getEqipId());
        prsm.setInt(2, bill.getEmpId());
        prsm.setTimestamp(3, bill.getCreatedAt());
        prsm.setLong(4, bill.getTotal());
        prsm.executeUpdate();
    }
 public static void deleteBill(Connection conn, String id) throws SQLException {
        String sql = "update bill set isactive=false where id=?";
        PreparedStatement prsm = conn.prepareStatement(sql);
        int ID = 0;
        try {
            ID = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        prsm.setInt(1, ID);
        prsm.executeUpdate();
    }
}