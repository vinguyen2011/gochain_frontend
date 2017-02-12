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
import com.gochain.gochainandroid.services.DateConverter;
import com.gochain.gochainandroid.vo.CampaignVo;
import com.gochain.gochainandroid.vo.VoteVo;

public class DetailsFragment extends Fragment {
    private RecyclerView recyclerView;
    private CampaignVo poll;
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
        mVoteTask = new SendVoteTask();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        adapter = new PollDetailsAdapter(this, poll.getProjectVos(), editable, false);

        submitBtn = (Button) rootView.findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
            mVoteTask.execute();
            }
        });

        title = (TextView) rootView.findViewById(R.id.title);
        title.setText(poll.getCampaignId());

        daysRemained = (TextView) rootView.findViewById(R.id.daysRemained);
        daysRemained.setText(new DateConverter().getDaysTillExpireDate(poll.getExpiryDate()) + " days remained");

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

    public void setPoll(CampaignVo poll){
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
        GoChainRestService goChainRestService;

        SendVoteTask() {
            goChainRestService = new GoChainRestService();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean success = true;
            for (VoteVo voteVo: adapter.getVotes()) {
                success &= goChainRestService.sendVote(voteVo);
            }
            return success;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if (success) {
                FinishFragment fragment = new FinishFragment();

                fragment.setPoll(poll);
                fragment.setEditable(false);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_body, fragment).addToBackStack("details_fragment")
                        .commit();

            } else {
                Log.e("HomeFragment", "Error sending vote");
            }
        }

    }
}
