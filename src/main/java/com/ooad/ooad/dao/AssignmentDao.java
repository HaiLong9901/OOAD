package com.ooad.ooad.dao;

import com.ooad.ooad.entity.Assignment;
import com.ooad.ooad.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssignmentDao {
    private Connection connection;
    private ResultSet result;
    private PreparedStatement prepare;
    public List<Assignment> getAllAssignment()   {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.assignment";
        ArrayList<Assignment> assList = new ArrayList<>();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                int requestId = result.getInt("requestId");
                int managerId = result.getInt("managerId");
                int empId = result.getInt("empId");
                int status = result.getInt("status");
                Date createdAt = result.getDate("createdAt");
                Date expectation = result.getDate("expectation");
                int equipId = result.getInt("equipId");
                Assignment assignment = new Assignment( requestId, managerId, empId, status, createdAt, expectation, equipId );
                assList.add(assignment);
                assignment.setId(id);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assList;
    }

    public Assignment findAssignment(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM assignment WHERE id = ?";
        PreparedStatement prsm = conn.prepareStatement(sql);
        prsm.setInt(1, id);
        ResultSet rs = prsm.executeQuery();
        while (rs.next()) {
            int requestId = rs.getInt("requestId");
            int managerId = rs.getInt("managerId");
            int empId = rs.getInt("empId");
            int status = rs.getInt("status");
            Date createdAt = rs.getDate("createdAt");
            Date expectation = rs.getDate("expectation");
            int equipId = rs.getInt("equipId");

            Assignment assignment = new Assignment(requestId, managerId, empId, status, createdAt, expectation, equipId);
            assignment.setId(id);
            return assignment;
        }
        return null;
    }

    public void insertAssignment(Connection conn, Assignment assignment) throws SQLException {
        String sql = "INSERT INTO assignment(requestId, managerId, empId, status, createdAt, expectation, equipId) " +
                "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement prsm = conn.prepareStatement(sql);
        prsm.setInt(1, assignment.getRequestId());
        prsm.setInt(2, assignment.getManagerId());
        prsm.setInt(3, assignment.getEmpId());
        prsm.setInt(4, assignment.getStatus());
        prsm.setDate(5, new java.sql.Date(assignment.getCreatedAt().getTime()));
        prsm.setDate(6, new java.sql.Date(assignment.getExpectation().getTime()));
        prsm.setInt(7, assignment.getEquipId());
        prsm.executeUpdate();
    }

    public void updateAssignment(Connection conn, Assignment assignment) throws SQLException {
        String sql = "UPDATE assignment SET requestId=?, managerId=?, empId=?, status=?, createdAt=?, expectation=?, equipId=? WHERE id=?";
        PreparedStatement prsm = conn.prepareStatement(sql);
        prsm.setInt(1, assignment.getRequestId());
        prsm.setInt(2, assignment.getManagerId());
        prsm.setInt(3, assignment.getEmpId());
        prsm.setInt(4, assignment.getStatus());
        prsm.setDate(5, new java.sql.Date(assignment.getCreatedAt().getTime()));
        prsm.setDate(6, new java.sql.Date(assignment.getExpectation().getTime()));
        prsm.setInt(7, assignment.getEquipId());
        prsm.setInt(8, assignment.getId());
        prsm.executeUpdate();
    }

    public void deleteAssignment(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM assignment WHERE id=?";
        PreparedStatement prsm = conn.prepareStatement(sql);
        prsm.setInt(1, id);
        prsm.executeUpdate();
    }
}






