package com.ooad.ooad.entity;

public class RequestDetail {
    private int requestId;
    private int equipId;
    private boolean isActive;
    private String desc;

    public RequestDetail() {
    }

    public RequestDetail(int requestId, int equipId, boolean isActive, String desc) {
        this.requestId = requestId;
        this.equipId = equipId;
        this.isActive = isActive;
        this.desc = desc;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getEquipId() {
        return equipId;
    }

    public void setEquipId(int equipId) {
        this.equipId = equipId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "RequestDetail{" +
                "requestId=" + requestId +
                ", equipId=" + equipId +
                ", isActive=" + isActive +
                ", desc='" + desc + '\'' +
                '}';
    }
}
