package com.example.coronavirus_updates.Acivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coronavirus_updates.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class IndiaWiseCase extends AppCompatActivity {

    private String URL="https://api.covid19india.org/data.json";
    private String link="https://api.covid19india.org/state_district_wise.json";
    private ListView listView;
    public static ArrayList<DataModel> dataModelArrayList=new ArrayList<>();
    private AdapterClass adapterClass;
    DataModel dataModel;
    public static ArrayList<DistrictModelClass> districtModelClassArrayList=new ArrayList<>();
    DistrictModelClass districtModelClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india_wise_case);

        listView=findViewById(R.id.listView2);

        getSupportActionBar().setTitle("State Wise Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        districtData();
        fetchJSON();

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        startActivity(new Intent(getApplicationContext(),IndiaDetailsActivity.class).putExtra("position",position));
        }
});


    }

    private void districtData() {

            StringRequest request=new StringRequest(Request.Method.GET, link,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                            JSONObject jsonObject=new JSONObject(response);

                            for (int i=0;i<jsonObject.length();i++){

                                JSONObject districtDataObj=jsonObject.getJSONObject("districtData");

                                for (int j=0;j<districtDataObj.length();j++){

                                    String districtCases=districtDataObj.getString("confirmed");
                                    String districtactive=districtDataObj.getString("active");
                                    String districtdeceased=districtDataObj.getString("deceased");
                                    districtModelClass=new DistrictModelClass(districtCases,districtactive,districtdeceased);
                                }
                                districtModelClassArrayList.add(districtModelClass);
                            }



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });



    }


    private void fetchJSON() {

        StringRequest request=new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj=new JSONObject(response);



                            JSONArray dataArray=obj.getJSONArray("statewise");

                            for (int i=0;i<dataArray.length();i++){

                                JSONObject jsonObject=dataArray.getJSONObject(i);

                                String active=jsonObject.getString("active");
                                String confirmed=jsonObject.getString("confirmed");
                                String deaths=jsonObject.getString("deaths");
                                String deltaconfirmed=jsonObject.getString("deltaconfirmed");
                                String deltadeaths=jsonObject.getString("deltadeaths");
                                String deltarecovered=jsonObject.getString("deltarecovered");
                                String lastupdatedtime=jsonObject.getString("lastupdatedtime");
                                String recovered=jsonObject.getString("recovered");
                                String state=jsonObject.getString("state");
                                String statecode=jsonObject.getString("statecode");
                                String statenotes=jsonObject.getString("statenotes");

                                dataModel=new DataModel(active,confirmed,deaths,deltaconfirmed,deltadeaths,deltarecovered,
                                        lastupdatedtime,recovered,state,statecode,statenotes);
                                dataModelArrayList.add(dataModel);



                            }
                            adapterClass=new AdapterClass(IndiaWiseCase.this,dataModelArrayList);
                            listView.setAdapter(adapterClass);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

}