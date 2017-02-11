package com.gochain.gochainandroid.services;

import com.gochain.gochainandroid.rest.GoChainRestService;
import com.gochain.gochainandroid.vo.CampaignVo;

import java.util.List;

/**
 * Created by Brad on 11/02/2017.
 */

public class GoChainService {

    private GoChainRestService goChainRestService;

    public GoChainService() {
        goChainRestService = new GoChainRestService();
    }

    public List<CampaignVo> getCampaigns() {
        goChainRestService.fetchCampaigns();
        return null;
    }
}
