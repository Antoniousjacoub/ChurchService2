package com.example.antonio.marinaApp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.antonio.marinaApp.R;
import com.example.antonio.marinaApp.activities.BiblicalFactsDetailsActivity;
import com.example.antonio.marinaApp.activities.ShowDetailsUserActivity;
import com.example.antonio.marinaApp.models.biblicalFacts.Item;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.antonio.marinaApp.activities.ShowDetailsUserActivity.USER_INFO;

/**
 * Created by antonio on 8/21/18.
 */

public class BiblicalFactsAdapter  extends RecyclerView.Adapter<BiblicalFactsAdapter.ViewHolder> {


    Context context;
    private ArrayList<Item> itemArrayList;
    private String userid;
    private String apiToken;
    private final RequestOptions requestOptions;

    public BiblicalFactsAdapter(ArrayList<Item> itemArrayList
            , Context context) {
        this.context = context;
        this.itemArrayList = itemArrayList;

        requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.img_elsaleb);
        requestOptions.error(R.drawable.img_elsaleb);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_biblical_facts, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (itemArrayList.get(position).getFields().getTitle()!= null)
            holder.tv_title_biblical_facts.setText(itemArrayList.get(position).getFields().getTitle());

        if (itemArrayList.get(position).getFields().getImageUrl() != null) {
            Glide.with(context)
                    .setDefaultRequestOptions(requestOptions)
                    .load(itemArrayList.get(position).getFields().getImageUrl())
                    .into(holder.img_biblical_facts);
        }
        holder.container_item_biblical_facts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, BiblicalFactsDetailsActivity.class);
                intent.putExtra(BiblicalFactsDetailsActivity.IMAGE_URL,itemArrayList.get(position).getFields().getImageUrl());
                intent.putExtra(BiblicalFactsDetailsActivity.TITLE,itemArrayList.get(position).getFields().getTitle());
                intent.putExtra(BiblicalFactsDetailsActivity.TEXT_CONTENT,itemArrayList.get(position).getFields().getTopicdetails());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title_biblical_facts)
        TextView tv_title_biblical_facts;

        @BindView(R.id.img_biblical_facts)
        ImageView img_biblical_facts;

        @BindView(R.id.container_item_biblical_facts)
        LinearLayout container_item_biblical_facts;

        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}

