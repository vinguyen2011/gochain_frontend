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
import com.gochain.gochainandroid.adapter.PollDetailsAdapter;
import com.gochain.gochainandroid.model.PollDetails;
import com.gochain.gochainandroid.rest.GoChainRestService;

import java.util.List;

public class DetailsFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<PollDetails> itemList;
    private PollDetailsAdapter adapter;
    private SendVoteTask mVoteTask;

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
        adapter = new PollDetailsAdapter(this, itemList);

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

    public void setCustomObject(List<PollDetails> pollDetails){
        this.itemList = pollDetails;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class SendVoteTask extends AsyncTask<Void, Void, Boolean> {

        SendVoteTask() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            return new GoChainRestService().sendDummyVote();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mVoteTask = null;

            if (success) {
                Log.e("HomeFragment", "Vote sent");

            } else {
                Log.e("HomeFragment", "Error sending vote");
            }
        }

    }
}
