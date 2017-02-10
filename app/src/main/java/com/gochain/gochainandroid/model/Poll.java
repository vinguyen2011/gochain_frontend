package com.gochain.gochainandroid.model;

import java.util.List;

/**
 * Created by vinguyen on 07/02/2017.
 */

public class Poll {
    private String name;
    private List<PollDetails> pollDetails;

    public Poll() {
    }

    public Poll(String name, List<PollDetails> pollDetails) {
        this.name = name;
        this.pollDetails = pollDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PollDetails> getPollDetails() {
        return pollDetails;
    }

    public void setPollDetails(List<PollDetails> pollDetails) {
        this.pollDetails = pollDetails;
    }
}