package com.gmail.khanhit100896.foody.branch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.Config;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class BranchDetailsActivity extends AppCompatActivity {

    protected ImageView img_branch_details;
    protected TextView txt_name_branch_details;
    protected TextView txt_address_branch;
    protected TextView txt_open_time;
    protected TextView txt_price_branch;

    protected Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_details);

        init();
    }

    private void init() {
        this.img_branch_details         = findViewById(R.id.img_branch_details);
        this.txt_name_branch_details    = findViewById(R.id.txt_name_branch_details);
        this.txt_address_branch         = findViewById(R.id.txt_address_branch);
        this.txt_open_time              = findViewById(R.id.txt_open_time);
        this.txt_price_branch           = findViewById(R.id.txt_price_branch);

        this.intent = getIntent();
        String image = Objects.requireNonNull(intent.getExtras()).getString("BranchImage");
        String name = intent.getExtras().getString("BranchName");
        String address = intent.getExtras().getString("BranchAddress");
        String opentime = intent.getExtras().getString("BranchOpenTime");
        String price = intent.getExtras().getString("BranchPrice");

        if (image != null) {
            Picasso.get().load(Config.getConfig().getPathLoadImgBranch().concat(image))
                    .placeholder(R.drawable.search_mages_icon).into(this.img_branch_details);
        }
        this.txt_name_branch_details.setText(name);
        this.txt_address_branch.setText(address);
        this.txt_open_time.setText(opentime);
        this.txt_price_branch.setText(price);
    }
}
