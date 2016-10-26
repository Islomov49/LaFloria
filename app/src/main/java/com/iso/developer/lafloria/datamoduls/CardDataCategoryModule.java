package com.iso.developer.lafloria.datamoduls;

import com.iso.developer.lafloria.utils.TypeOfCategory;

/**
 * Created by developer on 07.10.2016.
 */

public class CardDataCategoryModule {
    private String nameOfCategory;
    private String linkOfCategoryPhoto;
    private TypeOfCategory typeOfCategory;


    private String localCachedPhotoPath;
    private int extraType;
    private String extraString;
    private int priorityCategory;

    // temp construct for debug
    public CardDataCategoryModule(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }

    public CardDataCategoryModule(String nameOfCategory, TypeOfCategory typeOfCategory, String linkOfCategoryPhoto, int priorityCategory) {
        this.nameOfCategory = nameOfCategory;
        this.typeOfCategory = typeOfCategory;
        this.linkOfCategoryPhoto = linkOfCategoryPhoto;
        this.priorityCategory = priorityCategory;

    }


    public String getNameOfCategory() {
        return nameOfCategory;
    }

    public void setNameOfCategory(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }

    public TypeOfCategory getTypeOfCategory() {
        return typeOfCategory;
    }

    public void setTypeOfCategory(TypeOfCategory typeOfCategory) {
        this.typeOfCategory = typeOfCategory;
    }

    public String getLinkOfCategoryPhoto() {
        return linkOfCategoryPhoto;
    }

    public void setLinkOfCategoryPhoto(String linkOfCategoryPhoto) {
        this.linkOfCategoryPhoto = linkOfCategoryPhoto;
    }

    public int getExtraType() {
        return extraType;
    }

    public void setExtraType(int extraType) {
        this.extraType = extraType;
    }

    public String getExtraString() {
        return extraString;
    }

    public void setExtraString(String extraString) {
        this.extraString = extraString;
    }

    public int getPriorityCategory() {
        return priorityCategory;
    }

    public void setPriorityCategory(int priorityCategory) {
        this.priorityCategory = priorityCategory;
    }

    public String getLocalCachedPhotoPath() {
        return localCachedPhotoPath;
    }

    public void setLocalCachedPhotoPath(String localCachedPhotoPath) {
        this.localCachedPhotoPath = localCachedPhotoPath;
    }

}
