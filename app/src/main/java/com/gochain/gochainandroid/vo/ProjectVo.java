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

    public ProjectVo() {
        super();
    }

    public ProjectVo(String projectId, String name, String location, String description, int cost, int costCovered) {
        this.projectId = projectId;
        this.name = name;
        this.location = location;
        this.description = description;
        this.cost = cost;
        this.costCovered = costCovered;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCostCovered() {
        return costCovered;
    }

    public void setCostCovered(int costCovered) {
        this.costCovered = costCovered;
    }
}
