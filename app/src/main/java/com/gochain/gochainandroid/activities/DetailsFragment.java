package com.gochain.gochainandroid.activities;

import android.app.Activity;
import android.os.AsyncTask;
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
import android.widget.Button;
import android.widget.TextView;

import com.gochain.gochainandroid.R;
import com.gochain.gochainandroid.adapter.PollDetailsAdapter;
import com.gochain.gochainandroid.model.PollDetails;
import com.gochain.gochainandroid.rest.GoChainRestService;

import java.util.List;
import com.gochain.gochainandroid.model.Poll;

public class DetailsFragment extends Fragment {
    private RecyclerView recyclerView;
    private Poll poll;
    private Boolean editable;
    private PollDetailsAdapter adapter;

    private SendVoteTask mVoteTask;

    private TextView title, daysRemained;
    private Button submitBtn;

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
        adapter = new PollDetailsAdapter(this, poll.getPollDetails(), editable, false);
        submitBtn = (Button) rootView.findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                FinishFragment fragment = new FinishFragment();

                fragment.setPoll(poll);
                fragment.setVotedPollDetails(poll.getPollDetails().subList(0, 1));
                fragment.setEditable(false);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_body, fragment).addToBackStack("details_fragment")
                        .commit();
            }
        });

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

    public void setEditable(Boolean editable){
        this.editable = editable;
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
