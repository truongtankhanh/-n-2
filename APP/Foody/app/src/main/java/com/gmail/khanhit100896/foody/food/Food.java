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

    public Food(int id, String food_code, String food_name, String food_address, String food_price, String img_address, String res_code, String kind_code) {
        this.setId(id);
        this.setFood_code(food_code);
        this.setFood_name(food_name);
        this.setFood_address(food_address);
        this.setFood_price(food_price);
        this.setImg_address(img_address);
        this.setRes_code(res_code);
        this.setKind_code(kind_code);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFood_code() {
        return food_code;
    }

    public void setFood_code(String food_code) {
        this.food_code = food_code;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_address() {
        return food_address;
    }

    public void setFood_address(String food_address) {
        this.food_address = food_address;
    }

    public String getFood_price() {
        return food_price;
    }

    public void setFood_price(String food_price) {
        this.food_price = food_price;
    }

    public String getImg_address() {
        return img_address;
    }

    public void setImg_address(String img_address) {
        this.img_address = img_address;
    }

    public String getRes_code() {
        return res_code;
    }

    public void setRes_code(String res_code) {
        this.res_code = res_code;
    }

    public String getKind_code() {
        return kind_code;
    }

    public void setKind_code(String kind_code) {
        this.kind_code = kind_code;
    }
}
