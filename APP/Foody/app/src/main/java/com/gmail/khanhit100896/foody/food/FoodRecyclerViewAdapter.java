package com.gmail.khanhit100896.foody.food;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.Config;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodRecyclerViewAdapter extends RecyclerView.Adapter<FoodRecyclerViewAdapter.FoodViewHolder> {

    private Context context;
    private List<Food> foodList;

    public FoodRecyclerViewAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.cardview_item_food,parent,false);

        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txt_name_food.setText(this.foodList.get(position).getFood_name());
        Picasso.get().load(Config.getConfig().getPathLoadImgFood().concat(this.foodList.get(position).getImg_address()))
                .into(holder.img_food);

        // set click listener
        holder.cardview_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,FoodDetailsActivity.class);
                intent.putExtra("FoodName",foodList.get(position).getFood_name());
                intent.putExtra("FoodImage",foodList.get(position).getImg_address());
                intent.putExtra("FoodAddress",foodList.get(position).getFood_address());
                intent.putExtra("FoodPrice",foodList.get(position).getFood_price());
                intent.putExtra("FoodKind",foodList.get(position).getKind_code());
                context.startActivity(intent);
            }
        });

        holder.iv_menu_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context,holder.iv_menu_food);
                popupMenu.inflate(R.menu.main);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_settings:
                                context.startActivity(new Intent(Settings.ACTION_SETTINGS));
                                break;

                            default: break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.foodList.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder{
        ImageView img_food, iv_menu_food;
        TextView txt_name_food;

        CardView cardview_food;

        FoodViewHolder(View itemView) {
            super(itemView);

            img_food        = itemView.findViewById(R.id.img_food);
            iv_menu_food    = itemView.findViewById(R.id.iv_menu_food);
            txt_name_food   = itemView.findViewById(R.id.txt_name_food);
            cardview_food   = itemView.findViewById(R.id.cardview_food);
        }
    }
}
