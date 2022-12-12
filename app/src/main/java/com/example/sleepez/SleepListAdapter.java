package com.example.sleepez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class SleepListAdapter extends
        RecyclerView.Adapter<SleepListAdapter.SleepViewHolder> {
    private final ArrayList<SleepData> mSleepList;
    private final LayoutInflater mInflater;

    public SleepListAdapter(Context context, ArrayList<SleepData> mSleepList) {
        this.mSleepList = mSleepList;
        mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public SleepListAdapter.SleepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.sleep_list_item, parent, false);
        return new SleepViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull SleepListAdapter.SleepViewHolder holder, int position) {
        SleepData sleep = mSleepList.get(position);
        holder.sleepDateView.setText(sleep.getDate());
        holder.bedTimeView.setText(sleep.getBedTime().toString());
        holder.wakeTimeView.setText(sleep.getWakeTime().toString());
        holder.dreamNoteView.setText(sleep.getDreamNotes());
        holder.sleepRatingView.setText(String.valueOf(sleep.getSleepQuality()));
    }

    @Override
    public int getItemCount() {
        return mSleepList.size();
    }

    class SleepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView sleepDateView;
        public final TextView bedTimeView;
        public final TextView wakeTimeView;
        public final TextView dreamNoteView;
        public final TextView sleepRatingView;
        final SleepListAdapter mAdapter;

        public SleepViewHolder(@NonNull View itemView, SleepListAdapter adapter) {
            super(itemView);
            sleepDateView = itemView.findViewById(R.id.sleep_date_displayed);
            bedTimeView = itemView.findViewById(R.id.bed_time_displayed);
            wakeTimeView = itemView.findViewById(R.id.wake_time_displayed);
            dreamNoteView = itemView.findViewById(R.id.dream_notes_displayed);
            sleepRatingView = itemView.findViewById(R.id.sleep_rating_displayed);
            this.mAdapter = adapter;

            sleepDateView.setOnClickListener(this);
            bedTimeView.setOnClickListener(this);
            wakeTimeView.setOnClickListener(this);
            dreamNoteView.setOnClickListener(this);
            sleepRatingView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
            // Use that to access the affected item in mWordList.
            String sleep_date = mSleepList.get(mPosition).getDate();
            Snackbar.make(dreamNoteView,
                    sleep_date + " clicked!",
                    Snackbar.LENGTH_SHORT).show();

        }
    }
}