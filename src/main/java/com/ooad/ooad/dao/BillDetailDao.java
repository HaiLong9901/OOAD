package com.ooad.ooad.dao;

import com.ooad.ooad.entity.Bill;
import com.ooad.ooad.entity.BillDetail;
import com.ooad.ooad.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDetailDao {
    Connection connection;
    PreparedStatement prepare;
    ResultSet result;

    public void createBillDetail(BillDetail billDetail) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "insert into ooad.detail_bill(billId, component, quantity, price, total, unit) values(?,?,?,?,?,?)";
        try {
            prepare = connection.prepareStatement(sql);
            prepare.setInt(1, billDetail.getBillId());
            prepare.setString(2, billDetail.getComponent());
            prepare.setInt(3, billDetail.getQuantity());
            prepare.setLong(4, billDetail.getPrice());
            prepare.setLong(5, billDetail.getTotal());
            prepare.setString(6, billDetail.getUnit());
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
    }

    public List<BillDetail> getBillDetailByBillId(int billId) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.detail_bill where billId=" + billId;
        List<BillDetail> list = new ArrayList<>();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String unit = result.getString("unit");
                Long price = result.getLong("price");
                Long total = result.getLong("total");
                String component = result.getString("component");
                int quantity = result.getInt("quantity");
                BillDetail billDetail = new BillDetail(billId, component, quantity, price, total, unit);
                list.add(billDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
