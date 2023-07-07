package com.ooad.ooad.entity;

import java.util.Date;

public class Request {
    private int id;
    private int leader;
    private Date createdAt;

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

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", leader=" + leader +
                ", createdAt=" + createdAt +
                '}';
    }
}
