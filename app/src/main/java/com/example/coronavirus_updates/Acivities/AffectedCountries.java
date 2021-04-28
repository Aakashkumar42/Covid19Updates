package com.example.coronavirus_updates.Acivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coronavirus_updates.R;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AffectedCountries extends AppCompatActivity {

    EditText editTextSearch;
        ListView listView;
    SimpleArcLoader loader;

    public static List<CountryModel> countryModelList=new ArrayList<>();
    CountryModel countryModel;
    MyCustomAdapter myCustomAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affected_countries);

        editTextSearch=(EditText) findViewById(R.id.edtsearch);
        listView=(ListView) findViewById(R.id.listView);
        loader=(SimpleArcLoader) findViewById(R.id.ARCloader);

        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fetchdata();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),CountryDetails.class).putExtra("postion",position));
            }
        });

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                    myCustomAdapter.getFilter().filter(s);
                    myCustomAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }


    private void fetchdata() {

        String URL="https://corona.lmao.ninja/v2/countries/ ";

        loader.start();

        StringRequest request=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        String countryName=jsonObject.getString("country");
                        String cases=jsonObject.getString("cases");
                        String todayCases=jsonObject.getString("todayCases");
                        String deaths=jsonObject.getString("deaths");
                        String todayDeaths=jsonObject.getString("todayDeaths");
                        String recovered=jsonObject.getString("recovered");
                        String active=jsonObject.getString("active");
                        String critical=jsonObject.getString("critical");
                        String casesPerOneMillion=jsonObject.getString("casesPerOneMillion");
                        String deathsPerOneMillion=jsonObject.getString("deathsPerOneMillion");
                        String tests=jsonObject.getString("tests");
                        String testsPerOneMillion=jsonObject.getString("testsPerOneMillion");

                        JSONObject object=jsonObject.getJSONObject("countryInfo");
                        String flagUrl=object.getString("flag");

                        countryModel=new CountryModel(flagUrl,countryName,cases,todayCases,deaths,todayDeaths,recovered,active,critical,casesPerOneMillion
                                ,deathsPerOneMillion,tests,testsPerOneMillion);
                        countryModelList.add(countryModel);

                    }

                    myCustomAdapter=new MyCustomAdapter(AffectedCountries.this,countryModelList);
                    listView.setAdapter(myCustomAdapter);
                    loader.stop();
                    loader.setVisibility(View.GONE);

                }



                catch (JSONException e) {
                    loader.stop();
                    loader.setVisibility(View.GONE);

                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AffectedCountries.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                loader.stop();
                loader.setVisibility(View.GONE);


            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}