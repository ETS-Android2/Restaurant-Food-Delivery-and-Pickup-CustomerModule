package com.example.capstoneproject.Model;

import java.util.List;

public class Order {
    private String custID;
    private String orderTelNo;
    private String orderAddress;
    private String orderType;
    private String orderPrice;
    private String status;
    private String orderRequest;
    private List<CartDetail> foods; //List of food order
    private String custIDStatusFilter;

    public Order() {
    }

    public Order(String custID, String orderTelNo, String orderAddress, String orderType, String orderPrice, String orderRequest, List<CartDetail> foods) {
        this.custID = custID;
        this.orderTelNo = orderTelNo;
        this.orderAddress = orderAddress;
        this.orderType = orderType;
        this.orderPrice = orderPrice;
        this.orderRequest = orderRequest;
        this.foods = foods;
        this.status = "0"; //Default is 0, 0: Placed, 1: Delivering, 2: Delivered
        this.custIDStatusFilter = custID + this.status;
    }

    public String getStatus() {
        return status;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getOrderTelNo() {
        return orderTelNo;
    }

    public void setOrderTelNo(String orderTelNo) {
        this.orderTelNo = orderTelNo;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderRequest() {
        return orderRequest;
    }

    public void setOrderRequest(String orderRequest) {
        this.orderRequest = orderRequest;
    }

    public List<CartDetail> getFoods() {
        return foods;
    }

    public void setFoods(List<CartDetail> foods) {
        this.foods = foods;
    }

    public String getCustIDStatusFilter() {
        return custIDStatusFilter;
    }

    public void setCustIDStatusFilter(String custIDStatusFilter) {
        this.custIDStatusFilter = custIDStatusFilter;
    }
}
