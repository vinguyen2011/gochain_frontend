package com.gochain.gochainandroid.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gochain.gochainandroid.R;
import com.gochain.gochainandroid.adapter.PollDetailsAdapter;
import com.gochain.gochainandroid.rest.GoChainRestService;

import com.gochain.gochainandroid.services.DateConverter;
import com.gochain.gochainandroid.vo.CampaignVo;
import com.gochain.gochainandroid.vo.VoteVo;

public class DetailsFragment extends Fragment {
    private RecyclerView recyclerView;
    private CampaignVo poll;
    private Boolean editable;
    private PollDetailsAdapter adapter;
    private View mProgressView;
    private SendVoteTask mVoteTask;
    private View scrollView;
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
        scrollView = rootView.findViewById(R.id.scroll_view);
        mVoteTask = new SendVoteTask();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        adapter = new PollDetailsAdapter(this, poll.getProjectVos(), editable, false);

        submitBtn = (Button) rootView.findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showProgress(true);
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

        mProgressView = rootView.findViewById(R.id.details_progress_container);

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
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            setFadingAnimation(show, shortAnimTime);

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            scrollView.setVisibility(show ? View.GONE : View.VISIBLE);
            submitBtn.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private void setFadingAnimation(final boolean show, int shortAnimTime) {
        scrollView.setVisibility(show ? View.GONE : View.VISIBLE);
        scrollView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                scrollView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });
        submitBtn.setVisibility(show ? View.GONE : View.VISIBLE);
        submitBtn.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                submitBtn.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });
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
                if (voteVo.getVotePercent() > 0) {
                    success &= goChainRestService.sendVote(voteVo);
                }
            }
            return success;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mVoteTask = null;
            showProgress(false);
            if (success) {
                FinishFragment fragment = new FinishFragment();

                fragment.setPoll(poll);
                fragment.setEditable(false);
                fragment.setCampaignId(poll.getCampaignId());

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_body, fragment).addToBackStack("details_fragment")
                        .commit();

            } else {
                Log.e("HomeFragment", "Error sending vote");
            }
        }

        @Override
        protected void onCancelled() {
            mVoteTask = null;
            showProgress(false);
        }
    }
}
