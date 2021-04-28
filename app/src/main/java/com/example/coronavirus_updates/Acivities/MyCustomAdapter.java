package com.example.coronavirus_updates.Acivities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.coronavirus_updates.R;

import java.util.ArrayList;
import java.util.List;

public class   MyCustomAdapter extends  ArrayAdapter<CountryModel> {


    private Context mCtx;
    private List<CountryModel> countryModelList;
    private List<CountryModel> countryModelListFiltered;



    public MyCustomAdapter(@NonNull Context mCtx, List<CountryModel> countryModelList) {
        super(mCtx, R.layout.list_custom_item,countryModelList);

        this.mCtx=mCtx;
        this.countryModelList=countryModelList;
        this.countryModelListFiltered=countryModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item,null,true);

        TextView tvCountryName=view.findViewById(R.id.tvCountryName);
        TextView tvcases=view.findViewById(R.id.tvCasesCountry);
        ImageView imageView=view.findViewById(R.id.imgFlag);

        tvCountryName.setText(countryModelListFiltered.get(position).getCountry());
        tvcases.setText(countryModelListFiltered.get(position).getCases());

        Glide.with(mCtx).load(countryModelListFiltered.get(position).getFlag()).into(imageView);

        return view;
    }

    @Override
    public int getCount() {
        return countryModelListFiltered.size();

    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelListFiltered.get(position);
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
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults=new FilterResults();
                if (constraint == null || constraint.length()==0){
                    filterResults.count=countryModelList.size();
                    filterResults.values=countryModelList;


                }

                List<CountryModel> resultModel=new ArrayList<>();
                String seachStr=constraint.toString().toLowerCase();


                for(CountryModel itemModel:countryModelList){
                    if (itemModel.getCountry().toLowerCase().contains(seachStr)){
                        resultModel.add(itemModel);

                    }
                    filterResults.count=resultModel.size();
                    filterResults.values=resultModel;

                }

                for (CountryModel itemCases:countryModelList){
                    if (itemCases.getCases().toLowerCase().contains(seachStr)){
                        resultModel.add(itemCases);
                    }
                    filterResults.count=resultModel.size();
                    filterResults.values=resultModel;
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {

                countryModelListFiltered=(List<CountryModel>) results.values;
                AffectedCountries.countryModelList=(List<CountryModel>) results.values;
                notifyDataSetChanged();


            }
        };


        return filter;
    }
}
