package com.gmail.khanhit100896.foody.restaurant;

public class Restaurant {
    private int id;
    private String res_code;
    private String res_name;
    private String num_of_branch;
    private String img_address;
    private String city_code;
    private int action_like;
    private int action_touch;

    public Restaurant(int id, String res_code, String res_name, String num_of_branch, String img_address,
                      String city_code) {
        this.setId(id);
        this.setRes_code(res_code);
        this.setRes_name(res_name);
        this.setNum_of_branch(num_of_branch);
        this.setImg_address(img_address);
        this.setCity_code(city_code);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRes_code() {
        return res_code;
    }

    public void setRes_code(String res_code) {
        this.res_code = res_code;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public String getNum_of_branch() {
        return num_of_branch;
    }

    public void setNum_of_branch(String num_of_branch) {
        this.num_of_branch = num_of_branch;
    }

    public String getImg_address() {
        return img_address;
    }

    public void setImg_address(String img_address) {
        this.img_address = img_address;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
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
}
