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
            CampaignVo existCampaign = null;
            if (projectFlatVo.getTags().size() > 0) {

                for (CampaignVo campaignVo: campaignVos) {
                    if (campaignVo.getCampaignId().equals(projectFlatVo.getTags().get(0))) {
                        existCampaign = campaignVo;
                        break;
                    }
                }

                if (existCampaign == null) {
                    existCampaign = new CampaignVo();
                    existCampaign.setExpiryDate(projectFlatVo.getExpiryDate());
                    existCampaign.setCampaignId(projectFlatVo.getTags().get(0));
                    existCampaign.setVoteRestriction(projectFlatVo.getVoteRestriction());
                    existCampaign.setProjectVos(new ArrayList<ProjectVo>());
                }
                existCampaign.getProjectVos().add(produceProjectVoFromFlat(projectFlatVo));
            }

            campaignVos.add(existCampaign);
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