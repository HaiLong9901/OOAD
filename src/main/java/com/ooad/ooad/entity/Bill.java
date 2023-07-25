package com.ooad.ooad.entity;

import java.util.Date;

public class Bill {
    private int id;
    private int empId;
    private String equipId;
    private Date createdAt;
    private long total;

    public Bill() {
    }

    public Bill(int empId, String equipId, Date createdAt, long total) {
        this.empId = empId;
        this.equipId = equipId;
        this.createdAt = createdAt;
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

    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId;
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
                ", eqipId=" + equipId +
                ", createdAt=" + createdAt +
                ", total=" + total +
                '}';
    }
}
