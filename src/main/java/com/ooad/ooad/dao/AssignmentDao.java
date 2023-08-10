package com.ooad.ooad.dao;

import com.ooad.ooad.entity.Assignment;
import com.ooad.ooad.entity.Employee;
import com.ooad.ooad.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDao {
    Connection connection;
    PreparedStatement prepare;
    ResultSet result;

    public List<Assignment> getAllAssignment() throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.assignment order by createdAt desc";
        List<Assignment> assignmentList = new ArrayList<>();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                int requestId = result.getInt("requestId");
                int managerId = result.getInt("managerId");
                int employeeId = result.getInt("employeeId");
                int status = result.getInt("status");
                Date createdAt = result.getDate("createdAt");
                Date expectation = result.getDate("expectation");
                Assignment assignment = new Assignment(id, requestId, managerId, employeeId, status, createdAt, expectation);
                assignmentList.add(assignment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
        return assignmentList;
    }

    public void createAssignment(Assignment assignment) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "insert into ooad.assignment(requestId, managerId, employeeId, status, expectation) values(?,?,?,?,?)";
        try {
            prepare = connection.prepareStatement(sql);
            prepare.setInt(1, assignment.getRequestId());
            prepare.setInt(2, assignment.getManagerId());
            prepare.setInt(3, assignment.getEmpId());
            prepare.setInt(4, assignment.getStatus());
            prepare.setDate(5, (Date) assignment.getExpectation());
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
    }

    public Assignment getAssignmentById(int id) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.assignment where id=" + id +  " order by createdAt desc";
        Assignment assignment = new Assignment();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int assId = result.getInt("id");
                int requestId = result.getInt("requestId");
                int managerId = result.getInt("managerId");
                int employeeId = result.getInt("employeeId");
                int status = result.getInt("status");
                Date createdAt = result.getDate("createdAt");
                Date expectation = result.getDate("expectation");
                assignment.setId(assId);
                assignment.setRequestId(requestId);
                assignment.setManagerId(managerId);
                assignment.setEmpId(employeeId);
                assignment.setStatus(status);
                assignment.setCreatedAt(createdAt);
                assignment.setExpectation(expectation);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
        return assignment;
    }

    public void updateAssignStatus(int id, int status) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "update ooad.assignment set status=" + status + " where id=" + id;
        try {
            prepare = connection.prepareStatement(sql);
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
    }

    public List<Assignment> getAssignmentsByEmployeeId(int id) throws SQLException {
        connection = DBUtil.connectDB();
        String sql = "select * from ooad.assignment where employeeid=" + id +  " order by createdAt desc";
        List<Assignment> assignmentList = new ArrayList<>();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                int assignid = result.getInt("id");
                int requestId = result.getInt("requestId");
                int managerId = result.getInt("managerId");
                int employeeId = result.getInt("employeeId");
                int status = result.getInt("status");
                Date createdAt = result.getDate("createdAt");
                Date expectation = result.getDate("expectation");
                Assignment assignment = new Assignment(assignid, requestId, managerId, employeeId, status, createdAt, expectation);
                assignmentList.add(assignment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
        return assignmentList;
    }
}
