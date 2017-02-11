package com.gochain.gochainandroid.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.BoolRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gochain.gochainandroid.R;
import com.gochain.gochainandroid.activities.DetailsFragment;
import com.gochain.gochainandroid.model.Poll;
import com.gochain.gochainandroid.model.PollDetails;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinguyen on 07/02/2017.
 */

public class PollDetailsAdapter extends RecyclerView.Adapter<PollDetailsAdapter.MyViewHolder> {
    private Context mContext;
    private List<PollDetails> itemList;
    private Fragment parent;
    private PollDetails item;
    private Boolean editable, voted;
    private List<DiscreteSeekBar> bars = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, status, cost, percentage;
        private DiscreteSeekBar bar;
        private ImageButton infoBtn;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            status = (TextView) view.findViewById(R.id.status);
            cost = (TextView) view.findViewById(R.id.cost);
            bar = (DiscreteSeekBar) view.findViewById(R.id.voteBar);
            percentage = (TextView) view.findViewById(R.id.percentage);
            infoBtn = (ImageButton) view.findViewById(R.id.infoBtn);
        }
    }


    public PollDetailsAdapter(Fragment parent, List<PollDetails> itemList, Boolean editable, Boolean voted) {
        this.parent = parent;
        this.mContext = parent.getContext();
        this.itemList = itemList;
        this.editable = editable;
        this.voted = voted;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.polldetails_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        item = itemList.get(position);

        holder.title.setText(item.getTitle());
        holder.status.setText(String.valueOf(item.getStatus()));
        holder.cost.setText(" / " + item.getCost() + " €");

        if (this.editable == false) {
            holder.bar.setVisibility(View.GONE);
        }

        if (this.voted == true) {
            holder.percentage.setText("Your preference: " + item.getPercentage() + "%");
        } else if (this.editable == false) {
            holder.percentage.setVisibility(View.GONE);
        }
        // if not editable, hide seekbar and show the selection
        if (this.editable == true) {
            holder.bar.setProgress(0);
            //add bar listener
            holder.bar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
                @Override
                public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                    holder.percentage.setText("Current selection: " + value + "%");
                    int sum = 0;
                    for (int i = 0; i < bars.size(); i++) {
                        sum = sum + bars.get(i).getProgress();
                    }
                    int extra = sum - 100;
                    if (extra > 0) {
                        // balance others
                        List<DiscreteSeekBar> otherBars = new ArrayList<DiscreteSeekBar>();
                        for (int i = 0; i < bars.size(); i++) {
                            if (position != i && bars.get(i).getProgress() > 0) {
                                otherBars.add(bars.get(i));
                            }
                        }

                        if (otherBars.size() > 0) {
                            int adjustment = extra / (otherBars.size());
                            for (int i = 0; i < otherBars.size(); i++) {
                                if (i < otherBars.size() - 1) {
                                    otherBars.get(i).setProgress(otherBars.get(i).getProgress() - adjustment);
                                } else {
                                    int remaining = extra - (adjustment * (otherBars.size() - 1));
                                    otherBars.get(i).setProgress(otherBars.get(i).getProgress() - remaining);
                                }
                            }
                        }
                    }
                }

                @Override
                public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

                }
            });

            bars.add(holder.bar);
        }

        // add button listener
        holder.infoBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                PollDetails itemA = itemList.get(position);

                // custom dialog
                final Dialog dialog = new Dialog(mContext);
                dialog.setContentView(R.layout.dialog_detail);
                TextView title = (TextView) dialog.findViewById(R.id.title);
                title.setText(itemA.getTitle());

                TextView description = (TextView) dialog.findViewById(R.id.description);
                description.setText(itemA.getDescription());

                TextView cost = (TextView) dialog.findViewById(R.id.cost);
                cost.setText("Budget needed: " + itemA.getCost() + " €");

                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                image.setImageResource(itemA.getPhotoId());

                Button dialogButton = (Button) dialog.findViewById(R.id.closeBtn);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_item, popup.getMenu());
        popup.show();
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }
}