package com.iso.developer.lafloria.datamoduls;

/**
 * Created by developer on 02.11.2016.
 */

public class ClientEntity {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String photoPath;
    private boolean isTrusted;

    public ClientEntity(String fullName, String phoneNumber, String email, String photoPath, boolean isTrusted) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.photoPath = photoPath;
        this.isTrusted = isTrusted;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public boolean isTrusted() {
        return isTrusted;
    }

    public void setTrusted(boolean trusted) {
        isTrusted = trusted;
    }
}
