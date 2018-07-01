package com.example.antonio.marinaApp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.antonio.marinaApp.R;
import com.example.antonio.marinaApp.adapters.UserAdapter;
import com.example.antonio.marinaApp.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.antonio.marinaApp.ulities.Helpers.onDoIntentTo;

public class UserInformationActivity extends AppCompatActivity {

   private String TAG=UserInformationActivity.class.getSimpleName();
    @BindView(R.id.container_add_5adem)
    LinearLayout container_add_5adem;

    @BindView(R.id.rv_all_user)
    RecyclerView rv_all_user;
    Unbinder unbinder;
    private ArrayList<User> userArrayList=new ArrayList<>();
    User user =new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        unbinder= ButterKnife.bind(this);
        getDataFromFirebase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataFromFirebase();

    }

    private void getDataFromFirebase(){

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference artpicsRef = rootRef.child("user");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                userArrayList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    User user = ds.getValue(User.class);

//                    user.setFirst_name(ds.child("first_name").getValue(String.class));
//                    user.setMidle_name(ds.child("midle_name").getValue(String.class));
//                    user.setLast_name(ds.child("last_name").getValue(String.class));
//                    user.setGender(ds.child("gender").getValue(String.class));
//                    user.setBithdate(ds.child("bithdate").getValue(String.class));
                    userArrayList.add(user);

                }

                if (!userArrayList.isEmpty()||userArrayList!=null) {
                    UserAdapter adapter = new UserAdapter(userArrayList, UserInformationActivity.this);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    rv_all_user.setLayoutManager(mLayoutManager);
                    rv_all_user.setItemAnimator(new DefaultItemAnimator());
                    rv_all_user.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        artpicsRef.addListenerForSingleValueEvent(eventListener);

    }
    @OnClick({R.id.container_add_5adem})
    void aVoid(View view){
        switch (view.getId()){
            case R.id.container_add_5adem:
                onDoIntentTo(this,UserInfoDetailsActivity.class);
                break;


        }


    }
}
