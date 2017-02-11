package com.gochain.gochainandroid.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.SeekBar;

/**
 * Created by vinguyen on 07/02/2017.
 */

public class PollDetails implements Parcelable {
    private String title;
    private String description;
    private double cost;
    private double status;
    private int photoId;
    private double percentage;
    private SeekBar bar;
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

    protected PollDetails(Parcel in) {
        title = in.readString();
        description = in.readString();
        cost = in.readDouble();
        status = in.readDouble();
        photoId = in.readInt();
        percentage = in.readDouble();
    }

    public static final Creator<PollDetails> CREATOR = new Creator<PollDetails>() {
        @Override
        public PollDetails createFromParcel(Parcel in) {
            return new PollDetails(in);
        }

        @Override
        public PollDetails[] newArray(int size) {
            return new PollDetails[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeDouble(cost);
        parcel.writeDouble(status);
        parcel.writeInt(photoId);
        parcel.writeDouble(percentage);
    }

    public SeekBar getBar() {
        return bar;
    }

    public void setBar(SeekBar bar) {
        this.bar = bar;
    }
}