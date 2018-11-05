package com.gmail.khanhit100896.foody.food;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.Config;
import com.gmail.khanhit100896.foody.kind.Kind;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FoodDetailsActivity extends AppCompatActivity {

    protected ImageView imgFood;
    protected TextView txtFoodName;
    protected TextView txtFoodAddress;
    protected TextView txtFoodPrice;
    protected TextView txtFoodKind;

    protected Intent intent;
    protected List<Kind> kindList;

    protected String kindName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        init();

        getAllKind(Config.getConfig().getPathGetAllKind());
    }

    private void init() {
        this.imgFood        = findViewById(R.id.img_food_details);
        this.txtFoodName    = findViewById(R.id.txt_name_food_details);
        this.txtFoodAddress = findViewById(R.id.txt_address_food);
        this.txtFoodPrice = findViewById(R.id.txt_price_food);
        this.txtFoodKind = findViewById(R.id.txt_kind_food);

        kindList = new ArrayList<>();

        intent = getIntent();
        String image = Objects.requireNonNull(intent.getExtras()).getString("FoodImage");
        String name = intent.getExtras().getString("FoodName");
        String address = intent.getExtras().getString("FoodAddress");
        String price = intent.getExtras().getString("FoodPrice");

        this.txtFoodName.setText(name);
        this.txtFoodAddress.setText(address);
        this.txtFoodPrice.setText(price);
        if (image != null) {
            Picasso.get().load(Config.getConfig().getPathLoadImgFood().concat(image))
                    .placeholder(R.drawable.search_mages_icon).into(this.imgFood);
        }
    }

    private void getAllKind(String getURL) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        kindList.clear();
                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                kindList.add(new Kind(
                                        object.getInt("ID"),
                                        object.getString("KindCode"),
                                        object.getString("KindName")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        String kind = Objects.requireNonNull(intent.getExtras()).getString("FoodKind");
                        for (Kind kind1 : kindList){
                            if(kind1.getKind_code().equals(kind)){
                                kindName = kind1.getKind_name();
                            }
                        }
                        txtFoodKind.setText(kindName);
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
