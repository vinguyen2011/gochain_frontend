package com.gochain.gochainandroid.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;

/**
 * Created by vinguyen on 07/02/2017.
 */

public class PollDetailsAdapter extends RecyclerView.Adapter<PollDetailsAdapter.MyViewHolder> {
    private Context mContext;
    private List<PollDetails> itemList;
    private Fragment parent;
    private SeekBar bar;
    private TextView percentage;
    private ImageButton infoBtn;
    private PollDetails item;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, status;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            status = (TextView) view.findViewById(R.id.status);
            bar = (SeekBar) view.findViewById(R.id.voteBar);
            percentage = (TextView) view.findViewById(R.id.percentage);
            infoBtn = (ImageButton) view.findViewById(R.id.infoBtn);



        }
    }


    public PollDetailsAdapter(Fragment parent, List<PollDetails> itemList) {
        this.parent = parent;
        this.mContext = parent.getContext();
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.polldetails_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        item = itemList.get(position);
        holder.title.setText(item.getTitle());
        holder.status.setText(item.getStatus() + " / " + item.getCost() + "$");
        holder.image.setImageResource(itemList.get(position).getPhotoId());
        holder.status.setText(item.getStatus() + " / " + item.getCost() + "$");

        bar.setMax(100);
        bar.setProgress(0);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                p.addRule(RelativeLayout.ABOVE, seekBar.getId());
                Rect thumbRect = bar.getThumb().getBounds();
                p.setMargins(
                        thumbRect.centerX(),0, 0, 0);
                percentage.setLayoutParams(p);
                percentage.setText(String.valueOf(progress) + " %");
            }
        });

        // add button listener
        infoBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(mContext);
                dialog.setContentView(R.layout.dialog_detail);
                TextView title = (TextView) dialog.findViewById(R.id.title);
                title.setText(item.getTitle());
                TextView description = (TextView) dialog.findViewById(R.id.description);
                title.setText(item.getDescription());
                TextView cost = (TextView) dialog.findViewById(R.id.cost);
                title.setText(item.getCost() + "$");

                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                image.setImageResource(item.getPhotoId());

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