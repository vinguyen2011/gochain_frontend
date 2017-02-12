package com.gochain.gochainandroid.vo;

import java.math.BigDecimal;

/**
 * Created by Brad on 11/02/2017.
 */
public class ProjectVo {
    private String projectId;
    private String name;
    private String location;
    private String description;
    private int cost;
    private int costCovered;
    private int pictureId;

    public ProjectVo() {
        super();
    }

    public ProjectVo(String projectId, String name, String location,
                     String description, int cost, int costCovered,
                     int pictureId) {
        this.projectId = projectId;
        this.name = name;
        this.location = location;
        this.description = description;
        this.cost = cost;
        this.costCovered = costCovered;
        this.pictureId = pictureId;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }


    public String getLocation() {
        return location;
    }


    public String getDescription() {
        return description;
    }


    public int getCost() {
        return cost;
    }


    public int getCostCovered() {
        return costCovered;
    }


    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public void setCostCovered(int costCovered) {
        this.costCovered = costCovered;
    }

}
