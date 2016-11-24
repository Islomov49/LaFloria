package com.iso.developer.lafloria.datamoduls;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by developer on 02.11.2016.
 */
@IgnoreExtraProperties
public class ProductEntity {
    private String nameOfProduct; //
    private double ammountOfProduct; //
    private int locationCode; //?
    private String category;
    private String subCategory;
    private String infoOfProduct; //
    private long timeDelivery; //
    private String phoneNumber; //
    private String pathProductImageOne;
    private String pathProductImageSecond;
    private String pathProductImagethirt;

    public ProductEntity(){

    }

    public ProductEntity(String category,String subCategory,String nameOfProduct, double ammountOfProduct, int locationCode, String infoOfProduct, long timeDelivery, String phoneNumber,String firstPhoto,String secondPhoto,String thirtyPhoto) {
        this.nameOfProduct = nameOfProduct;
        this.ammountOfProduct = ammountOfProduct;
        this.locationCode = locationCode;
        this.category = category;
        this.subCategory = subCategory;
        this.phoneNumber = phoneNumber;
        this.timeDelivery = timeDelivery;
        this.infoOfProduct = infoOfProduct;
        pathProductImageOne = firstPhoto;
        pathProductImageSecond = secondPhoto;
        pathProductImagethirt = thirtyPhoto;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public double getAmmountOfProduct() {
        return ammountOfProduct;
    }

    public void setAmmountOfProduct(float ammountOfProduct) {
        this.ammountOfProduct = ammountOfProduct;
    }

    public int getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(int locationCode) {
        this.locationCode = locationCode;
    }



    public String getInfoOfProduct() {
        return infoOfProduct;
    }

    public void setInfoOfProduct(String infoOfProduct) {
        this.infoOfProduct = infoOfProduct;
    }

    public long getTimeDelivery() {
        return timeDelivery;
    }

    public void setTimeDelivery(long timeDelivery) {
        this.timeDelivery = timeDelivery;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAmmountOfProduct(double ammountOfProduct) {
        this.ammountOfProduct = ammountOfProduct;
    }

    public String getPathProductImageOne() {
        return pathProductImageOne;
    }

    public void setPathProductImageOne(String pathProductImageOne) {
        this.pathProductImageOne = pathProductImageOne;
    }

    public String getPathProductImageSecond() {
        return pathProductImageSecond;
    }

    public void setPathProductImageSecond(String pathProductImageSecond) {
        this.pathProductImageSecond = pathProductImageSecond;
    }

    public String getPathProductImagethirt() {
        return pathProductImagethirt;
    }

    public void setPathProductImagethirt(String pathProductImagethirt) {
        this.pathProductImagethirt = pathProductImagethirt;
    }

    @Exclude
    public Map<String, Object> toMap() {
      HashMap<String, Object> result = new HashMap<>();
        result.put("nameOfProduct", nameOfProduct);
        result.put("ammountOfProduct", ammountOfProduct);
        result.put("locationCode", locationCode);
        result.put("locationCode", locationCode);
        result.put("category", category);
        result.put("subCategory", subCategory);
        result.put("phoneNumber", phoneNumber);
        result.put("timeDelivery", timeDelivery);
        result.put("infoOfProduct", infoOfProduct);
        result.put("pathProductImageOne", pathProductImageOne);
        result.put("pathProductImageSecond", pathProductImageSecond);
        result.put("pathProductImagethirt", pathProductImagethirt);
        return result;
        }

}
