package com.ooad.ooad.entity;

import java.util.Date;

public class Request {
    private int id;
    private int leader;
    private String equipId;
    private Date createdAt;
    private String descfault;

    public Request() {
    }

    public Request(int leader, Date createdAt) {
        this.leader = leader;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeader() {
        return leader;
    }

    public void setLeader(int leader) {
        this.leader = leader;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId;
    }
    public String getDescfault() {
        return descfault;
    }

    public void setDescfault(String descfault) {
        this.descfault = descfault;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", leader=" + leader +
                ", createdAt=" + createdAt +
                '}';
    }
}
