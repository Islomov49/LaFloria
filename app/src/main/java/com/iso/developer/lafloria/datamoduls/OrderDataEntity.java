package com.iso.developer.lafloria.datamoduls;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by developer on 02.11.2016.
 */

public class OrderDataEntity {
    ClientEntity clientEntity;
    ArrayList<ProductEntity> productEntity;
    long orderDate;
    boolean delevered;
    double locationX;
    double locationY;

    public OrderDataEntity(ClientEntity clientEntity,  ArrayList<ProductEntity> productEntity, long orderDate, boolean delevered, double locationX, double locationY) {
        this.clientEntity = clientEntity;
        this.productEntity = productEntity;
        this.orderDate = orderDate;
        this.delevered = delevered;
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    public  ArrayList<ProductEntity> getProductEntity() {
        return productEntity;
    }

    public void setProductEntity( ArrayList<ProductEntity> productEntity) {
        this.productEntity = productEntity;
    }

    public long getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(long orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isDelevered() {
        return delevered;
    }

    public void setDelevered(boolean delevered) {
        this.delevered = delevered;
    }

    public double getLocationX() {
        return locationX;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }
}
