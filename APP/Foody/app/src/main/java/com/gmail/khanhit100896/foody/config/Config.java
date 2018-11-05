package com.gmail.khanhit100896.foody.config;

import com.google.firebase.auth.FirebaseAuth;

public class Config {
    private static final Config config = new Config();

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private String ip;
    private String pathLoadImg;
    private String pathLoadImgRes;
    private String pathLoadImgFood;
    private String pathLoadImgBranch;

    private String pathGetAllCity;
    private String pathGetAllRestaurant;
    private String pathGetAllFood;
    private String pathGetAllKind;
    private String pathGetAllBranch;
    private String pathGetAllFoodBuffet;
    private String pathGetAllFoodChay;
    private String pathGetAllFoodAV;

    private void setIp() {
        this.ip = "http://192.168.1.5";
    }

    private Config() {
        // [START initialize_auth]
        this.setmAuth(FirebaseAuth.getInstance());
        // [END initialize_auth]

        this.setIp();
        this.setPathLoadImg(this.getIp().concat("/foody-admin/"));
        this.setPathLoadImgRes(this.getIp().concat("/foody-admin/src/admin/modules/user/restaurant/"));
        this.setPathLoadImgFood(this.getIp().concat("/foody-admin/src/admin/modules/user/food/"));
        this.setPathLoadImgBranch(this.getIp().concat("/foody-admin/src/admin/modules/user/branch/"));
        this.setPathGetAllCity(this.getIp().concat("/foody-admin/src/libs/getAllCity.php"));
        this.setPathGetAllRestaurant(this.getIp().concat("/foody-admin/src/libs/getAllRestaurant.php"));
        this.setPathGetAllFood(this.getIp().concat("/foody-admin/src/libs/getAllFood.php"));
        this.setPathGetAllKind(this.getIp().concat("/foody-admin/src/libs/getAllKind.php"));
        this.setPathGetAllBranch(this.getIp().concat("/foody-admin/src/libs/getAllBranch.php"));
        this.setPathGetAllFoodBuffet(this.getIp().concat("/foody-admin/src/libs/getAllBuffetFood.php"));
        this.setPathGetAllFoodChay(this.getIp().concat("/foody-admin/src/libs/getAllChayFood.php"));
        this.setPathGetAllFoodAV(this.getIp().concat("/foody-admin/src/libs/getAllAVFood.php"));
    }

    public void signOut() {
        // Firebase sign out
        this.getmAuth().signOut();
        // Google sign out
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    private void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    public String getPathLoadImg() {
        return pathLoadImg;
    }

    private void setPathLoadImg(String pathLoadImg) {
        this.pathLoadImg = pathLoadImg;
    }

    public String getPathGetAllCity() {
        return pathGetAllCity;
    }

    private void setPathGetAllCity(String pathGetAllCity) {
        this.pathGetAllCity = pathGetAllCity;
    }

    private String getIp() {
        return ip;
    }

    public static Config getConfig() {
        return config;
    }

    public String getPathGetAllRestaurant() {
        return pathGetAllRestaurant;
    }

    private void setPathGetAllRestaurant(String pathGetAllRestaurant) {
        this.pathGetAllRestaurant = pathGetAllRestaurant;
    }

    public String getPathLoadImgRes() {
        return pathLoadImgRes;
    }

    private void setPathLoadImgRes(String pathLoadImgRes) {
        this.pathLoadImgRes = pathLoadImgRes;
    }

    public String getPathLoadImgFood() {
        return pathLoadImgFood;
    }

    private void setPathLoadImgFood(String pathLoadImgFood) {
        this.pathLoadImgFood = pathLoadImgFood;
    }

    public String getPathGetAllFood() {
        return pathGetAllFood;
    }

    private void setPathGetAllFood(String pathGetAllFood) {
        this.pathGetAllFood = pathGetAllFood;
    }

    public String getPathGetAllKind() {
        return pathGetAllKind;
    }

    private void setPathGetAllKind(String pathGetAllKind) {
        this.pathGetAllKind = pathGetAllKind;
    }

    public String getPathGetAllBranch() {
        return pathGetAllBranch;
    }

    private void setPathGetAllBranch(String pathGetAllBranch) {
        this.pathGetAllBranch = pathGetAllBranch;
    }

    public String getPathLoadImgBranch() {
        return pathLoadImgBranch;
    }

    private void setPathLoadImgBranch(String pathLoadImgBranch) {
        this.pathLoadImgBranch = pathLoadImgBranch;
    }

    public String getPathGetAllFoodBuffet() {
        return pathGetAllFoodBuffet;
    }

    private void setPathGetAllFoodBuffet(String pathGetAllFoodBuffet) {
        this.pathGetAllFoodBuffet = pathGetAllFoodBuffet;
    }

    public String getPathGetAllFoodChay() {
        return pathGetAllFoodChay;
    }

    private void setPathGetAllFoodChay(String pathGetAllFoodChay) {
        this.pathGetAllFoodChay = pathGetAllFoodChay;
    }

    public String getPathGetAllFoodAV() {
        return pathGetAllFoodAV;
    }

    private void setPathGetAllFoodAV(String pathGetAllFoodAV) {
        this.pathGetAllFoodAV = pathGetAllFoodAV;
    }
}
