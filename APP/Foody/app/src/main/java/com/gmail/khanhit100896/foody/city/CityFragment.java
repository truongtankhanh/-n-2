package com.gmail.khanhit100896.foody.city;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.khanhit100896.foody.config.Config;
import com.gmail.khanhit100896.foody.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityFragment extends Fragment {

    String getURL = Config.getConfig().getPathGetAllCity();

    RecyclerView recyclerCity;
    List<City> cityList;
    CityRecyclerViewAdapter adapter;

    public CityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_city, container, false);

        this.recyclerCity = view.findViewById(R.id.recycler_city);
        this.cityList = new ArrayList<>();

        getAllCity(getURL);

        this.adapter = new CityRecyclerViewAdapter(getActivity(),this.cityList);
        this.recyclerCity.setLayoutManager(new GridLayoutManager(getActivity(),2));
        this.recyclerCity.setAdapter(this.adapter);

        return view;
    }

    private void getAllCity(String getURL) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        cityList.clear();
                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                cityList.add(new City(
                                        object.getInt("ID"),
                                        object.getString("CityCode"),
                                        object.getString("CityName"),
                                        object.getString("CityImage")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

}
