package com.ooad.ooad.dao;

import com.ooad.ooad.entity.Request;
import com.ooad.ooad.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDao {
    Connection connection;
    PreparedStatement prepare;
    ResultSet result;

    public void createRequest(Request request) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "insert into ooad.request(leader, equipId, isEquipActive, descfault) values(?,?,?,?)";
        try {
            prepare = connection.prepareStatement(sql);
            prepare.setInt(1, request.getLeader());
            prepare.setString(2, request.getEquipId());
            prepare.setBoolean(3, request.isEquipActive());
            prepare.setString(4, request.getDescfault());
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Request> getAllRequestByDepId(int depId) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.request inner join ooad.leader on leader.id = request.leader where depId=" + depId + " order by createdat desc";
        List<Request> requestList = new ArrayList<>();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                int idleader = result.getInt("leader");
                Date createdAt = result.getDate("createdat");
                String equipId = result.getString("equipId");
                int dep = result.getInt("depid");
                boolean isActive = result.getBoolean("isEquipActive");
                String desc = result.getString("descfault");
                Request request = new Request(id, idleader, createdAt, equipId, isActive, desc);
                requestList.add(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestList;
    }

    public Request getRequestById(int id) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.request inner join ooad.leader on leader.id = request.leader where request.id=" + id;
        Request request = null;
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int reqId = result.getInt("id");
                int idleader = result.getInt("leader");
                Date createdAt = result.getDate("createdat");
                String equipId = result.getString("equipId");
                int dep = result.getInt("depid");
                boolean isActive = result.getBoolean("isEquipActive");
                String desc = result.getString("descfault");
                request = new Request(reqId, idleader, createdAt, equipId, isActive, desc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }

    public List<Request> getAllRequest() throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.request inner join ooad.leader on leader.id = request.leader order by createdat desc";
        List<Request> requestList = new ArrayList<>();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                int idleader = result.getInt("leader");
                Date createdAt = result.getDate("createdat");
                String equipId = result.getString("equipId");
                int dep = result.getInt("depid");
                boolean isActive = result.getBoolean("isEquipActive");
                String desc = result.getString("descfault");
                Request request = new Request(id, idleader, createdAt, equipId, isActive, desc);
                requestList.add(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestList;
    }
}
