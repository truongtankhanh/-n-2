package com.gmail.khanhit100896.foody.home;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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
 */
public class HomeNearFragment extends Fragment {

    protected String getURL = Config.getConfig().getPathGetAllFood();
    protected RecyclerView recyclerFood;
    protected List<Food> foodList;
    protected FoodRecyclerViewAdapter adapter;

    public HomeNearFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_near, container, false);
        this.recyclerFood = view.findViewById(R.id.recycler_near_food);
        this.foodList = new ArrayList<>();

        getAllFood(getURL);

        return view;
    }

    /*
     * Hàm lấy các món ăn gần người dùng nhất
     */
    private void getAllFood(String getURL) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getURL, null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONArray response) {
                        foodList.clear();
                        for (int i=0;i < 10;i++){

                            Random rand = new Random();

                            // Generate random integers in range 0 to response.length() - 1
                            int number = rand.nextInt(response.length());

                            try {
                                JSONObject object = response.getJSONObject(number);
                                foodList.add(new Food(
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
                        /*
                         * Đổ dữ liệu món ăn lên RecyclerView
                         */

                        adapter = new FoodRecyclerViewAdapter(getActivity(),foodList);
                        recyclerFood.setLayoutManager(new LinearLayoutManager(getActivity(),
                                LinearLayoutManager.HORIZONTAL, false));
                        recyclerFood.setAdapter(adapter);
                        /*
                         */
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
