package com.gmail.khanhit100896.foody.restaurant;

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

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewAdapter.RestaurantViewHolder> {

    private Context context;
    private List<Restaurant> restaurantList;

    public RestaurantRecyclerViewAdapter(Context context, List<Restaurant> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.cardview_item_restaurant,parent,false);

        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RestaurantViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txt_name_res.setText(this.restaurantList.get(position).getRes_name());
        Picasso.get().load(Config.getConfig().getPathLoadImgRes().concat(this.restaurantList.get(position).getImg_address()))
                .into(holder.img_res);

        holder.cardview_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,RestaurantBranchActivity.class);
                intent.putExtra("ResCode",restaurantList.get(position).getRes_code());
                intent.putExtra("ResName",restaurantList.get(position).getRes_name());
                intent.putExtra("ResImage",restaurantList.get(position).getImg_address());
                intent.putExtra("NumOfBranch",restaurantList.get(position).getNum_of_branch());
                context.startActivity(intent);
            }
        });

        holder.iv_menu_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context,holder.iv_menu_res);
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
        return this.restaurantList.size();
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder{
        ImageView img_res, iv_menu_res;
        TextView txt_name_res;

        CardView cardview_restaurant;

        RestaurantViewHolder(View itemView) {
            super(itemView);

            img_res = itemView.findViewById(R.id.img_res);
            iv_menu_res = itemView.findViewById(R.id.iv_menu_res);
            txt_name_res = itemView.findViewById(R.id.txt_name_res);
            cardview_restaurant = itemView.findViewById(R.id.cardview_restaurant);
        }
    }
}
