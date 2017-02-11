package com.gochain.gochainandroid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gochain.gochainandroid.R;
import com.gochain.gochainandroid.adapter.PollDetailsAdapter;
import com.gochain.gochainandroid.model.Poll;
import com.gochain.gochainandroid.model.PollDetails;

import java.util.List;

public class DetailsFragment extends Fragment {
    private RecyclerView recyclerView;
    private Poll poll;
    private PollDetailsAdapter adapter;
    private TextView title, daysRemained;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        adapter = new PollDetailsAdapter(this, poll.getPollDetails());

        title = (TextView) rootView.findViewById(R.id.title);
        title.setText(poll.getName());

        daysRemained = (TextView) rootView.findViewById(R.id.daysRemained);
        daysRemained.setText(poll.getDaysRemained() + " days remained");

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

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

    public void setPoll(Poll poll){
        this.poll = poll;
    }

}
