package com.ooad.ooad.entity;

import java.util.Date;

public class Request {
    private int id;
    private int leader;
    private Date createdAt;
    private String equipId;
    private boolean isEquipActive;
    private String descfault;

    public Request() {
    }

    public Request(int leader, String equipId, boolean isEquipActive, String descfault) {
        this.leader = leader;
        this.equipId = equipId;
        this.isEquipActive = isEquipActive;
        this.descfault = descfault;
    }

    public Request(int id, int leader, Date createdAt, String equipId, boolean isEquipActive, String descfault) {
        this.id = id;
        this.leader = leader;
        this.createdAt = createdAt;
        this.equipId = equipId;
        this.isEquipActive = isEquipActive;
        this.descfault = descfault;
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

    public boolean isEquipActive() {
        return isEquipActive;
    }

    public void setEquipActive(boolean equipActive) {
        isEquipActive = equipActive;
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
                ", equipId=" + equipId +
                ", isEquipActive=" + isEquipActive +
                ", descfault='" + descfault + '\'' +
                '}';
    }
}
