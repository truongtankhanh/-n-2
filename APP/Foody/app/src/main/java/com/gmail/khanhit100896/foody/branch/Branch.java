package com.gmail.khanhit100896.foody.branch;

public class Branch {
    private int id;
    private String branch_code;
    private String res_code;
    private String branch_name;
    private String branch_address;
    private String branch_opentime;
    private String branch_price;
    private String branch_image;
    private int action_like;
    private int action_touch;

    public Branch(int id, String branch_code, String res_code, String branch_name, String branch_address,
                  String branch_opentime, String branch_price, String branch_image) {
        this.id = id;
        this.branch_code = branch_code;
        this.res_code = res_code;
        this.setRes_code(res_code);
        this.branch_name = branch_name;
        this.branch_address = branch_address;
        this.branch_opentime = branch_opentime;
        this.branch_price = branch_price;
        this.branch_image = branch_image;
    }

    public Branch(int id, String branch_code, String res_code, String branch_name, String branch_address,
                  String branch_opentime, String branch_price, String branch_image, int action_like) {
        this.id = id;
        this.branch_code = branch_code;
        this.res_code = res_code;
        this.branch_name = branch_name;
        this.branch_address = branch_address;
        this.branch_opentime = branch_opentime;
        this.branch_price = branch_price;
        this.branch_image = branch_image;
        this.action_like = action_like;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public String getBranch_address() {
        return branch_address;
    }

    public String getBranch_opentime() {
        return branch_opentime;
    }

    public String getBranch_price() {
        return branch_price;
    }

    public String getBranch_image() {
        return branch_image;
    }

    public String getBranch_code() {
        return branch_code;
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

    public void setRes_code(String res_code) {
        this.res_code = res_code;
    }

    public String getRes_code() {
        return res_code;
    }
}
