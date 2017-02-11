package com.gochain.gochainandroid.vo;

import java.util.List;

/**
 * Created by Brad on 11/02/2017.
 */
public class CampaignVo {
    private String campaignId;
    private List<ProjectVo> projectVos;

    public CampaignVo() {
        super();
    }

    public CampaignVo(String campaignId, List<ProjectVo> projectVos) {
        this.campaignId = campaignId;
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
}
