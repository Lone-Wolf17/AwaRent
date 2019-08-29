package com.gmail.korex006.awarent;

public class Vacancy {
    private User poster;
    private String location;
    private String apartmentType;
    private String apartmentLocation;
    private float mPrice;

    public Vacancy(User poster, String location, String apartmentType, String apartmentLocation, float mPrice) {
        this.poster = poster;
        this.location = location;
        this.apartmentType = apartmentType;
        this.apartmentLocation = apartmentLocation;
        this.mPrice = mPrice;
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

    public float getmPrice() {
        return mPrice;
    }

    public void setmPrice(float mPrice) {
        this.mPrice = mPrice;
    }
}
