package com.gmail.khanhit100896.foody.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.Config;
import com.gmail.khanhit100896.foody.food.Food;
import com.gmail.khanhit100896.foody.food.FoodRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Nơi ăn buffet
 */

public class HomeFood1Fragment extends Fragment {

    String getURL = Config.getConfig().getPathGetAllFoodBuffet();

    RecyclerView recyclerFoodBuffet;
    List<Food> foodBuffetList;
    FoodRecyclerViewAdapter adapter;

    public HomeFood1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home_food1, container, false);

        View view = inflater.inflate(R.layout.fragment_home_food1, container, false);

        this.recyclerFoodBuffet = view.findViewById(R.id.recycler_buffet_food);
        this.foodBuffetList = new ArrayList<>();

        getAllFoodBuffet(this.getURL);

        this.adapter = new FoodRecyclerViewAdapter(getActivity(),this.foodBuffetList);
        this.recyclerFoodBuffet.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        this.recyclerFoodBuffet.setAdapter(this.adapter);

        return view;
    }

    private void getAllFoodBuffet(String getURL) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        foodBuffetList.clear();
                        for (int i=0;i < 20;i++){

                            Random rand = new Random();

                            // Generate random integers in range 0 to response.length() - 1
                            int number = rand.nextInt(response.length());

                            try {
                                JSONObject object = response.getJSONObject(number);
                                foodBuffetList.add(new Food(
                                        object.getInt("ID"),
                                        object.getString("FoodCode"),
                                        object.getString("FoodName"),
                                        object.getString("FoodAddress"),
                                        object.getString("FoodPrice"),
                                        object.getString("ImageAddress"),
                                        object.getString("ResCode"),
                                        object.getString("KindCode")
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
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

}
