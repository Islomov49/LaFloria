package com.iso.developer.lafloria.datamoduls;

/**
 * Created by developer on 02.11.2016.
 */

public class ProductEntity {
    private String nameOfProduct;
    private float ammountOfProduct;
    private int locationCode;
    private CardDataCategoryEntity cardDataCategoryEntity;
    private String infoOfProduct;
    private long timeDelivery;
    private String phoneNumber;

    public ProductEntity(String nameOfProduct, float ammountOfProduct, int locationCode, CardDataCategoryEntity cardDataCategoryEntity, String infoOfProduct, long timeDelivery, String phoneNumber) {
        this.nameOfProduct = nameOfProduct;
        this.ammountOfProduct = ammountOfProduct;
        this.locationCode = locationCode;
        this.cardDataCategoryEntity = cardDataCategoryEntity;
        this.infoOfProduct = infoOfProduct;
        this.timeDelivery = timeDelivery;
        this.phoneNumber = phoneNumber;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public float getAmmountOfProduct() {
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

    public CardDataCategoryEntity getCardDataCategoryEntity() {
        return cardDataCategoryEntity;
    }

    public void setCardDataCategoryEntity(CardDataCategoryEntity cardDataCategoryEntity) {
        this.cardDataCategoryEntity = cardDataCategoryEntity;
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
}
