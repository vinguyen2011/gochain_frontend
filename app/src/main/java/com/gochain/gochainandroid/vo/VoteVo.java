package com.gochain.gochainandroid.vo;

/**
 * Created by Brad on 10/02/2017.
 */

public class VoteVo {
    private String userId;
    private CampaignVo campaignVo;

    public VoteVo() {
        super();
    }

    public VoteVo(String userId, CampaignVo campaignVo) {
        this.userId = userId;
        this.campaignVo = campaignVo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CampaignVo getCampaignVo() {
        return campaignVo;
    }

    public void setCampaignVo(CampaignVo campaignVo) {
        this.campaignVo = campaignVo;
    }
}
