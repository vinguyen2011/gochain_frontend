package com.gochain.gochainandroid.services;

import com.gochain.gochainandroid.vo.CampaignVo;
import com.gochain.gochainandroid.vo.CampaignVoContainer;
import com.gochain.gochainandroid.vo.ProjectFlatVo;
import com.gochain.gochainandroid.vo.ProjectVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinguyen on 07/02/2017.
 */

public class ProjectFlatToVoConverter {

    public ProjectFlatToVoConverter() {}

    public CampaignVoContainer convertProjectFlatToCampaignContainer(ProjectFlatVo[] projectFlatVos) {
        CampaignVoContainer campaignVoContainer = new CampaignVoContainer();
        for (ProjectFlatVo projectFlatVo: projectFlatVos) {
            for (String tag: projectFlatVo.getTags()) {
                CampaignVo existCampaign = null;

                for (CampaignVo campaignVo: campaignVoContainer.getCampaignVos()) {
                    if (campaignVo.getCampaignId().equals(tag)) {
                        existCampaign = campaignVo;
                        break;
                    }
                }

                if (existCampaign == null) {
                    existCampaign.setProjectVos(new ArrayList<ProjectVo>());
                }
                existCampaign.getProjectVos().add(produceProjectVoFromFlat(projectFlatVo));
            }
        }
        return campaignVoContainer;
    }

    public ProjectVo produceProjectVoFromFlat(ProjectFlatVo projectFlatVo) {
        return new ProjectVo(projectFlatVo.getProjectId(),
                projectFlatVo.getName(), projectFlatVo.getLocation(),
                projectFlatVo.getDescription(), projectFlatVo.getCost(),
                projectFlatVo.getCostCovered(), projectFlatVo.getPictureID());
    }
}