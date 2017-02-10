package com.gochain.gochainandroid.model;

/**
 * Created by vinguyen on 07/02/2017.
 */

public class PollDetails {
    private String title;
    private String description;
    private double cost;
    private double status;
    private int photoId;
    private double percentage;
    public PollDetails() {
    }

    public PollDetails(String title, String description, double cost, int photoId, double status, double percentage) {
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.photoId = photoId;
        this.percentage = percentage;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }
}