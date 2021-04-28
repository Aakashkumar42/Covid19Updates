package com.example.coronavirus_updates.Acivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coronavirus_updates.R;

import java.util.List;

public class IndiaDetailsActivity extends AppCompatActivity {

    private int Indiaposition;

    TextView active,confirmed,deaths,deltaconfirmed,deltadeaths,deltarecovered,
            lastupdatedtime,recovered,state,statecode,statenotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india_details);

        confirmed=findViewById(R.id.ConfirmCases);
        recovered=findViewById(R.id.totalRecovered);
        deltaconfirmed=findViewById(R.id.todayCases);
        active=findViewById(R.id.active);
        deaths=findViewById(R.id.tvdeaths);
        deltarecovered=findViewById(R.id.deltarecovered);
        lastupdatedtime=findViewById(R.id.lastUpdatetime);

        Intent intent=getIntent();
        Indiaposition=intent.getIntExtra("position",0);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(IndiaWiseCase.dataModelArrayList.get(Indiaposition).getState());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lastupdatedtime.setText(IndiaWiseCase.dataModelArrayList.get(Indiaposition).getLastupdatedtime());
        confirmed.setText(IndiaWiseCase.dataModelArrayList.get(Indiaposition).getConfirmed());
        recovered.setText(IndiaWiseCase.dataModelArrayList.get(Indiaposition).getRecovered());
        deltaconfirmed.setText(IndiaWiseCase.dataModelArrayList.get(Indiaposition).getDeltaconfirmed());
        active.setText(IndiaWiseCase.dataModelArrayList.get(Indiaposition).getActive());
        deaths.setText(IndiaWiseCase.dataModelArrayList.get(Indiaposition).getDeaths());
        deltarecovered.setText(IndiaWiseCase.dataModelArrayList.get(Indiaposition).getDeltarecovered());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void DistrictWise(View view) {

        Intent intent=new Intent(getApplicationContext(),);

    }
}
