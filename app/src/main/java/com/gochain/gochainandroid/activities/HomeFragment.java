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
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11};

        Poll a = new Poll("Car 1", photos[0]);
        itemList.add(a);

        Poll b = new Poll("Car 2", photos[1]);
        itemList.add(b);

        Poll c = new Poll("Car 3", photos[2]);
        itemList.add(c);

        Poll d = new Poll("Car 4", photos[3]);
        itemList.add(d);

        Poll e = new Poll("Car 5", photos[4]);
        itemList.add(e);

        Poll f = new Poll("Car 6", photos[5]);
        itemList.add(f);
        adapter.notifyDataSetChanged();
    }
}
