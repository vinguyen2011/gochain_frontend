package com.gochain.gochainandroid.rest;

import android.util.Log;

import com.gochain.gochainandroid.vo.VoteVo;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Brad on 10/02/2017.
 */

public class VoteRestService extends AbstractRestService {
    private static final String VOTE_URL = BASE_URL + "/vote";

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
}
