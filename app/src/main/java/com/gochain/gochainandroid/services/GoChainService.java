package com.gochain.gochainandroid.services;

import com.gochain.gochainandroid.rest.GoChainRestService;
import com.gochain.gochainandroid.vo.CampaignVo;
import com.gochain.gochainandroid.vo.ProjectFlatVo;

import java.util.List;

/**
 * Created by Brad on 11/02/2017.
 */

public class GoChainService {

    private GoChainRestService goChainRestService;
    private ProjectFlatToVoConverter projectFlatToVoConverter;

    public GoChainService() {
        goChainRestService = new GoChainRestService();
        projectFlatToVoConverter = new ProjectFlatToVoConverter();
    }

    public List<CampaignVo> getCampaigns() {
        ProjectFlatVo[] projectFlatVos = goChainRestService.fetchCampaigns();
        if (projectFlatVos != null) {
            return projectFlatToVoConverter.convertProjectFlatToCampaignContainer(projectFlatVos);
        }
        return null;
    }
}
