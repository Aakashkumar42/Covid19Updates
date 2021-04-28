package com.example.coronavirus_updates.Acivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.coronavirus_updates.R;

public class CountryDetails extends AppCompatActivity {

    Context mCtx;
    private int positionCountry;
    TextView tvcountry,tvcases,tvtodaycases,tvdeaths,tvtodaydeaths,tvrecovered,tvactive,tvcritical
            ,tvcasesPerOneMillion,tvdeathsPerOneMillion,tvtests,tvtestPerOneMillion;
   ImageView imageView;
   Button btnIndiaWise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        imageView=findViewById(R.id.imgFlag);
        tvcountry=findViewById(R.id.tvCountryName);
        tvcases=findViewById(R.id.tvCases);
        tvdeaths=findViewById(R.id.tvTotalDeaths);
        tvtodaycases=findViewById(R.id.tvTodayCases);
        tvtodaydeaths=findViewById(R.id.tvTodaydeaths);
        tvrecovered=findViewById(R.id.tvRecovered);
        tvactive=findViewById(R.id.tvActive);
        tvcritical=findViewById(R.id.tvCritical);
        tvcasesPerOneMillion=findViewById(R.id.tvcasesPerOneMillion);
        tvdeathsPerOneMillion=findViewById(R.id.tvdeathsPerOneMillion);
        tvtests=findViewById(R.id.tvtests);
        tvtestPerOneMillion=findViewById(R.id.tvtestsPerOneMillion);
        btnIndiaWise=findViewById(R.id.BtnindiaCasesStateWise);

        Intent intent=getIntent();
        positionCountry=intent.getIntExtra("position",0);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Details of "+AffectedCountries.countryModelList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tvcases.setText(AffectedCountries.countryModelList.get(positionCountry).getCases());
        tvtodaycases.setText(AffectedCountries.countryModelList.get(positionCountry).getTodaycases());
        tvdeaths.setText(AffectedCountries.countryModelList.get(positionCountry).getDeaths());
        tvtodaydeaths.setText(AffectedCountries.countryModelList.get(positionCountry).getTodaydeaths());
        tvrecovered.setText(AffectedCountries.countryModelList.get(positionCountry).getRecovered());
        tvactive.setText(AffectedCountries.countryModelList.get(positionCountry).getActive());
        tvcritical.setText(AffectedCountries.countryModelList.get(positionCountry).getCritical());
        tvcasesPerOneMillion.setText(AffectedCountries.countryModelList.get(positionCountry).getCasesPerOneMillion());
        tvdeathsPerOneMillion.setText(AffectedCountries.countryModelList.get(positionCountry).getDeathsPerOneMillion());
        tvtests.setText(AffectedCountries.countryModelList.get(positionCountry).getTests());
        tvtestPerOneMillion.setText(AffectedCountries.countryModelList.get(positionCountry).getTestPerOneMillion());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void IndiaStateWise(View view) {



    }
}
