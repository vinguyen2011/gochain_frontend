package com.gochain.gochainandroid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gochain.gochainandroid.R;
import com.gochain.gochainandroid.adapter.PollDetailsAdapter;
import com.gochain.gochainandroid.model.Poll;
import com.gochain.gochainandroid.model.PollDetails;
import com.gochain.gochainandroid.services.DateConverter;
import com.gochain.gochainandroid.vo.CampaignVo;
import com.gochain.gochainandroid.vo.ProjectFlatVo;
import com.gochain.gochainandroid.vo.ProjectVo;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.util.ArrayList;
import java.util.List;

public class FinishFragment extends Fragment {
    private RecyclerView recyclerViewVotedPolls, recyclerViewPolls;
    private List<ProjectVo> votedPollDetails = new ArrayList<>();
    private CampaignVo poll;
    private Boolean editable;
    private PollDetailsAdapter adapterVotedPolls, adapterPolls;
    private TextView title, daysRemained;

    public FinishFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_finish, container, false);

        // all polls
        recyclerViewPolls = (RecyclerView) rootView.findViewById(R.id.recycler_view_polls);

        adapterPolls = new PollDetailsAdapter(this, poll.getProjectVos(), editable, false);

        title = (TextView) rootView.findViewById(R.id.title);
        title.setText(poll.getCampaignId());

        daysRemained = (TextView) rootView.findViewById(R.id.daysRemained);
        daysRemained.setText(new DateConverter().getDaysTillExpireDate(poll.getExpiryDate()) + " days remained");

        RecyclerView.LayoutManager votedLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerViewPolls.setLayoutManager(votedLayoutManager);
        recyclerViewPolls.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPolls.setAdapter(adapterPolls);

        // voted polls
        recyclerViewVotedPolls = (RecyclerView) rootView.findViewById(R.id.recycler_view_voted_polls);
        adapterVotedPolls = new PollDetailsAdapter(this, votedPollDetails, editable, true);

        RecyclerView.LayoutManager allLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerViewVotedPolls.setLayoutManager(allLayoutManager);
        recyclerViewVotedPolls.setItemAnimator(new DefaultItemAnimator());
        recyclerViewVotedPolls.setAdapter(adapterVotedPolls);

        if (this.editable == false) {
            DiscreteSeekBar bar = (DiscreteSeekBar) recyclerViewVotedPolls.findViewById(R.id.voteBar);
            recyclerViewVotedPolls.removeView(bar);
        }
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setVotedPollDetails(List<ProjectVo> votedPollDetails){
        this.votedPollDetails = votedPollDetails;
    }

    public void setPoll(CampaignVo poll){
        this.poll = poll;
    }

    public void setEditable(Boolean editable){
        this.editable = editable;
    }
}
