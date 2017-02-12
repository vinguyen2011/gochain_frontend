package com.gochain.gochainandroid.activities;

import android.app.Activity;
import android.os.AsyncTask;
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
import com.gochain.gochainandroid.adapter.PollAdapter;
import com.gochain.gochainandroid.services.GoChainService;
import com.gochain.gochainandroid.vo.CampaignVo;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private PollAdapter adapter;
    private List<CampaignVo> itemList;
    View rootView;

    private RestTask mRestTask = null;

    private static String TAG = "HomeFragment";

    public HomeFragment() {
        mRestTask = new RestTask();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        itemList = new ArrayList<>();
        displayItems();

        if (mRestTask == null) {
            mRestTask = new RestTask();
        }
        mRestTask.execute();

        // Inflate the layout for this fragment
        return rootView;
    }

    private void displayItems() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        adapter = new PollAdapter(this, itemList);
        Log.i(TAG, "itemList size: " + itemList.size());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class RestTask extends AsyncTask<Void, Void, Boolean> {
        private GoChainService goChainService;

        RestTask() {
            goChainService = new GoChainService();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            itemList = goChainService.getCampaigns();
            Log.i("Campaigns", itemList.toString());
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                Log.i(TAG, "Refreshing adapter with size: " + itemList.size());
                adapter.clear();
                adapter.add(itemList);
                adapter.notifyDataSetChanged();
            }
            mRestTask = null;
        }

        @Override
        protected void onCancelled() {
            mRestTask = null;
        }
    }
}
