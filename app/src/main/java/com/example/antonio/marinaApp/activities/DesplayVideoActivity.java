package com.example.antonio.marinaApp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.antonio.marinaApp.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import static com.example.antonio.marinaApp.ulities.AppKeys.API_KEY;

public class DesplayVideoActivity extends YouTubeBaseActivity {

    Button button;
    private YouTubePlayerView view;
    private YouTubePlayer.OnInitializedListener listener;
    private String videoID;
    public static String _VIDEO_ID="VIDEO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desplay_video);
        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null)
        {
            videoID = (String) bundle.get(_VIDEO_ID);
            initPlayer(videoID);
        }
    }

    private void initPlayer(final String videoId){
view=findViewById(R.id.youtube_view);

        view.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(!b){

                    youTubePlayer.loadVideo(videoId);
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

                    youTubePlayer.play();
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            }
        });


    }
}
