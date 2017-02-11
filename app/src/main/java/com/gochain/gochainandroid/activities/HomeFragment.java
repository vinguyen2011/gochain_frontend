package com.gochain.gochainandroid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gochain.gochainandroid.R;
import com.gochain.gochainandroid.SessionValueHelper;
import com.gochain.gochainandroid.adapter.PollAdapter;
import com.gochain.gochainandroid.model.Poll;
import com.gochain.gochainandroid.model.PollDetails;
import com.gochain.gochainandroid.rest.GoChainRestService;
import com.gochain.gochainandroid.vo.AuthenticatedUserVo;
import com.gochain.gochainandroid.vo.CampaignVo;
import com.gochain.gochainandroid.vo.CampaignVoContainer;
import com.gochain.gochainandroid.vo.UserVo;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private PollAdapter adapter;
    private List<CampaignVo> itemList;

    private GoChainRestService goChainRestService;

    public HomeFragment() {
        goChainRestService = new GoChainRestService();
        goChainRestService.fetchCampaigns();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goChainRestService = new GoChainRestService();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        // fetch item from blockchain
        itemList = new ArrayList<CampaignVo>();//goChainRestService.fetchCampaigns();
        Log.i("Campaigns", itemList.toString());

        adapter = new PollAdapter(this, itemList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        // Inflate the layout for this fragment
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

}
