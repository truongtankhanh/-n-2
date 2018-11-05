package com.gmail.khanhit100896.foody.city;

public class City {

    private int id;
    private String cityCode;
    private String cityName;
    private String cityImage;

    public City(int id, String cityCode, String cityName,String cityImage) {
        this.setId(id);
        this.setCityCode(cityCode);
        this.setCityName(cityName);
        this.setCityImage(cityImage);
    }

    public String getCityImage() {
        return cityImage;
    }

    public void setCityImage(String cityImage) {
        this.cityImage = cityImage;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
