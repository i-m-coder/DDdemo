package com.dddemo.sumanthmadala.androidapp.dddemo.ui.adaptors;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dddemo.sumanthmadala.androidapp.dddemo.R;
import com.dddemo.sumanthmadala.androidapp.dddemo.model.Restaurant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sumanthmadala on 3/26/17.
 */

public class RestaurantsAdaptor extends RecyclerView.Adapter<RestaurantsAdaptor.RestaurantViewHolder>{

    private Context appContext;
    private List<Restaurant> items;
    private final String TAG = this.getClass().getSimpleName();
    public RestaurantsAdaptor(Context context,List<Restaurant> items) {
        appContext = context;
        this.items = items;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list_item,parent,false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RestaurantViewHolder holder, int position) {
        holder.logo_progress.setVisibility(View.VISIBLE);
        com.squareup.picasso.Picasso.with(appContext).load(items.get(position).logo_url)
                .into(holder.logo, new com.squareup.picasso.Callback() {


            @Override
            public void onSuccess() {
                holder.logo_progress.setVisibility(View.GONE);
                holder.logo.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError() {
                holder.logo_progress.setVisibility(View.GONE);
                holder.logo.setVisibility(View.GONE);
            }
        });
        holder.name.setText(items.get(position).name);
        log("name"+items.get(position).name);
        holder.description.setText(items.get(position).description);
        holder.travelTime.setText(items.get(position).travelTime);
        holder.address.setText(items.get(position).address);
        log("address"+items.get(position).address);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    public class RestaurantViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.restaurant_title)
        TextView name;
        @BindView(R.id.restaurant_desc)
        TextView description;
        @BindView(R.id.traveltime)
        TextView travelTime;
        @BindView(R.id.restaurant_address)
        TextView address;
        @BindView(R.id.favorite_icon)
        ImageButton favButton;
        @BindView(R.id.logo_image)
        ImageView logo;
        @BindView(R.id.logo_progress)
        ProgressBar logo_progress;

        public RestaurantViewHolder(View itemView) {
             super(itemView);
             ButterKnife.bind(this,itemView);
            favButton.setOnClickListener(this);
         }

        @Override
        public void onClick(View v) {

        }
    }

    private void log(String msg){
        Log.v(TAG,msg);
    }
}
