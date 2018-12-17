package com.gmail.khanhit100896.foody.food;


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
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChayFoodFragment extends Fragment {

    /*
     * Khai báo biến cần thiết
     */
    protected String getURL = Config.getConfig().getPathGetAllFoodChay();
    protected RecyclerView recyclerFoodChay;
    protected List<Food> foodChayList;
    protected FoodRecyclerViewAdapter adapter;
    /*
     */

    /*
     * Hàm Constructor
     */
    public ChayFoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        /*
         * Ánh xạ và khởi tạo view, các biến đã khai báo
         */
        View view = inflater.inflate(R.layout.fragment_chay_food, container, false);
        this.recyclerFoodChay = view.findViewById(R.id.recycler_chay);
        this.foodChayList = new ArrayList<>();
        /*
         */

        getAllFoodChay(this.getURL);

        return view;
    }

    /*
     * Hàm lấy tất cả các món chay từ CSDL và hiển thị theo dạng lưới
     */
    private void getAllFoodChay(String getURL) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        foodChayList.clear();
                        for (int i=0;i < response.length();i++){

                            try {
                                JSONObject object = response.getJSONObject(i);

                                foodChayList.add(new Food(
                                        object.getInt("ID"),
                                        object.getString("FoodCode"),
                                        object.getString("FoodName"),
                                        object.getString("FoodAddress"),
                                        object.getString("FoodPrice"),
                                        object.getString("ImageAddress"),
                                        object.getString("ResCode"),
                                        object.getString("KindCode"),
                                        object.getInt("ActionLike")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        /*
                         * Đổ dữ liệu đồ ăn vặt lên RecyclerView
                         */
                        adapter = new FoodRecyclerViewAdapter(getActivity(),foodChayList);
                        recyclerFoodChay.setLayoutManager(new GridLayoutManager(getActivity(),2));
                        recyclerFoodChay.setAdapter(adapter);
                        /*
                         */
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //error.getMessage();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

}
