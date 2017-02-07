package com.gochain.gochainandroid.model;

/**
 * Created by vinguyen on 07/02/2017.
 */

public class Item {
    private String name;
    private int photoId;

    public Item() {
    }

    public Item(String name, int photoId) {
        this.name = name;
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}