package com.gochain.gochainandroid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gochain.gochainandroid.R;
import com.gochain.gochainandroid.adapter.ItemsAdapter;
import com.gochain.gochainandroid.model.Item;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private ItemsAdapter adapter;
    private List<Item> itemList;
    private FloatingActionButton fab;

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
        adapter = new ItemsAdapter(this.getContext(), itemList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareItems();

        // click on fab button open the scan screen
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ScanFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_body, fragment)
                        .commit();
            }
        });

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

                R.drawable.publicparksmall,
                R.drawable.swimmingpoolsmall,
                R.drawable.youthcentersmall,
                R.drawable.amuseparksmall,
                R.drawable.footballfieldsmall};
        String[] name = new String[]{
                "Park",
                "Swimming pool",
                "Youth center",
                "Amusement park",
                "Football field"};

        double[] status = new double[] {
                112.0,
                13,
                23,
                0,
                0
        };
        PollDetails detail;
        List<PollDetails> details = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            detail = new PollDetails(name[i],
                    "Description Description Description Description Description Description Description",
                    123.4, photos[i], status[i], 23.6);
            details.add(detail);
        }

        Poll a = new Poll("Social Projects Q1 2017", photos[0], details, 30);
        itemList.add(a);

        Poll b = new Poll("Education projects Q1 2017", photos[1], details, 90);
        itemList.add(b);
        itemList.add(b);
        itemList.add(b);
        itemList.add(b);
        itemList.add(b);
        itemList.add(b);

        itemList.add(b);

        Item c = new Item("Car 3", photos[2]);
        itemList.add(c);

        Item d = new Item("Car 4", photos[3]);
        itemList.add(d);

        Item e = new Item("Car 5", photos[4]);
        itemList.add(e);

        Item f = new Item("Car 6", photos[5]);
        itemList.add(f);
        adapter.notifyDataSetChanged();
    }
}
