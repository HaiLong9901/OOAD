package com.ooad.ooad.entity;

import java.util.Date;

public class Equipment {
    private int id;
    private String name;
    private String model;
    private Date purchase;
    private Date expiry;
    private int depId;
    private long price;

    public Equipment() {
    }

    public Equipment(int id, String name, String model, Date purchase, Date expiry, int depId, long price) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.purchase = purchase;
        this.expiry = expiry;
        this.depId = depId;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getPurchase() {
        return purchase;
    }

    public void setPurchase(Date purchase) {
        this.purchase = purchase;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", purchase=" + purchase +
                ", expiry=" + expiry +
                ", depId=" + depId +
                ", price=" + price +
                '}';
    }
}
