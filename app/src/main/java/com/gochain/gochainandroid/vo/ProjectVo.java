package com.gochain.gochainandroid.vo;

import java.math.BigDecimal;

/**
 * Created by Brad on 11/02/2017.
 */
public class ProjectVo {
    private String projectId;
    private String projectName;
    private int estimatedCost;
    private int voteValue;

    public ProjectVo() {
        super();
    }

    public ProjectVo(String projectId, String projectName, int estimatedCost, int voteValue) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.estimatedCost = estimatedCost;
        this.voteValue = voteValue;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(int estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public int getVoteValue() {
        return voteValue;
    }

    public void setVoteValue(int voteValue) {
        this.voteValue = voteValue;
    }
}
