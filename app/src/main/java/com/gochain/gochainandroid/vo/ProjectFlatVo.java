package com.gochain.gochainandroid.vo;

import java.util.List;

/**
 * Created by Brad on 11/02/2017.
 */
public class ProjectFlatVo {
    private String projectId;
    private String name;
    private String location;
    private String description;
    private List<String> tags;
    private int cost;
    private int costCovered;
    private String campaignId;
    private String voteRestriction;
    private Long expiryDate;

    public ProjectFlatVo() {
        super();
    }

    public ProjectFlatVo(String projectId, String name, String location, String description, int cost, int costCovered,
                         String campaignId, String voteRestriction, Long expiryDate, List<String> tags) {
        this.projectId = projectId;
        this.name = name;
        this.location = location;
        this.description = description;
        this.cost = cost;
        this.costCovered = costCovered;
        this.campaignId = campaignId;
        this.voteRestriction = voteRestriction;
        this.expiryDate = expiryDate;
        this.tags = tags;
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

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getVoteRestriction() {
        return voteRestriction;
    }

    public void setVoteRestriction(String voteRestriction) {
        this.voteRestriction = voteRestriction;
    }

    public Long getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Long expiryDate) {
        this.expiryDate = expiryDate;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "ProjectFlatVo{" +
                "projectId='" + projectId + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                ", cost=" + cost +
                ", costCovered=" + costCovered +
                ", campaignId='" + campaignId + '\'' +
                ", voteRestriction='" + voteRestriction + '\'' +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
