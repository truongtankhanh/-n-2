package com.gmail.khanhit100896.foody.like;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
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
import com.gmail.khanhit100896.foody.branch.BranchDetailsActivity;
import com.gmail.khanhit100896.foody.comment.Comment;
import com.gmail.khanhit100896.foody.comment.CommentAdapter;
import com.gmail.khanhit100896.foody.config.Config;
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

public class BranchLikeDetailsActivity extends AppCompatActivity {

    /*
     * Khái báo biến cần thiết
     */
    protected Toolbar app_bar_branch_details;
    protected ImageView img_back_branch_details;

    protected ImageView img_branch_details;
    protected TextView txt_name_branch_details;
    protected TextView txt_address_branch;
    protected TextView txt_open_time;
    protected TextView txt_price_branch;
    protected ImageView imgComment;
    protected TextView txt_num_comment;

    protected Intent intent;
    protected String branchCode;
    protected int idBranch, actionlike;

    protected String getURl = Config.getConfig().getPathGetAllComment();
    private String getURLBranch = Config.getConfig().getPathUpdateBranch();

    protected RecyclerView recyclerView;
    protected List<Comment> commentList;
    protected CommentAdapter adapter;
    /*
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_like_details);

        // Gọi hàm ánh xạ
        init();

        getAllComment(getURl,branchCode);

        adapter = new CommentAdapter(getApplicationContext(),commentList);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        recyclerView.setAdapter(adapter);

        /*
         * Bắt sự kiện truy tìm vị trí của nhà hàng
         */
        this.txt_address_branch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                GeocodingLocation.getAddressFromLocation(txt_address_branch.getText().toString()
//                        ,getApplicationContext(), new GeocoderHandler());

                Double latitude = GeocodingLocation.getLatitude(txt_address_branch.getText().toString(),getApplicationContext());
                Double longitude = GeocodingLocation.getLongitude(txt_address_branch.getText().toString(),getApplicationContext());

                Intent intent = new Intent(getApplicationContext(),MapActivity.class);
                String context = BranchDetailsActivity.class.getSimpleName();
                intent.putExtra("context",context);
                intent.putExtra("name",txt_name_branch_details.getText().toString());
                intent.putExtra("address",txt_address_branch.getText().toString());
                intent.putExtra("latitude",String.valueOf(latitude));
                intent.putExtra("longitude",String.valueOf(longitude));
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
                    String context = BranchLikeDetailsActivity.class.getSimpleName();
                    intent1.putExtra("context",context);
                    intent1.putExtra("code",branchCode);
                    intent1.putExtra("name",txt_name_branch_details.getText());
                    intent1.putExtra("address",txt_address_branch.getText());
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent1);
                }
                else{
                    Intent intent1 = new Intent(getApplicationContext(),LoginActivity.class);
                    String context = BranchLikeDetailsActivity.class.getSimpleName();
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
    private void init() {
        /*
         * Ánh xạ các id và khởi tạo 1 số biến
         */
        this.app_bar_branch_details     = findViewById(R.id.app_bar_branch_details);
        this.img_back_branch_details    = findViewById(R.id.img_back_branch_details);
        this.img_branch_details         = findViewById(R.id.img_branch_details);
        this.txt_name_branch_details    = findViewById(R.id.txt_name_branch_details);
        this.txt_address_branch         = findViewById(R.id.txt_address_branch);
        this.txt_open_time              = findViewById(R.id.txt_open_time);
        this.txt_price_branch           = findViewById(R.id.txt_price_branch);
        this.imgComment                 = findViewById(R.id.imgComment);
        this.txt_num_comment            = findViewById(R.id.txt_num_comment);
        setSupportActionBar(this.app_bar_branch_details);
        this.commentList                = new ArrayList<>();
        this.recyclerView               = findViewById(R.id.recycler_comment);
        /*
         */

        /*
         * Lấy thông tin món gửi từ BranchRecyclerViewAdapter và gán giá trị cho các thuộc tính
         */
        this.intent = getIntent();
        final String className = intent.getExtras().getString("class");
        String image = Objects.requireNonNull(intent.getExtras()).getString("BranchImage");
        String name = intent.getExtras().getString("BranchName");
        String address = intent.getExtras().getString("BranchAddress");
        String opentime = intent.getExtras().getString("BranchOpenTime");
        String price = intent.getExtras().getString("BranchPrice");
        branchCode = intent.getExtras().getString("BranchCode");
        idBranch = intent.getExtras().getInt("ID");
        actionlike = intent.getExtras().getInt("ActionLike");

        if (image != null) {
            Picasso.get().load(Config.getConfig().getPathLoadImgBranch().concat(image))
                    .into(this.img_branch_details);
        }
        this.txt_name_branch_details.setText(name);
        this.txt_address_branch.setText(address);
        this.txt_open_time.setText(opentime);
        this.txt_price_branch.setText(price);
        this.app_bar_branch_details.setTitle(name);
        /*
         */

        /*
         * Bắt sự kiện click cho image chuyển từ màn hình hiện tại về màn hình trước đó
         */
        this.img_back_branch_details.setOnClickListener(new View.OnClickListener() {
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
                    GeocodingLocation.getLatitude(txt_address_branch.getText().toString(),getApplicationContext())
                    ,GeocodingLocation.getLongitude(txt_address_branch.getText().toString(),getApplicationContext()));

            Intent intent2 = new Intent(getApplicationContext(),MapActivity.class);
            String context = BranchLikeDetailsActivity.class.getSimpleName();
            intent2.putExtra("context",context);
            intent2.putExtra("name",txt_name_branch_details.getText().toString());
            intent2.putExtra("address",txt_address_branch.getText().toString());
            intent2.putExtra("latitude",String.valueOf(point.getLatitude()));
            intent2.putExtra("longitude",String.valueOf(point.getLongitude()));
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent2);
        }
        else if(id == R.id.action_comment){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                Intent intent1 = new Intent(getApplicationContext(),CommentActivity.class);
                String context = BranchLikeDetailsActivity.class.getSimpleName();
                intent1.putExtra("context",context);
                intent1.putExtra("code",branchCode);
                intent1.putExtra("name",txt_name_branch_details.getText());
                intent1.putExtra("address",txt_address_branch.getText());
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }
            else{
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getApplicationContext());
                alertBuilder.setTitle("Thông báo");
                alertBuilder.setMessage("Bạn chưa đăng nhập. Vui lòng đăng nhập.");

                alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent1 = new Intent(getApplicationContext(),LoginActivity.class);
                        String context = BranchLikeDetailsActivity.class.getSimpleName();
                        intent1.putExtra("context",context);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                    }
                });
                alertBuilder.show();
            }
        }
        else if(id == R.id.action_dislike){
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, getURLBranch,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.trim().equals("success")){
                                Toast toast = Toast.makeText(getApplicationContext(),"Đã xóa "+ txt_name_branch_details.getText() +" khỏi danh sách yêu thích.",Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER,0,0);
                                toast.show();
                            }else{
                                Toast toast = Toast.makeText(getApplicationContext(),"Lỗi. Vui lòng kiểm tra lại.",Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER,0,0);
                                toast.show();
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
                    params.put("id", String.valueOf(idBranch));
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
