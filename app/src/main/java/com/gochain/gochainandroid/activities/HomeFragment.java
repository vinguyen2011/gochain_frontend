package com.gochain.gochainandroid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gochain.gochainandroid.R;
import com.gochain.gochainandroid.adapter.PollAdapter;
import com.gochain.gochainandroid.model.Poll;
import com.gochain.gochainandroid.model.PollDetails;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private PollAdapter adapter;
    private List<Poll> itemList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        itemList = new ArrayList<>();
        adapter = new PollAdapter(this, itemList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareItems();

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

    private void prepareItems() {
        int[] photos = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3};
        String[] name = new String[]{
                "Park",
                "Swimming pool",
                "Gym"};

        List<PollDetails> details = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            PollDetails detail = new PollDetails(name[i],
                    "Description Description Description Description Description Description Description",
                    123.4, photos[i], 123.4, 23.6);
            details.add(detail);
        }

        Poll a = new Poll(name[0], details);
        itemList.add(a);

        itemList.add(a);

        adapter.notifyDataSetChanged();
    }
}
