package com.gmail.khanhit100896.foody.city;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.PopupMenu;
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

public class CityRecyclerViewAdapter extends RecyclerView.Adapter<CityRecyclerViewAdapter.CityViewHolder> {

    private Context context;
    private List<City> cityList;

    CityRecyclerViewAdapter(Context context, List<City> cityList) {
        this.context = context;
        this.cityList = cityList;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.cardview_item_city,parent,false);

        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CityViewHolder holder, int position) {
        holder.txt_name_city.setText(this.cityList.get(position).getCityName());
        Picasso.get().load(Config.getConfig().getPathLoadImg().concat(this.cityList.get(position).getCityImage()))
                .into(holder.img_city);

        holder.iv_menu_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context,holder.iv_menu_city);
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
        return cityList.size();
    }

    static class CityViewHolder extends RecyclerView.ViewHolder{
        ImageView img_city, iv_menu_city;
        TextView txt_name_city;

        CityViewHolder(View itemView) {
            super(itemView);

            img_city = itemView.findViewById(R.id.img_city);
            iv_menu_city = itemView.findViewById(R.id.iv_menu_city);
            txt_name_city = itemView.findViewById(R.id.txt_name_city);
        }
    }
}
