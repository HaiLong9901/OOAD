package com.ooad.ooad.dao;

import com.ooad.ooad.entity.Leader;
import com.ooad.ooad.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        connection.close();
    }

    public List<Leader> getAllLeader() throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.leader where isactive=true";
        ArrayList<Leader> leaderList = new ArrayList<>();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String password = result.getString("password");
                int depId = result.getInt("depid");
                String phone = result.getString("phone");
                Leader leader = new Leader(id, name, phone, email, password, depId);
                leaderList.add(leader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
        return leaderList;
    }

    public void deleteLeader(int leaderId) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "update ooad.leader set isactive=false where id=" + leaderId;
        try {
            prepare = connection.prepareStatement(sql);
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
    }

    public Leader getLeaderByPhone(String phone) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.leader where phone=" + phone;
        Leader leader = new Leader();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String phoneNum = result.getString("phone");
                int depId = result.getInt("depid");
                String password = result.getString("password");
                leader.setId(id);
                leader.setPhone(phoneNum);
                leader.setEmail(email);
                leader.setName(name);
                leader.setDepId(depId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
        return leader;
    }

    public Leader getLeaderById(int id) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.leader where id=" + id;
        Leader leader = new Leader();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int leaderId = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String phoneNum = result.getString("phone");
                int depId = result.getInt("depid");
                String password = result.getString("password");
                leader.setId(id);
                leader.setPhone(phoneNum);
                leader.setEmail(email);
                leader.setName(name);
                leader.setDepId(depId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
        return leader;
    }
}
