package com.example.antonio.marinaApp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.antonio.marinaApp.R;
import com.example.antonio.marinaApp.models.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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

            if (userArrayList.get(position).getBithdate()!=null)
            holder.tv_bithdate_user.setText(userArrayList.get(position).getBithdate());

            if (userArrayList.get(position).getFirst_name()!=null)
                holder.tv_name_user.setText(userArrayList.get(position).getFirst_name());

if (userArrayList.get(position).getProfile_image_url()!=null) {
    Glide.with(context)

            .load(userArrayList.get(position).getProfile_image_url())
            .into(holder.img_profile_user);
}
        }

        @Override
        public int getItemCount() {
            return userArrayList.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.tv_name_user)
            TextView tv_name_user;


            @BindView(R.id.tv_bithdate_user)
            TextView tv_bithdate_user;

            @BindView(R.id.img_profile_user)
            ImageView img_profile_user;
            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);

            }
        }


    }

