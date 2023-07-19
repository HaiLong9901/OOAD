package com.ooad.ooad.dao;

import com.ooad.ooad.entity.BillDetail;
import com.ooad.ooad.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDetailDao {
    private Connection connection;
    private ResultSet result;
    private PreparedStatement prepare;

    public List<BillDetail> getBillDetail() throws SQLException{
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.detail_bill";
        ArrayList<BillDetail> detailBillList = new ArrayList<>();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {	
                int billid = result.getInt("billid");
                String component = result.getString("component");
		int quantity = result.getInt("quantity");
		long price = result.getLong("price");
                long total = result.getLong("total");
                BillDetail billDetail = new BillDetail(billid, component, quantity, price, total);
		detailBillList.add(billDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detailBillList;
    }
    public static BillDetail findDetailBill(Connection conn, int billid) throws  SQLException {
        String sql = "select * from detail_bill where billid = ?";
        PreparedStatement prsm = conn.prepareStatement(sql);
        prsm.setInt(1, billid);
        ResultSet rs = prsm.executeQuery();
        while (rs.next()) {
            int billid = result.getInt("billid");
            String component = result.getString("component");
	    int quantity = result.getInt("quantity");
	    long price = result.getLong("price");
            long total = result.getLong("total");
             BillDetail billDetail = new BillDetail(billid, component, quantity, price, total);
            return billDetail;
        }
        return null;
    }
   public static void insertDetailBill(Connection conn, BillDetail detailbill) throws SQLException {
        String sql = "insert into detail_bill(component, quantity, price, total)\n" +
                "values (?,?,?,?)";
        PreparedStatement prsm = conn.prepareStatement(sql);
        prsm.setString(1, detailbill.getComponent());
        prsm.setInt(2, detailbill.getQuantity());
        prsm.setLong(3, detailbill.getPrice());
        prsm.setLong(4, detailbill.getTotal());
       
        prsm.executeUpdate();
    }
    public static void updateDetailBill(Connection conn, BillDetail detailbill) throws SQLException {
        String sql = "update detail_bill set component = ?, quantity = ?, price = ?, total = ? where billid = ?";
        PreparedStatement prsm = conn.prepareStatement(sql);
        prsm.setString(1, detailbill.getComponent());
        prsm.setInt(2, detailbill.getQuantity());
        prsm.setLong(3, detailbill.getPrice());
        prsm.setLong(4, detailbill.getTotal());
        prsm.executeUpdate();
    }
   public static void deleteDetailBill(Connection conn, String detailbill) throws SQLException {
        String sql = "update detail_bill set isactive=false where detailbill=?";
        PreparedStatement prsm = conn.prepareStatement(sql);
        int ID = 0;
        try {
            ID = Integer.parseInt(detailbill);
        } catch (Exception e) {
            e.printStackTrace();
        }
        prsm.setInt(1, ID);
        prsm.executeUpdate();
    }
}