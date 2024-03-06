package com.aviad.guidedtraining.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aviad.guidedtraining.R;
import com.aviad.guidedtraining.callbacks.CallBack_RecordClicked;
import com.aviad.guidedtraining.objects.TrainingRecord;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHolder> {
    private Activity activity;
    private Context context;
    private ArrayList<TrainingRecord> allRecords;
    private CallBack_RecordClicked callBack_RecordClicked;

    private Animation translate_anim;

    public RecordAdapter() { }

    public Activity getActivity() {
        return activity;
    }

    public RecordAdapter setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public Context getContext() {
        return context;
    }

    public RecordAdapter setContext(Context context) {
        this.context = context;
        return this;
    }

    public ArrayList<TrainingRecord> getAllRecords() {
        return allRecords;
    }

    public RecordAdapter setAllRecords(ArrayList<TrainingRecord> allRecords) {
        this.allRecords = allRecords;
        return this;
    }

    public CallBack_RecordClicked getCallBack_RecordClicked() {
        return callBack_RecordClicked;
    }

    public RecordAdapter setCallBack_RecordClicked(CallBack_RecordClicked callBack_RecordClicked) {
        this.callBack_RecordClicked = callBack_RecordClicked;
        return this;
    }

    public RecordAdapter(Activity activity, Context context, ArrayList<TrainingRecord> allRecords, CallBack_RecordClicked callBack_RecordClicked) {
        this.activity = activity;
        this.context = context;
        this.allRecords = allRecords;
        this.callBack_RecordClicked = callBack_RecordClicked;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_record,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String title = allRecords.get(position).getMode() + " " + allRecords.get(position).getDate();
        holder.record_details.setText(title);
        Log.d("pttt",allRecords.get(position).getMode().length() + "");
        if(allRecords.get(position).getMode().equalsIgnoreCase("Beginner"))
            holder.record_details.setIconResource(R.drawable.ic_starone);
        else if(allRecords.get(position).getMode().equalsIgnoreCase("Medium"))
            holder.record_details.setIconResource(R.drawable.ic_startwo);
        else if(allRecords.get(position).getMode().equalsIgnoreCase("Advanced"))
            holder.record_details.setIconResource(R.drawable.ic_starthree);
        else
            holder.record_details.setIconResource(R.drawable.ic_star);

        holder.record_details.setOnClickListener(e -> {
            if(callBack_RecordClicked != null)
                callBack_RecordClicked.recordClicked(title,allRecords.get(position).getLatitude(),allRecords.get(position).getLongitude());
        });
    }

    @Override
    public int getItemCount() {
        return allRecords.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout mainLayout;
        private MaterialButton record_details;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            record_details = itemView.findViewById(R.id.record_details);

            mainLayout = itemView.findViewById(R.id.mainLayout);
            // Animate Recyclerview
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
