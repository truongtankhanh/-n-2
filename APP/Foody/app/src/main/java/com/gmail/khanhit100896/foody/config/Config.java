package com.gmail.khanhit100896.foody.config;

import com.google.firebase.auth.FirebaseAuth;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Config {
    private static final Config config = new Config();

    // [START declare_auth]
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
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
    private String pathGetAllComment;
    private String pathInsertComment;
    private String pathUpdateBranch;
    private String pathUpdateTouchBranch;
    private String pathUpdateFood;
    private String pathUpdateTouchFood;
    private String pathUpdateRestaurant;
    private String pathUpdateTouchRestaurant;
    private String pathDeleteHistoryRestaurant;
    private String pathDeleteHistoryBranch;
    private String pathDeleteHistoryFood;
    private String pathDeleteLikeRestaurant;
    private String pathDeleteLikeBranch;
    private String pathDeleteLikeFood;

    private void setIp() {
        this.ip = "http://192.168.1.6";
    }

    private Config() {
        // [START initialize_auth]
        this.setmAuth(FirebaseAuth.getInstance());
        this.setAuthStateListener(this.authStateListener);
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
        this.setPathGetAllComment(this.getIp().concat("/foody-admin/src/libs/getAllComment.php"));
        this.setPathInsertComment(this.getIp().concat("/foody-admin/src/libs/addComment.php"));
        this.setPathUpdateBranch(this.getIp().concat("/foody-admin/src/libs/updateBranch.php"));
        this.setPathUpdateFood(this.getIp().concat("/foody-admin/src/libs/updateFood.php"));
        this.setPathUpdateRestaurant(this.getIp().concat("/foody-admin/src/libs/updateRestaurant.php"));
        this.setPathUpdateTouchRestaurant(this.getIp().concat("/foody-admin/src/libs/updateTouchRestaurant.php"));
        this.setPathUpdateTouchFood(this.getIp().concat("/foody-admin/src/libs/updateTouchFood.php"));
        this.setPathUpdateTouchBranch(this.getIp().concat("/foody-admin/src/libs/updateTouchBranch.php"));
        this.setPathDeleteHistoryRestaurant(this.getIp().concat("/foody-admin/src/libs/deleteHistoryRestaurant.php"));
        this.setPathDeleteHistoryBranch(this.getIp().concat("/foody-admin/src/libs/deleteHistoryBranch.php"));
        this.setPathDeleteHistoryFood(this.getIp().concat("/foody-admin/src/libs/deleteHistoryFood.php"));
        this.setPathDeleteLikeRestaurant(this.getIp().concat("/foody-admin/src/libs/deleteLikeRestaurant.php"));
        this.setPathDeleteLikeBranch(this.getIp().concat("/foody-admin/src/libs/deleteLikeBranch.php"));
        this.setPathDeleteLikeFood(this.getIp().concat("/foody-admin/src/libs/deleteLikeFood.php"));
    }

    public void signOut() {
        // Firebase sign out
        this.getmAuth().signOut();
        // Google sign out
    }

    public String getCurrentTime(){
        return new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
    }

    public String getCurrentDate(){
        //return String.valueOf(new Date(System.currentTimeMillis()));
        String currentDate;
        int hours = Calendar.getInstance().getTime().getHours();
        SimpleDateFormat formatter;
        Date date = new Date();
        if(hours > 12){
            formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
            currentDate = formatter.format(date) + " PM";
        }
        else {
            formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
            currentDate = formatter.format(date) + " AM";
        }
        return currentDate;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public FirebaseAuth.AuthStateListener getAuthStateListener() {
        return authStateListener;
    }

    public void setAuthStateListener(FirebaseAuth.AuthStateListener authStateListener) {
        this.authStateListener = authStateListener;
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

    public String getPathGetAllComment() {
        return pathGetAllComment;
    }

    public void setPathGetAllComment(String pathGetAllComment) {
        this.pathGetAllComment = pathGetAllComment;
    }

    public String getPathInsertComment() {
        return pathInsertComment;
    }

    public void setPathInsertComment(String pathInsertComment) {
        this.pathInsertComment = pathInsertComment;
    }

    public String getPathUpdateBranch() {
        return pathUpdateBranch;
    }

    public void setPathUpdateBranch(String pathUpdateBranch) {
        this.pathUpdateBranch = pathUpdateBranch;
    }

    public String getPathUpdateFood() {
        return pathUpdateFood;
    }

    public void setPathUpdateFood(String pathUpdateFood) {
        this.pathUpdateFood = pathUpdateFood;
    }

    public String getPathUpdateRestaurant() {
        return pathUpdateRestaurant;
    }

    public void setPathUpdateRestaurant(String pathUpdateRestaurant) {
        this.pathUpdateRestaurant = pathUpdateRestaurant;
    }

    public String getPathUpdateTouchRestaurant() {
        return pathUpdateTouchRestaurant;
    }

    public void setPathUpdateTouchRestaurant(String pathUpdateTouchRestaurant) {
        this.pathUpdateTouchRestaurant = pathUpdateTouchRestaurant;
    }

    public String getPathUpdateTouchFood() {
        return pathUpdateTouchFood;
    }

    public void setPathUpdateTouchFood(String pathUpdateTouchFood) {
        this.pathUpdateTouchFood = pathUpdateTouchFood;
    }

    public String getPathUpdateTouchBranch() {
        return pathUpdateTouchBranch;
    }

    public void setPathUpdateTouchBranch(String pathUpdateTouchBranch) {
        this.pathUpdateTouchBranch = pathUpdateTouchBranch;
    }

    public String getPathDeleteHistoryRestaurant() {
        return pathDeleteHistoryRestaurant;
    }

    public void setPathDeleteHistoryRestaurant(String pathDeleteHistoryRestaurant) {
        this.pathDeleteHistoryRestaurant = pathDeleteHistoryRestaurant;
    }

    public String getPathDeleteHistoryBranch() {
        return pathDeleteHistoryBranch;
    }

    public void setPathDeleteHistoryBranch(String pathDeleteHistoryBranch) {
        this.pathDeleteHistoryBranch = pathDeleteHistoryBranch;
    }

    public String getPathDeleteHistoryFood() {
        return pathDeleteHistoryFood;
    }

    public void setPathDeleteHistoryFood(String pathDeleteHistoryFood) {
        this.pathDeleteHistoryFood = pathDeleteHistoryFood;
    }

    public String getPathDeleteLikeRestaurant() {
        return pathDeleteLikeRestaurant;
    }

    public void setPathDeleteLikeRestaurant(String pathDeleteLikeRestaurant) {
        this.pathDeleteLikeRestaurant = pathDeleteLikeRestaurant;
    }

    public String getPathDeleteLikeBranch() {
        return pathDeleteLikeBranch;
    }

    public void setPathDeleteLikeBranch(String pathDeleteLikeBranch) {
        this.pathDeleteLikeBranch = pathDeleteLikeBranch;
    }

    public String getPathDeleteLikeFood() {
        return pathDeleteLikeFood;
    }

    public void setPathDeleteLikeFood(String pathDeleteLikeFood) {
        this.pathDeleteLikeFood = pathDeleteLikeFood;
    }
}
