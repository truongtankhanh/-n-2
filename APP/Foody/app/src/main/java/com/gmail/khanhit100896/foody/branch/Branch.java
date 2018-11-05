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

    public Branch(int id, String branch_code, String res_code, String branch_name, String branch_address, String branch_opentime, String branch_price, String branch_image) {
        this.id = id;
        this.branch_code = branch_code;
        this.res_code = res_code;
        this.branch_name = branch_name;
        this.branch_address = branch_address;
        this.branch_opentime = branch_opentime;
        this.branch_price = branch_price;
        this.branch_image = branch_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getRes_code() {
        return res_code;
    }

    public void setRes_code(String res_code) {
        this.res_code = res_code;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getBranch_address() {
        return branch_address;
    }

    public void setBranch_address(String branch_address) {
        this.branch_address = branch_address;
    }

    public String getBranch_opentime() {
        return branch_opentime;
    }

    public void setBranch_opentime(String branch_opentime) {
        this.branch_opentime = branch_opentime;
    }

    public String getBranch_price() {
        return branch_price;
    }

    public void setBranch_price(String branch_price) {
        this.branch_price = branch_price;
    }

    public String getBranch_image() {
        return branch_image;
    }

    public void setBranch_image(String branch_image) {
        this.branch_image = branch_image;
    }
}
