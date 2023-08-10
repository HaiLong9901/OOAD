package com.ooad.ooad.entity;

import java.util.Date;

public class Bill {
    private int id;
    private int empId;
    private String eqipId;
    private Date createdAt;
    private long total;

    public Bill() {
    }

    public Bill(int id, int empId, String eqipId, Date createdAt, long total) {
        this.id = id;
        this.empId = empId;
        this.eqipId = eqipId;
        this.createdAt = createdAt;
        this.total = total;
    }

    public Bill(int empId, String eqipId, Date createdAt, long total) {
        this.empId = empId;
        this.eqipId = eqipId;
        this.createdAt = createdAt;
        this.total = total;
    }

    public Bill(int empId, String eqipId, long total) {
        this.empId = empId;
        this.eqipId = eqipId;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEqipId() {
        return eqipId;
    }

    public void setEqipId(String eqipId) {
        this.eqipId = eqipId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", empId=" + empId +
                ", eqipId=" + eqipId +
                ", createdAt=" + createdAt +
                ", total=" + total +
                '}';
    }
}
