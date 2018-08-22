package com.example.antonio.marinaApp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.codewaves.youtubethumbnailview.ThumbnailLoader;
import com.codewaves.youtubethumbnailview.ThumbnailView;
import com.codewaves.youtubethumbnailview.downloader.OembedVideoInfoDownloader;
import com.example.antonio.marinaApp.R;
import com.example.antonio.marinaApp.activities.DesplayVideoActivity;
import com.example.antonio.marinaApp.models.biblicalFacts.Item;
import com.example.antonio.marinaApp.ulities.Helpers;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.antonio.marinaApp.activities.DesplayVideoActivity._VIDEO_ID;

/**
 * Created by antonio on 8/21/18.
 */

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {


    Context context;
    private ArrayList<Item> itemArrayList;
    private String userid;
    private String apiToken;
    private final RequestOptions requestOptions;

    public VideosAdapter(ArrayList<Item> itemArrayList
            , Context context) {
        this.context = context;
        this.itemArrayList = itemArrayList;

        requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.img_elsaleb);
        requestOptions.error(R.drawable.img_elsaleb);
        ThumbnailLoader.initialize()
                .setVideoInfoDownloader(new OembedVideoInfoDownloader());

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        if (itemArrayList.get(position).getFields().getTitle()!= null)
            holder.tv_title_biblical_facts.setText(itemArrayList.get(position).getFields().getTitle());


        if (itemArrayList.get(position).getFields().getImageUrl()!=null){
        final String videoID = Helpers.getVideoId(itemArrayList.get(position).getFields().getImageUrl());
        assert holder.thumbnail != null;
        holder.thumbnail.loadThumbnail(itemArrayList.get(position).getFields().getImageUrl());

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, DesplayVideoActivity.class);
                intent.putExtra(_VIDEO_ID,videoID);
                context.startActivity(intent);
            }
        });
        holder.container_item_biblical_facts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, DesplayVideoActivity.class);
                intent.putExtra(_VIDEO_ID,videoID);
                context.startActivity(intent);
            }
        });
    }

    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title_biblical_facts)
        TextView tv_title_biblical_facts;

        @BindView(R.id.thumbnail)
        ThumbnailView thumbnail;

        @BindView(R.id.container_item_biblical_facts)
        LinearLayout container_item_biblical_facts;

        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}