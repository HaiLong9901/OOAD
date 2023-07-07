package com.ooad.ooad.entity;

public class BillDetail {
    private int id;
    private int billId;
    private String component;
    private int quantity;
    private long price;
    private long total;
    private String unit;

    public BillDetail() {
    }

    public BillDetail(int billId, String component, int quantity, long price, long total, String unit) {
        this.billId = billId;
        this.component = component;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "BillDetail{" +
                "id=" + id +
                ", billId=" + billId +
                ", component='" + component + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", total=" + total +
                ", unit='" + unit + '\'' +
                '}';
    }
}
