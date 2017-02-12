package com.gochain.gochainandroid.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Brad on 10/02/2017.
 */

public class VoteVo {

    @SerializedName("_voterId")
    private String voterId;
    @SerializedName("_projectId")
    private String projectId;
    @SerializedName("_votePercent")
    private int votePercent;

    public VoteVo() {
        super();
    }

    public VoteVo(String voterId, String projectId, int votePercent) {
        this.voterId = voterId;
        this.projectId = projectId;
        this.votePercent = votePercent;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getVotePercent() {
        return votePercent;
    }

    public void setVotePercent(int votePercent) {
        this.votePercent = votePercent;
    }

    @Override
    public String toString() {
        return "VoteVo{" +
                "voterId='" + voterId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", votePercent=" + votePercent +
                '}';
    }
}
