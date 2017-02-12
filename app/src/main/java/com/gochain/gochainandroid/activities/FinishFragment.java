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
import android.widget.TextView;

import com.gochain.gochainandroid.R;
import com.gochain.gochainandroid.adapter.PollDetailsAdapter;
import com.gochain.gochainandroid.services.DateConverter;
import com.gochain.gochainandroid.services.GoChainService;
import com.gochain.gochainandroid.vo.CampaignVo;
import com.gochain.gochainandroid.vo.ProjectVo;

import java.util.ArrayList;
import java.util.List;

public class FinishFragment extends Fragment {
    private RecyclerView recyclerViewPolls;
    private CampaignVo poll;
    private String campaignId;
    private Boolean editable;
    private PollDetailsAdapter adapter;
    private TextView title, daysRemained;
    RestTask mRestTask;

    private static String TAG = "FinishFragment";

    public FinishFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_finish, container, false);

        // all polls
        recyclerViewPolls = (RecyclerView) rootView.findViewById(R.id.recycler_view_polls);

        adapter = new PollDetailsAdapter(this, new ArrayList<ProjectVo>(), editable, false);

        title = (TextView) rootView.findViewById(R.id.title);
        title.setText(campaignId);

        daysRemained = (TextView) rootView.findViewById(R.id.daysRemained);
        daysRemained.setText(new DateConverter().getDaysTillExpireDate(poll.getExpiryDate()) + " days remained");

        RecyclerView.LayoutManager votedLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerViewPolls.setLayoutManager(votedLayoutManager);
        recyclerViewPolls.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPolls.setAdapter(adapter);

        mRestTask = new RestTask();
        mRestTask.execute();

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

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * Represents an asynchronous rest service calling task used to authenticate
     * the user.
     */
    public class RestTask extends AsyncTask<Void, Void, Boolean> {
        private GoChainService goChainService;

        RestTask() {
            goChainService = new GoChainService();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            List<CampaignVo> itemList = goChainService.getCampaigns();
            for (CampaignVo campaignVo: itemList) {
                if (campaignId.equals(campaignVo.getCampaignId())) {
                    poll = campaignVo;
                    break;
                }
            }
            Log.i("Campaign selected: ", poll.toString());
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                adapter.clear();
                adapter.add(poll.getProjectVos());
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        protected void onCancelled() {
            mRestTask = null;
        }
    }
}
