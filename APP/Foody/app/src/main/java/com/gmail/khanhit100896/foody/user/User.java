package com.gmail.khanhit100896.foody.user;

public class User {

    private String userName;
    private String userEmail;
    private String photoUri;

    public User(String userName, String userEmail, String photoUri) {
        this.setUserName(userName);
        this.setUserEmail(userEmail);
        this.setPhotoUri(photoUri);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }
}
