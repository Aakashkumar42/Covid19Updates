package com.example.coronavirus_updates.Acivities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coronavirus_updates.Acivities.helpLineAdapter;
import com.example.coronavirus_updates.Acivities.helplinelist;
import com.example.coronavirus_updates.R;

import java.util.ArrayList;
import java.util.List;

public class helplineNoActivity extends AppCompatActivity {

    List<helplinelist> helplinelists;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline_no);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        helplinelists=new ArrayList<>();

        helplinelists.add(
                 new helplinelist("0866-2410978","Andhra Pradesh")
        );

        helplinelists.add(
                new helplinelist("9436055743","Arunachal Pradesh ")
        );
        helplinelists.add(
                new helplinelist("6913347770","Assam")
        );
        helplinelists.add(
                new helplinelist("104","Bihar")
        );
        helplinelists.add(
                new helplinelist("104","Chhattisgarh ")
        );
        helplinelists.add(
                new helplinelist("104","Goa ")
        );
        helplinelists.add(
                new helplinelist("104","Gujarat ")
        );
        helplinelists.add(
                new helplinelist("8558893911","Haryana")
        );
        helplinelists.add(
                new helplinelist("104","Himachal Pradesh")
        );
        helplinelists.add(
                new helplinelist("104","Jharkhand")
        );
        helplinelists.add(
                new helplinelist("104","Karnataka ")
        );
        helplinelists.add(
                new helplinelist("0471-2552056","Kerala ")
        );
        helplinelists.add(
                new helplinelist("104","Madhya Pradesh")
        );
        helplinelists.add(
                new helplinelist("020-26127394","Maharashtra")
        );
        helplinelists.add(
                new helplinelist("3852411668","Manipur")
        );

        helplinelists.add(
                new helplinelist("108","Meghalaya")
        );
        helplinelists.add(
                new helplinelist("3852411668","Manipur")
        );
        helplinelists.add(
                new helplinelist("102","Mizoram ")
        );
        helplinelists.add(
                new helplinelist("7005539653","Nagaland")
        );
        helplinelists.add(
                new helplinelist("9439994859","Odisha")
        );
        helplinelists.add(
                new helplinelist("104","Punjab")
        );
        helplinelists.add(
                new helplinelist("0141-2225624","Rajasthan")
        );

        helplinelists.add(
                new helplinelist("104","Sikkim")
        );


        helplinelists.add(
                new helplinelist("044-29510500","Tamil Nadu")
        );


        helplinelists.add(
                new helplinelist("104 ","Telangana")
        );


        helplinelists.add(
                new helplinelist("0381-2315879","Tripura")
        );


        helplinelists.add(
                new helplinelist("104","Uttarakhand")
        );


        helplinelists.add(
                new helplinelist("18001805145","Uttar Pradesh")
        );


        helplinelists.add(
                new helplinelist("1800313444222, 03323412600, ","West Bengal")
        );


        helplinelists.add(
                new helplinelist("03192-232102","Andaman and Nicobar Islands")
        );

        helplinelists.add(
                new helplinelist("9779558282","Chandigarh")
        );

        helplinelists.add(
                new helplinelist("104","Dadra and Nagar Haveli and Daman & Diu ")
        );

        helplinelists.add(
                new helplinelist("011-22307145","Delhi")
        );

        helplinelists.add(
                new helplinelist("01912520982, 0194-2440283","Jammu & Kashmir")
        );
        helplinelists.add(
                new helplinelist("01982256462","Ladakh")
        );
        helplinelists.add(
                new helplinelist("104","Lakshadeep")
        );
        helplinelists.add(
                new helplinelist("104","Puducherry")
        );


        helpLineAdapter adapter=new helpLineAdapter(this,helplinelists);
        recyclerView.setAdapter(adapter);
    }
}
