package com.example.coronavirus_updates.Acivities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.coronavirus_updates.R;


import java.util.ArrayList;
import java.util.List;

public class AdapterClass extends ArrayAdapter<DataModel> {

        private Context mCtx;
        private List<DataModel> dataModelArrayList;
    private List<DataModel> dataModelArrayListFilter;

    public AdapterClass(@NonNull Context mCtx, List<DataModel> dataModelList) {
        super(mCtx, R.layout.customer_layout_list,dataModelList);
        this.mCtx=mCtx;
        this.dataModelArrayList=dataModelList;
        this.dataModelArrayListFilter=dataModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_layout_list,null,true);

        TextView tvstate=view.findViewById(R.id.tvStateName);
        TextView tvCases=view.findViewById(R.id.tvStateCases);
        TextView tvDailyCases=view.findViewById(R.id.tvdailyCases);
        tvstate.setText(dataModelArrayListFilter.get(position).getState());
        tvDailyCases.setText(dataModelArrayListFilter.get(position).getDeltaconfirmed());
        tvCases.setText(dataModelArrayListFilter.get(position).getConfirmed());



        return view;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Nullable
    @Override
    public DataModel getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @NonNull
    @Override
    public Filter getFilter() {

            Filter filter=new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence container) {

                FilterResults filterResults=new FilterResults();
                if (container==null || container.length()==0){

                    filterResults.count=dataModelArrayList.size();
                    filterResults.values=dataModelArrayList;


                }
                List<DataModel> resultModel=new ArrayList<>();
                String str=container.toString().toLowerCase();


                for (DataModel itemModel:dataModelArrayList){
                    itemModel.getState().toLowerCase().contains(str);
                    resultModel.add(itemModel);
                }

                filterResults.count=resultModel.size();
                filterResults.values=resultModel;

                return  filterResults;

                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults results) {

                    dataModelArrayListFilter=(List<DataModel>) results.values;
                    IndiaWiseCase.dataModelArrayList=(ArrayList<DataModel>)results.values;
                    notifyDataSetChanged();

                }


            };

return filter;



    }
}
