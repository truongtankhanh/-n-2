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
import com.gmail.khanhit100896.foody.restaurant.Restaurant;
import com.gmail.khanhit100896.foody.restaurant.RestaurantRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeRestaurantFragment extends Fragment {

    String getURL = Config.getConfig().getPathGetAllRestaurant();

    // Nhà hàng
    RecyclerView recyclerRestaurant;
    List<Restaurant> restaurantList;
    RestaurantRecyclerViewAdapter adapter;
    // Nhà hàng

    public HomeRestaurantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_restaurant, container, false);

        this.recyclerRestaurant = view.findViewById(R.id.recycler_restaurant_home);
        this.restaurantList = new ArrayList<>();

        getAllRestaurant(this.getURL);

        this.adapter = new RestaurantRecyclerViewAdapter(getActivity(),this.restaurantList);
        this.recyclerRestaurant.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        this.recyclerRestaurant.setAdapter(this.adapter);

        return view;
    }

    private void getAllRestaurant(String getUrl){
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getUrl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        restaurantList.clear();
                        for (int i=0;i<20;i++){

                            Random rand = new Random();

                            // Generate random integers in range 0 to response.length() - 1
                            int number = rand.nextInt(response.length());

                            try {
                                JSONObject object = response.getJSONObject(number);
                                restaurantList.add(new Restaurant(
                                        object.getInt("ID"),
                                        object.getString("ResCode"),
                                        object.getString("ResName"),
                                        object.getString("NumberOfBranches"),
                                        object.getString("ImageAddress"),
                                        object.getString("CityCode")
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
