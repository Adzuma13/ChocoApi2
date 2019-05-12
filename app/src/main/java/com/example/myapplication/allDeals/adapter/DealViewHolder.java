package com.example.myapplication.allDeals.adapter;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.allDeals.rest.entities.Deal;


public class DealViewHolder extends RecyclerView.ViewHolder {
    private TextView price;
    private TextView bought;
    private TextView rate;
    private TextView shortTitle;
    private TextView titleRV;
    private ImageView dealImage;

    public DealViewHolder(@NonNull View itemView) {
        super(itemView);
        this.price = itemView.findViewById(R.id.price);
        this.bought = itemView.findViewById(R.id.bought);
        this.rate = itemView.findViewById(R.id.rate);
        this.shortTitle = itemView.findViewById(R.id.title_short);
        this.titleRV = itemView.findViewById(R.id.titleRV);
        this.dealImage = itemView.findViewById(R.id.dealImage);
    }


    public void bind(Deal deal){
        String priceHolder = itemView.getResources().getString(R.string.price);

        price.setText(String.format(priceHolder, deal.getPrice()));
        titleRV.setText(deal.getTitle());
        bought.setText(String.valueOf(deal.getBought()));
        rate.setText(String.valueOf(deal.getReviews_rate()));
        shortTitle.setText(deal.getTitle_short());

        if(deal.getDeal_kind().equals("special")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                dealImage.setForeground(null);
            }
            shortTitle.setVisibility(View.GONE);
            Glide.with(itemView)
                    .load(deal.getImage_url())
                    .placeholder(R.drawable.place_holder)
                    .into(dealImage);
        } else{
            shortTitle.setVisibility(View.VISIBLE);
            Glide.with(itemView)
                    .load(deal.getImage_url_wide())
                    .placeholder(R.drawable.place_holder)
                    .into(dealImage);
        }

    }
}
