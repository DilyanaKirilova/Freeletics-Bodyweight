package com.freeletics.dilyana.freeletics;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import static com.freeletics.dilyana.freeletics.PlayerConfig.API_KEY;

public class VideoActivity extends YouTubeBaseActivity {

    private YouTubePlayerView youTubePlayerView;
    private Button btnStartVideo;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        btnStartVideo = (Button) findViewById(R.id.btn_play_video);
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo("aatr_2MstrI&list=RDJGwWNGJdvx8&index=14");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };


        btnStartVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize(API_KEY, onInitializedListener);
            }
        });
    }

}