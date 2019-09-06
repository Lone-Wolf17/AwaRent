package com.gmail.korex006.awarent;

import java.io.Serializable;

public class Vacancy implements Serializable {
    private User poster;
    private String mPoster;
    private String location;
    private String apartmentType;
    private String apartmentLocation;
    private String mPrice;
    private String imageName;
    private String imageUrl;

    public Vacancy (){}

    public Vacancy(String poster, String apartmentType, String apartmentLocation, String mPrice) {
        this.setmPoster(poster);
        this.setApartmentType(apartmentType);
        this.setApartmentLocation(apartmentLocation);
        this.setmPrice(mPrice);
//        this.setImageName(imageName);
//        this.setImageUrl(imageUrl);
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getmPoster() {
        return mPoster;
    }

    public void setmPoster(String mPoster) {
        this.mPoster = mPoster;
    }
    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public String getApartmentLocation() {
        return apartmentLocation;
    }

    public void setApartmentLocation(String apartmentLocation) {
        this.apartmentLocation = apartmentLocation;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }
}
