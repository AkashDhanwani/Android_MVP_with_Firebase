package com.akash.rohonfancy.view_presenter.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.akash.rohonfancy.view_presenter.about_us.AboutUs;
import com.akash.rohonfancy.view_presenter.productinfo.ProductInfo;
import com.akash.rohonfancy.R;
import com.akash.rohonfancy.model.ListItem;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKASH on 3/12/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ListItem> itemList;
    private Context context;
   // int lastPosition = -1;

    public Adapter(List<ListItem> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem listItem = itemList.get(position);

        holder.imagePath = listItem.getImagePath();

        Glide.with(context).load(holder.imagePath.get(0)).into(holder.imageView);
        holder.textView.setText(listItem.getName());
        holder.tvPrice.setText("\u20B9 "+listItem.getPrice());

        holder.size = listItem.getSize();
        holder.nameproduct = listItem.getName();
        holder.price = listItem.getPrice();
        holder.desc = listItem.getDescription();

//        Animation animation = AnimationUtils.loadAnimation(context,
//                (position > lastPosition) ? R.anim.up_from_bottom
//                        : R.anim.down_from_top);
//        holder.itemView.startAnimation(animation);
//        lastPosition = position;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public String nameproduct, price, desc, size;
        public List<String> imagePath;

        public ImageView imageView;
        public TextView textView, tvPrice;
        public Button btnrflogo;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivcard);
            textView = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            btnrflogo = itemView.findViewById(R.id.btnrflogo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("imagePath", (ArrayList<String>) imagePath);
                    bundle.putString("size", size);
                    bundle.putString("namepro", nameproduct);
                    bundle.putString("price", price);
                    bundle.putString("desc", desc);
                    Intent intent = new Intent(context, ProductInfo.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

            btnrflogo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, AboutUs.class);
                    context.startActivity(intent);
                }
            });
        }
    }

}
