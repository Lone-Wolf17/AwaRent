package com.gmail.korex006.awarent;

public class User {
    private String userName;
    private String userPhoneNumber;
    private String emailAddress;
    private String imageresource;

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getImageresource() {
        return imageresource;
    }

    public void setImageresource(String imageresource) {
        this.imageresource = imageresource;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
