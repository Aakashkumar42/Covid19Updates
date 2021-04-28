package com.example.coronavirus_updates.Acivities;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coronavirus_updates.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashBroadActivity extends AppCompatActivity implements OnChartValueSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    TextView tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths,tvAffectedCountries;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;

    String cases,recovered,deaths,active;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    NavigationView nv;

    TextView uid;

    Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_broad);

        drawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv=(NavigationView) findViewById(R.id.nav_bar);

        nv.setNavigationItemSelectedListener(this);

      firebaseAuth=FirebaseAuth.getInstance();

      if (firebaseAuth.getCurrentUser()==null){

          finish();
          startActivity(new Intent(getApplicationContext(),LoginActivity.class));
      }

        firebaseUser=firebaseAuth.getCurrentUser();


        uid=(TextView)nv.getHeaderView(0).findViewById(R.id.uid);

        uid.setText(firebaseUser.getEmail());


        tvCases=(TextView) findViewById(R.id.tvCases);
        tvRecovered=(TextView) findViewById(R.id.tvRecovered);
        tvCritical=(TextView) findViewById(R.id.tvCritical);
        tvActive=(TextView) findViewById(R.id.tvActive);
        tvTodayCases=(TextView) findViewById(R.id.tvTodayCases);
        tvTotalDeaths=(TextView) findViewById(R.id.tvTotalDeaths);
        tvTodayDeaths=(TextView) findViewById(R.id.tvTodaydeaths);
        tvAffectedCountries=(TextView) findViewById(R.id.tvAffectedCountries);

        simpleArcLoader=(SimpleArcLoader) findViewById(R.id.loader);
        scrollView=(ScrollView) findViewById(R.id.scrollstats);
        pieChart=findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setText("Cases of Corona");





        fetchData();

    }



    private void logOut() {

    firebaseAuth.signOut();
    sendToLogin();
    }

    private void sendToLogin() {

    Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
    startActivity(intent);
    finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void fetchData() {

    String URL="https://corona.lmao.ninja/v2/all";

    simpleArcLoader.start();

    StringRequest request=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            try {
                JSONObject jsonObject=new JSONObject(response.toString());

                tvCases.setText(jsonObject.getString("cases"));
                tvRecovered.setText(jsonObject.getString("recovered"));
                tvCritical.setText(jsonObject.getString("critical"));
                tvActive.setText(jsonObject.getString("active"));
                tvTodayCases.setText(jsonObject.getString("todayCases"));
                tvTotalDeaths.setText(jsonObject.getString("deaths"));
                tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));

                Integer tvcases=jsonObject.getInt("cases");
                Integer tvrecovered=jsonObject.getInt("recovered");
                Integer tvdeaths=jsonObject.getInt("deaths");
                Integer tvactive=jsonObject.getInt("active");

                ArrayList entries=new ArrayList<>();

                entries.add(new PieEntry(tvcases,0));
                entries.add(new PieEntry(tvrecovered,1));
                entries.add(new PieEntry(tvdeaths,2));
                entries.add(new PieEntry(tvactive,3));

                PieDataSet dataSet=new PieDataSet(entries,"Data in chart");

                ArrayList Valy=new ArrayList<>();

                Valy.add("cases");
                Valy.add("recovered");
                Valy.add("deaths");
                Valy.add("active");

                PieData data=new PieData(dataSet);



                ArrayList<Integer> colors = new ArrayList<>();

                for (int c : ColorTemplate.VORDIPLOM_COLORS )
                    colors.add(c);

                for (int c : ColorTemplate.JOYFUL_COLORS)
                    colors.add(c);

                for (int c : ColorTemplate.COLORFUL_COLORS)
                    colors.add(c);

                for (int c : ColorTemplate.LIBERTY_COLORS)
                    colors.add(c);

                for (int c : ColorTemplate.PASTEL_COLORS)
                    colors.add(c);


                colors.add(ColorTemplate.getHoloBlue());

                Legend legend=pieChart.getLegend();
                  legend.setEnabled(true);
                legend.setTextColor(Color.red(1));

                dataSet.setColors(colors);
                data.setValueFormatter(new PercentFormatter(pieChart));
                data.setValueTextSize(11f);
                data.setValueTextColor(Color.WHITE);
                pieChart.setData(data);
                pieChart.animate();
                pieChart.invalidate();

                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);




            }



            catch (JSONException e) {
                e.printStackTrace();
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
            }

        }
        }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            simpleArcLoader.stop();
            simpleArcLoader.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
            Toast.makeText(DashBroadActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

        }
    });

    RequestQueue requestQueue=Volley.newRequestQueue(this);
    requestQueue.add(request);
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    public void track_countries(View view) {

        Intent intent=new Intent(getApplicationContext(),AffectedCountries.class);
        startActivity(intent);
    }


    public void IndiaStateWise(View view) {

    Intent intent=new Intent(getApplicationContext(),IndiaWiseCase.class);
    startActivity(intent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if (id==R.id.navigation_home){

            Intent home_intent=new Intent(getApplicationContext(),DashBroadActivity.class);
            startActivity(home_intent);
        }else if (id==R.id.logout){

            firebaseAuth.signOut();
            Intent logout=new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(logout);
            finish();

        }

        return true;




        }
}
