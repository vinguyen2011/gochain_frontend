package com.gochain.gochainandroid.rest;

import android.util.Log;

import com.gochain.gochainandroid.vo.CampaignVo;
import com.gochain.gochainandroid.vo.CampaignVoContainer;
import com.gochain.gochainandroid.vo.ProjectVo;
import com.gochain.gochainandroid.vo.VoteVo;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brad on 10/02/2017.
 */

public class GoChainRestService extends AbstractRestService {
    private static final String VOTE_URL = BASE_URL + "/vote";
    private static final String FETCH_CAMPAIGNS_URL = BASE_URL + "/fetch_campaigns";

    public List<CampaignVo> fetchCampaigns() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        try {
            CampaignVoContainer campaignVos = restTemplate.getForObject(VOTE_URL, CampaignVoContainer.class);
            return campaignVos.getCampaignVos();
        } catch (Throwable t) {
            Log.e(this.getClass().getName(), "Error trying to authenticate user with server", t);
        }
        return null;
    }

    public Boolean sendVote(VoteVo voteVo) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        try {
            Boolean success = restTemplate.postForObject(VOTE_URL, voteVo, Boolean.class);
            return success;
        } catch (Throwable t) {
            Log.e(this.getClass().getName(), "Error trying to authenticate user with server", t);
        }
        return false;
    }

    public Boolean sendDummyVote() {
        ProjectVo projectVo1 = new ProjectVo("1", "testProjectName1", 1000, 70);
        ProjectVo projectVo2 = new ProjectVo("2", "testProjectName2", 1000, 20);
        ProjectVo projectVo3 = new ProjectVo("3", "testProjectName3", 1000, 10);
        List<ProjectVo> projectVos = new ArrayList<>(3);
        projectVos.add(projectVo1);
        projectVos.add(projectVo2);
        projectVos.add(projectVo3);

        CampaignVo campaignVo = new CampaignVo("campaign1", projectVos);
        VoteVo voteVo = new VoteVo("bradDigiD", campaignVo);
        return this.sendVote(voteVo);
    }
}
