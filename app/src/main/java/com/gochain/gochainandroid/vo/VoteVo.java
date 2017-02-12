package com.gochain.gochainandroid.vo;

/**
 * Created by Brad on 10/02/2017.
 */

public class VoteVo {
    private String userId;
    private String projectId;
    private float votePercent;

    public VoteVo() {
        super();
    }

    public VoteVo(String userId, String projectId, float votePercent) {
        this.userId = userId;
        this.projectId = projectId;
        this.votePercent = votePercent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public float getVotePercent() {
        return votePercent;
    }

    public void setVotePercent(float votePercent) {
        this.votePercent = votePercent;
    }

    @Override
    public String toString() {
        return "VoteVo{" +
                "userId='" + userId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", votePercent=" + votePercent +
                '}';
    }
}
