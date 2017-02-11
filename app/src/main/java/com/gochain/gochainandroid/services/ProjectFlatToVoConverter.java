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

    public List<CampaignVo> convertProjectFlatToCampaignContainer(ProjectFlatVo[] projectFlatVos) {
        List<CampaignVo> campaignVos = new ArrayList<>();
        for (ProjectFlatVo projectFlatVo: projectFlatVos) {
            for (String tag: projectFlatVo.getTags()) {
                CampaignVo existCampaign = null;

                for (CampaignVo campaignVo: campaignVos) {
                    if (campaignVo.getCampaignId().equals(tag)) {
                        existCampaign = campaignVo;
                        break;
                    }
                }

                if (existCampaign == null) {
                    existCampaign = new CampaignVo();
                    existCampaign.setProjectVos(new ArrayList<ProjectVo>());
                }
                existCampaign.getProjectVos().add(produceProjectVoFromFlat(projectFlatVo));
            }
        }
        return campaignVos;
    }

    public ProjectVo produceProjectVoFromFlat(ProjectFlatVo projectFlatVo) {
        return new ProjectVo(projectFlatVo.getProjectId(),
                projectFlatVo.getName(), projectFlatVo.getLocation(),
                projectFlatVo.getDescription(), projectFlatVo.getCost(),
                projectFlatVo.getCostCovered(), projectFlatVo.getPictureID());
    }
}