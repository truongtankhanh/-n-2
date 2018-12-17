package com.gmail.khanhit100896.foody.food;

public class Food {
    private int id;
    private String food_code;
    private String food_name;
    private String food_address;
    private String food_price;
    private String img_address;
    private String res_code;
    private String kind_code;
    private int action_like;
    private int action_touch;
    private double distance;

    public Food(int id, String food_code, String food_name, String food_address, String food_price,
                String img_address, String res_code, String kind_code) {
        this.id = id;
        this.food_code = food_code;
        this.food_name = food_name;
        this.food_address = food_address;
        this.food_price = food_price;
        this.img_address = img_address;
        this.res_code = res_code;
        this.kind_code = kind_code;
    }

    public Food(int id, String food_code, String food_name, String food_address, String food_price,
                String img_address, String res_code, String kind_code, int action_like) {
        this.id = id;
        this.food_code = food_code;
        this.food_name = food_name;
        this.food_address = food_address;
        this.food_price = food_price;
        this.img_address = img_address;
        this.res_code = res_code;
        this.kind_code = kind_code;
        this.action_like = action_like;
    }

    public Food(int id, String food_code, String food_name, String food_address, String food_price,
                String img_address, String res_code, String kind_code, int action_like, double distance) {
        this.id = id;
        this.food_code = food_code;
        this.food_name = food_name;
        this.food_address = food_address;
        this.food_price = food_price;
        this.img_address = img_address;
        this.res_code = res_code;
        this.kind_code = kind_code;
        this.action_like = action_like;
        this.setDistance(distance);
    }

    public int getId() {
        return id;
    }

    public String getFood_code() {
        return food_code;
    }

    public String getFood_name() {
        return food_name;
    }

    public String getFood_address() {
        return food_address;
    }

    public String getFood_price() {
        return food_price;
    }

    public String getImg_address() {
        return img_address;
    }

    public String getRes_code() {
        return res_code;
    }

    public String getKind_code() {
        return kind_code;
    }

    public int getAction_like() {
        return action_like;
    }

    public void setAction_like(int action_like) {
        this.action_like = action_like;
    }

    public int getAction_touch() {
        return action_touch;
    }

    public void setAction_touch(int action_touch) {
        this.action_touch = action_touch;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
