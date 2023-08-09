package com.ooad.ooad.entity;

import java.util.Date;

public class Assignment {
    private int id;
    private int requestId;
    private int managerId;
    private int empId;
    private int status;
    private Date createdAt;
    private Date expectation;
    public Assignment() {
    }

    public Assignment(int requestId, int managerId, int empId, int status, Date expectation) {
        this.requestId = requestId;
        this.managerId = managerId;
        this.empId = empId;
        this.status = status;
        this.expectation = expectation;
    }

    public Assignment(int requestId, int managerId, int empId, int status, Date createdAt, Date expectation) {
        this.requestId = requestId;
        this.managerId = managerId;
        this.empId = empId;
        this.status = status;
        this.createdAt = createdAt;
        this.expectation = expectation;
    }

    public Assignment(int id, int requestId, int managerId, int empId, int status, Date createdAt, Date expectation) {
        this.id = id;
        this.requestId = requestId;
        this.managerId = managerId;
        this.empId = empId;
        this.status = status;
        this.createdAt = createdAt;
        this.expectation = expectation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpectation() {
        return expectation;
    }

    public void setExpectation(Date expectation) {
        this.expectation = expectation;
    }


    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", requestId=" + requestId +
                ", managerId=" + managerId +
                ", empId=" + empId +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", expectation=" + expectation +
                '}';
    }
}
