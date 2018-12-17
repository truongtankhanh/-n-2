package com.gmail.khanhit100896.foody.like;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.comment.Comment;
import com.gmail.khanhit100896.foody.comment.CommentAdapter;
import com.gmail.khanhit100896.foody.config.Config;
import com.gmail.khanhit100896.foody.food.FoodDetailsActivity;
import com.gmail.khanhit100896.foody.kind.Kind;
import com.gmail.khanhit100896.foody.main.CommentActivity;
import com.gmail.khanhit100896.foody.main.GeocodingLocation;
import com.gmail.khanhit100896.foody.main.LoginActivity;
import com.gmail.khanhit100896.foody.main.MapActivity;
import com.gmail.khanhit100896.foody.main.Point;
import com.gmail.khanhit100896.foody.user.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FoodLikeDetailsActivity extends AppCompatActivity {

    /*
     * Khai báo các biến cần thiết
     */
    protected Toolbar app_bar_food_details;
    protected ImageView img_back_food_details;
    protected TextView txt_address_food;

    protected ImageView imgFood;
    protected TextView txtFoodName;
    protected TextView txtFoodAddress;
    protected TextView txtFoodPrice;
    protected TextView txtFoodKind;
    protected ImageView imgComment;
    protected TextView txt_num_comment;

    protected Intent intent;
    protected List<Kind> kindList;

    protected String kindName = "";
    protected String kindCode = "";
    protected String foodCode = "";
    protected int idFood, actionlike;

    protected String getURl = Config.getConfig().getPathGetAllComment();
    private String getURLUpdateFood = Config.getConfig().getPathUpdateFood();

    protected RecyclerView recyclerView;
    protected List<Comment> commentList;
    protected CommentAdapter adapter;
    /*
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_like_details);

        // Gọi hàm ánh xạ
        init();

        getAllKind(Config.getConfig().getPathGetAllKind());

        getAllComment(getURl,foodCode);

        adapter = new CommentAdapter(getApplicationContext(),commentList);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        recyclerView.setAdapter(adapter);

        /*
         * Bắt sự kiện cho TextView chuyển đến MapActivity và thực bài toán hiện chỉ đường
         */
        this.txt_address_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Point point = new Point(GeocodingLocation.getLatitude(txt_address_food.getText().toString(),getApplicationContext())
                        ,GeocodingLocation.getLongitude(txt_address_food.getText().toString(),getApplicationContext()));

                Intent intent = new Intent(getApplicationContext(),MapActivity.class);
                String context = FoodLikeDetailsActivity.class.getSimpleName();
                intent.putExtra("context",context);
                intent.putExtra("name",txtFoodName.getText().toString());
                intent.putExtra("address",txt_address_food.getText().toString());
                intent.putExtra("latitude",String.valueOf(point.getLatitude()));
                intent.putExtra("longitude",String.valueOf(point.getLongitude()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        /*
         */

        // Bắt sự kiện chuyển tới màn hình comment
        this.imgComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent intent1 = new Intent(getApplicationContext(),CommentActivity.class);
                    String context = FoodLikeDetailsActivity.class.getSimpleName();
                    intent1.putExtra("context",context);
                    intent1.putExtra("code",foodCode);
                    intent1.putExtra("name",txtFoodName.getText());
                    intent1.putExtra("address",txtFoodAddress.getText());
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent1);
                }
                else{
                    Intent intent1 = new Intent(getApplicationContext(),LoginActivity.class);
                    String context = FoodLikeDetailsActivity.class.getSimpleName();
                    intent1.putExtra("context",context);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent1);
                }
            }
        });
    }

    /*
     * Hàm ánh xạ
     */
    @SuppressLint({"CutPasteId", "SetTextI18n"})
    private void init() {
        /*
         * Ánh xạ các id và khởi tạo 1 số biến
         */
        this.app_bar_food_details   = findViewById(R.id.app_bar_food_details);
        this.imgFood                = findViewById(R.id.img_food_details);
        this.txt_address_food       = findViewById(R.id.txt_address_food);
        this.txtFoodName            = findViewById(R.id.txt_name_food_details);
        this.txtFoodAddress         = findViewById(R.id.txt_address_food);
        this.txtFoodPrice           = findViewById(R.id.txt_price_food);
        this.txtFoodKind            = findViewById(R.id.txt_kind_food);
        this.imgComment             = findViewById(R.id.imgComment);
        this.txt_num_comment        = findViewById(R.id.txt_num_comment);
        this.img_back_food_details  = findViewById(R.id.img_back_food_details);
        this.kindList               = new ArrayList<>();
        setSupportActionBar(this.app_bar_food_details);
        this.commentList            = new ArrayList<>();
        this.recyclerView           = findViewById(R.id.recycler_comment);

        /*
         */

        /*
         * Lấy thông tin món gửi từ FoodRecyclerViewAdapter và gán giá trị cho các thuộc tính
         */
        intent = getIntent();
        final String className = Objects.requireNonNull(intent.getExtras()).getString("class");
        String image = Objects.requireNonNull(intent.getExtras()).getString("FoodImage");
        String name = intent.getExtras().getString("FoodName");
        String address = intent.getExtras().getString("FoodAddress");
        String price = intent.getExtras().getString("FoodPrice");
        kindCode = intent.getExtras().getString("FoodKind");
        foodCode = intent.getExtras().getString("FoodCode");
        this.idFood = intent.getExtras().getInt("ID");
        this.actionlike = intent.getExtras().getInt("ActionLike");

        this.app_bar_food_details.setTitle(name);
        this.txtFoodName.setText(name);
        this.txtFoodAddress.setText(address);

        assert price != null;
        if(price.equals("")){
            this.txtFoodPrice.setText("Đang cập nhật");
        }else{
            this.txtFoodPrice.setText(price);
        }

        if (image != null) {
            Picasso.get().load(Config.getConfig().getPathLoadImgFood().concat(image))
                    .into(this.imgFood);
        }
        /*
         */

        /*
         * Bắt sự kiện click cho image chuyển từ màn hình hiện tại về màn hình trước đó
         */
        this.img_back_food_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(getApplicationContext(), Class.forName(className).getClass()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
        /*
         */

    }

    /*
     *  Lấy tất cả thông tin loại món ăn từ CSDL
     */
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

    /*
     * Hàm khởi tạo menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_branch_like_details, menu);
        return true;
    }

    /*
     * Hàm bắt sự kiện cho menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Gửi dữ liệu qua MapActivity và bắt sự kiện tìm đường
        if (id == R.id.action_map) {
            Point point = new Point(
                    GeocodingLocation.getLatitude(txtFoodAddress.getText().toString(),getApplicationContext())
                    ,GeocodingLocation.getLongitude(txtFoodAddress.getText().toString(),getApplicationContext()));

            Intent intent2 = new Intent(getApplicationContext(),MapActivity.class);
            String context = FoodDetailsActivity.class.getSimpleName();
            intent2.putExtra("context",context);
            intent2.putExtra("name",txtFoodName.getText().toString());
            intent2.putExtra("address",txtFoodAddress.getText().toString());
            intent2.putExtra("latitude",String.valueOf(point.getLatitude()));
            intent2.putExtra("longitude",String.valueOf(point.getLongitude()));
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent2);
        }
        else if(id == R.id.action_comment){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                Intent intent1 = new Intent(getApplicationContext(),CommentActivity.class);
                String context = FoodLikeDetailsActivity.class.getSimpleName();
                intent1.putExtra("context",context);
                intent1.putExtra("code",foodCode);
                intent1.putExtra("name",txtFoodName.getText());
                intent1.putExtra("address",txtFoodAddress.getText());
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }
            else{
                Intent intent1 = new Intent(getApplicationContext(),LoginActivity.class);
                String context = FoodLikeDetailsActivity.class.getSimpleName();
                intent1.putExtra("context",context);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }
        }
        else if(id == R.id.action_dislike){
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, getURLUpdateFood,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.trim().equals("success")){
                                Toast.makeText(getApplicationContext(),"Đã xóa "+txtFoodName.getText() +" khỏi danh sách yêu thích.",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(),"Lỗi. Vui lòng kiểm tra lại.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
            ){
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<>();
                    params.put("id", String.valueOf(idFood));
                    params.put("actionLike", String.valueOf(actionlike));

                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }

        return super.onOptionsItemSelected(item);
    }

    /*
     *  Lấy tất cả comment từ CSDL
     */
    private void getAllComment(String getURL, final String foodCode) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getURL, null,
                new Response.Listener<JSONArray>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONArray response) {
                        commentList.clear();
                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject object = response.getJSONObject(i);

                                if(object.getString("FoodCode").equals(foodCode)){
                                    User user = new User(object.getString("UserName"),object.getString("Email"),
                                            object.getString("PhotoUrl"));
                                    commentList.add(new Comment(
                                            object.getInt("ID"),
                                            user,
                                            object.getString("Create_time"),
                                            object.getString("FoodCode"),
                                            object.getString("Comment")
                                    ));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        txt_num_comment.setText(commentList.size() + " bình luận");
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
