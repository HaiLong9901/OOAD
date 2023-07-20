package com.ooad.ooad.dao;

import com.ooad.ooad.entity.Leader;
import com.ooad.ooad.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LeaderDao {
    Connection connection;
    PreparedStatement prepare;
    ResultSet result;

    public void createLeader(Leader leader) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "insert into ooad.leader(name, phone, email, password, depid) values(?,?,?,?,?)";
        try {
            prepare = connection.prepareStatement(sql);
            prepare.setString(1, leader.getName());
            prepare.setString(2, leader.getPhone());
            prepare.setString(3, leader.getEmail());
            prepare.setString(4, leader.getPassword());
            prepare.setInt(5, leader.getDepId());
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
