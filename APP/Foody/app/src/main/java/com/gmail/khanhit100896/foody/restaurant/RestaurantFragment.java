package com.gmail.khanhit100896.foody.restaurant;


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
public class RestaurantFragment extends Fragment {

    /*
     * Khai báo các biến cần thiết
     */
    String getURL = Config.getConfig().getPathGetAllRestaurant();
    RecyclerView recyclerRestaurant;
    List<Restaurant> restaurantList;
    RestaurantRecyclerViewAdapter adapter;
    /*
     */

    /*
     * Hàm Constructor
     */
    public RestaurantFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        /*
         * Ánh xạ và khởi tạo view, các biến đã khai báo
         */
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);
        this.recyclerRestaurant = view.findViewById(R.id.recycler_restaurant);
        this.restaurantList = new ArrayList<>();
        /*
         */

        // Gọi hàm lấy danh sách nhà hàng
        getAllRestaurant(this.getURL);

        return view;
    }

    /*
     * Hàm lấy danh sách nhà hàng từ CSDL và hiển thị theo dạng lưới
     */
    private void getAllRestaurant(String getUrl){
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getUrl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        restaurantList.clear();
                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
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

                        /*
                         * Đổ dữ liệu đồ ăn vặt lên RecyclerView
                         */
                        adapter = new RestaurantRecyclerViewAdapter(getActivity(),restaurantList);
                        recyclerRestaurant.setLayoutManager(new GridLayoutManager(getActivity(),2));
                        recyclerRestaurant.setAdapter(adapter);
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
