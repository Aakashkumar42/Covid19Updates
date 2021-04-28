package Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coronavirus_updates.Acivities.DashBroadActivity;
import com.example.coronavirus_updates.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    TextView tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths,tvAffectedCountries;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_dash_broad,null);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        tvCases=(TextView)getView(). findViewById(R.id.tvCases);
        tvRecovered=(TextView) getView().findViewById(R.id.tvRecovered);
        tvCritical=(TextView) getView().findViewById(R.id.tvCritical);
        tvActive=(TextView) getView().findViewById(R.id.tvActive);
        tvTodayCases=(TextView) getView().findViewById(R.id.tvTodayCases);
        tvTotalDeaths=(TextView) getView().findViewById(R.id.tvTotalDeaths);
        tvAffectedCountries=(TextView) getView().findViewById(R.id.tvAffectedCountries);

        simpleArcLoader=(SimpleArcLoader) getView().findViewById(R.id.loader);
        scrollView=(ScrollView)getView(). findViewById(R.id.scrollstats);






        fetchData();






        super.onViewCreated(view, savedInstanceState);
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
                    ArrayList<PieEntry> entries=new ArrayList<>();

                    entries.add(new PieEntry(tvcases));
                    entries.add(new PieEntry(tvrecovered));
                    entries.add(new PieEntry(tvdeaths));
                    entries.add(new PieEntry(tvactive));

                    PieDataSet dataSet=new PieDataSet(entries,"Reports");
                    PieData data=new PieData(dataSet);
                    dataSet.setSliceSpace(3f);


                    ArrayList<Integer> colors = new ArrayList<>();

                    for (int c : ColorTemplate.VORDIPLOM_COLORS)
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


            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(request);
    }
}
