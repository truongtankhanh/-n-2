package com.gmail.khanhit100896.foody.comment;

import com.gmail.khanhit100896.foody.user.User;

public class Comment {

    private int id;
    private User user;
    private String createTime;
    private String foodCode;
    private String comment;

    public Comment(int id, User user, String createTime, String foodCode, String comment) {
        this.setId(id);
        this.setUser(user);
        this.setCreateTime(createTime);
        this.setFoodCode(foodCode);
        this.setComment(comment);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFoodCode(String foodCode) {
        this.foodCode = foodCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFoodCode() {
        return foodCode;
    }
}
