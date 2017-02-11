package com.gochain.gochainandroid.vo;

import java.util.List;

/**
 * Created by Brad on 11/02/2017.
 */

public class CampaignVoContainer {
    private List<CampaignVo> campaignVos;

    public CampaignVoContainer() {
        super();
    }

    public CampaignVoContainer(List<CampaignVo> campaignVos) {
        this.campaignVos = campaignVos;
    }

    public List<CampaignVo> getCampaignVos() {
        return campaignVos;
    }

    public void setCampaignVos(List<CampaignVo> campaignVos) {
        this.campaignVos = campaignVos;
    }
}
