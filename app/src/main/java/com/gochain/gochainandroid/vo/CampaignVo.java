package com.gochain.gochainandroid.vo;

import java.util.List;

/**
 * Created by Brad on 11/02/2017.
 */
public class CampaignVo {
    private String campaignId;
    private String voteRestriction;
    private Long expiryDate;
    private List<ProjectVo> projectVos;

    public CampaignVo() {
        super();
    }

    public CampaignVo(String campaignId, String voteRestriction, Long expiryDate, List<ProjectVo> projectVos) {
        this.campaignId = campaignId;
        this.voteRestriction = voteRestriction;
        this.expiryDate = expiryDate;
        this.projectVos = projectVos;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public List<ProjectVo> getProjectVos() {
        return projectVos;
    }

    public void setProjectVos(List<ProjectVo> projectVos) {
        this.projectVos = projectVos;
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
}
