package com.gmail.khanhit100896.foody.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.Config;
import com.gmail.khanhit100896.foody.config.RoundedTransformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class CommentActivity extends AppCompatActivity {

    protected AppBarLayout appBarLayout;
    protected Toolbar app_bar_branch_details;
    protected ImageView img_back_branch_details;

    protected TextView txt_name_branch;
    protected TextView txt_address_branch;
    protected ImageView img_user, img_dang;
    protected TextView txt_user_name, txt_create_time;
    protected TextView txtDang;
    protected EditText edtComment;
    protected String getUrl = Config.getConfig().getPathInsertComment();

    protected String context;
    protected String name;
    protected String username;
    protected String email;
    protected String photoUrl;
    protected String code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        // Gọi hàm ánh xạ
        init();
    }

    private void init() {
        this.app_bar_branch_details     = findViewById(R.id.app_bar_branch_details);
        this.img_back_branch_details    = findViewById(R.id.img_back_branch_details);
        setSupportActionBar(this.app_bar_branch_details);
        this.txt_name_branch            = findViewById(R.id.txt_name_branch);
        this.txt_address_branch         = findViewById(R.id.txt_address_branch);
        this.img_user                   = findViewById(R.id.img_user);
        this.txt_user_name              = findViewById(R.id.txt_user_name);
        this.txt_create_time            = findViewById(R.id.txt_create_time);
        this.img_dang                   = findViewById(R.id.img_dang);
        this.edtComment                 = findViewById(R.id.edtComment);

        this.txt_create_time.setText(Config.getConfig().getCurrentDate());

        Intent intent = getIntent();
        context = intent.getExtras().getString("context");
        name = intent.getExtras().getString("name");
        String address = intent.getExtras().getString("address");
        code = intent.getExtras().getString("code");

        this.app_bar_branch_details.setTitle("Viết bình luận");
        this.txt_name_branch.setText(name);
        this.txt_address_branch.setText(address);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            username = user.getDisplayName();
            email = user.getEmail();
            photoUrl = user.getPhotoUrl().toString();
            this.txt_user_name.setText(username);
            Picasso.get().load(photoUrl)
                    .transform(new RoundedTransformation(60, 0))
                    .into(this.img_user);
        }

        this.img_back_branch_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(getApplicationContext(), Class.forName(context).getClass()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });

        this.img_dang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtComment.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else {
                    addNewComment(getUrl);
                }
            }
        });
    }

    public void addNewComment(String getUrl) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
//                            try {
//                                startActivity(new Intent(getApplicationContext(), Class.forName(context).getClass()));
//                            } catch (ClassNotFoundException e) {
//                                e.printStackTrace();
//                            }
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Đăng bài không thành công.",Toast.LENGTH_SHORT).show();
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
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("userName",username);
                params.put("email",email);
                params.put("photoUrl",photoUrl);
                params.put("foodCode",code);
                params.put("comment", edtComment.getText().toString());
                params.put("createTime",Config.getConfig().getCurrentDate());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
