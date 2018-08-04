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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.antonio.marinaApp.R;
import com.example.antonio.marinaApp.activities.ShowDetailsUserActivity;
import com.example.antonio.marinaApp.models.User;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.antonio.marinaApp.activities.ShowDetailsUserActivity.USER_INFO;

/**
 * Created by antonio on 6/30/18.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {


    Context context;
    private ArrayList<User> userArrayList;
    private String userid;
    private String apiToken;

    public UserAdapter(ArrayList<User> userArrayList
            , Context context) {
        this.context = context;
        this.userArrayList = userArrayList;


    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (userArrayList.get(position).getBithdate() != null)
            holder.tv_bithdate_user.setText(userArrayList.get(position).getBithdate());

        if (userArrayList.get(position).getFirst_name() != null)
            holder.tv_name_user.setText(userArrayList.get(position).getFirst_name());

        if (userArrayList.get(position).getProfile_image_url() != null) {
            Glide.with(context)

                    .load(userArrayList.get(position).getProfile_image_url())
                    .into(holder.img_profile_user);
        }
        holder.container_row_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                Log.e("response_user>>>", gson.toJson(userArrayList.get(position)));
                Intent intent = new Intent(context, ShowDetailsUserActivity.class);
                intent.putExtra(USER_INFO, gson.toJson(userArrayList.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name_user)
        TextView tv_name_user;


        @BindView(R.id.tv_bithdate_user)
        TextView tv_bithdate_user;

        @BindView(R.id.img_profile_user)
        ImageView img_profile_user;

        @BindView(R.id.container_row_user)
        ConstraintLayout container_row_user;

        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}

