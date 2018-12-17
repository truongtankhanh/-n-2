package com.gmail.khanhit100896.foody.like;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.Config;
import com.gmail.khanhit100896.foody.food.Food;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodLikeFragment extends Fragment {

    /*
     * Khai báo biến cần thiết
     */
    protected String getURL = Config.getConfig().getPathGetAllFood();
    protected RecyclerView recyclerFood;
    protected List<Food> foodList;
    protected FoodRecyclerViewAdapter adapter;

    protected TextView txt_thong_bao;
    /*
     */


    public FoodLikeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*
         * Ánh xạ và khởi tạo view, các biến đã khai báo
         */
        View view = inflater.inflate(R.layout.fragment_food_like, container, false);
        this.recyclerFood   = view.findViewById(R.id.recycler_food);
        this.foodList       = new ArrayList<>();
        this.txt_thong_bao  = view.findViewById(R.id.txt_thong_bao);
        txt_thong_bao.setVisibility(View.GONE);
        /*
         */

        getAllFood(this.getURL);

        return view;
    }

    /*
     * Hàm lấy tất cả các món ăn từ CSDL và hiển thị theo dạng lưới
     */
    private void getAllFood(String getURL) {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        foodList.clear();
                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject object = response.getJSONObject(i);

                                if(object.getInt("ActionLike") == 1){
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
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        /*
                         * Đổ dữ liệu đồ ăn vặt lên RecyclerView
                         */
                        if(foodList.size() == 0){
                            recyclerFood.setVisibility(View.GONE);
                            txt_thong_bao.setVisibility(View.VISIBLE);
                        }else {
                            recyclerFood.setVisibility(View.VISIBLE);
                            adapter = new FoodRecyclerViewAdapter(getActivity(), foodList);
                            recyclerFood.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                            recyclerFood.setAdapter(adapter);
                        }
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
