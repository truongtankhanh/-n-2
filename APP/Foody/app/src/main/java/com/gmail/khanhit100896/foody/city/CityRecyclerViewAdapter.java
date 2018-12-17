package com.gmail.khanhit100896.foody.city;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.khanhit100896.foody.R;
import com.gmail.khanhit100896.foody.config.Config;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CityRecyclerViewAdapter extends RecyclerView.Adapter<CityRecyclerViewAdapter.CityViewHolder> implements Filterable {

    /*
     * Khai báo biến cần thiết
     */
    private Context context;
    private List<City> cityList;
    private List<City> cityListFull;
    /*
     */

    /*
     * Hàm Constructor
     */
    CityRecyclerViewAdapter(Context context, List<City> cityList) {
        this.context = context;
        this.cityList = cityList;
        this.cityListFull = new ArrayList<>();
        this.cityListFull.addAll(this.cityList);
    }

    /*
     * Ánh xạ và khởi tạo viewholder
     */
    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(context).inflate(R.layout.cardview_item_city,parent,false);
        view.startAnimation(AnimationUtils.loadAnimation(context,R.anim.anim_recycler_view));
        return new CityViewHolder(view);
    }
    /*
     */

    @Override
    public void onBindViewHolder(@NonNull final CityViewHolder holder, int position) {

        holder.txt_name_city.setText(this.cityList.get(position).getCityName());
        Picasso.get().load(Config.getConfig().getPathLoadImg().concat(this.cityList.get(position).getCityImage()))
                .into(holder.img_city);

        /*
         * Tạo menu cho cardview
         */
        holder.iv_menu_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context wrapper = new ContextThemeWrapper(context, R.style.MyPopupMenu);
                PopupMenu popupMenu = new PopupMenu(wrapper,holder.iv_menu_city);
                popupMenu.inflate(R.menu.activity_details);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_like:
                                //context.startActivity(new Intent(Settings.ACTION_SETTINGS));
                                break;

                                default: break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        /*
         */
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    /*
     * Hàm lọc dữ liệu trên RecyclerView
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<City> list = new ArrayList<>();
                if(charSequence.toString().isEmpty()){
                    list.addAll(cityListFull);
                }
                else{
                    String filter = charSequence.toString().toLowerCase().trim();

                    for (City city : cityListFull){
                        if(city.getCityName().toLowerCase().contains(filter)){
                            list.add(city);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = list;

                return results;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                cityList.clear();
                cityList.addAll((Collection<? extends City>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    /*
     * Class holder - ánh xạ các thuộc tính
     */
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
