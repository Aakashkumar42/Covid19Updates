package com.example.coronavirus_updates.Acivities;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.coronavirus_updates.R;

import java.util.List;

public class helpLineAdapter  extends RecyclerView.Adapter<helpLineAdapter.helplineViewHolder> {

    private Context mCtx;

    private List<helplinelist> helplinelists;

    public helpLineAdapter(Context mCtx, List<helplinelist> helplinelists) {
        this.mCtx = mCtx;
        this.helplinelists = helplinelists;
    }

    @NonNull
    @Override
    public helpLineAdapter.helplineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.custom_layout,null);
        return  new helplineViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull helplineViewHolder holder, int position) {
        helplinelist helplinelist=helplinelists.get(position);

        holder.textViewNumbers.setText(helplinelist.getContact_no());
        holder.textViewstateName.setText(helplinelist.getStateName());

    }

    @Override
    public int getItemCount() {
       return helplinelists.size();
    }
     class helplineViewHolder extends RecyclerView.ViewHolder{
        TextView textViewNumbers,textViewstateName;
         public helplineViewHolder(@NonNull View itemView) {
             super(itemView);

             textViewNumbers=itemView.findViewById(R.id.text_helpLineNo);
             textViewstateName=itemView.findViewById(R.id.text_stateName);
         }
     }
}
