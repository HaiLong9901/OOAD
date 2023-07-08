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

    public List<Department> getBillDetail() {
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
		int price = result.getInt("price");
                int total = result.getInt("total");
                BillDetail billDetail = new BillDetail(billid, component, quantity, price, total);
		detailBillList.add(billDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detailBillList;
    }
}