package com.gochain.gochainandroid.model;

/**
 * Created by vinguyen on 07/02/2017.
 */

public class PollDetails {
    private String title;
    private String description;
    private float cost;
    private int photoId;
    private float percentage;
    public PollDetails() {
    }

    public PollDetails(String title, String description, float cost, int photoId, float percentage) {
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.photoId = photoId;
        this.percentage = percentage;
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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}