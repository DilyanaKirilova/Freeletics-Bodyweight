package com.freeletics.dilyana.freeletics.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.freeletics.dilyana.freeletics.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {


    public VideoFragment() {
        // Required empty public constructor
    }

    private VideoView video;
   // private Button btnStartVideo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_video, container, false);



        video = (VideoView) root.findViewById(R.id.videoView);


        /*
            get the full YouTube url form - https://weibomiaopai.com/online-video-downloader/youtube
        */


        //1.
        Uri uri = Uri.parse("https://redirector.googlevideo.com/videoplayback?dur=263.267&ei=_a7wWNHtHdDE-wPTzayACQ&initcwndbps=7362500&expire=1492190045&pl=33&mime=video%2Fmp4&id=o-AOgexVc9TJ83TJAtFXdoBheKjTQTpVvZdew9w9Hoj2Ai&mn=sn-n4v7sn76&mm=31&ipbits=0&requiressl=yes&ip=2600%3A3c01%3A%3Af03c%3A91ff%3Afe24%3Ab564&ms=au&mv=m&mt=1492168344&ratebypass=yes&upn=5-2FhhQNIPU&itag=22&beids=%5B9466593%5D&lmt=1485858637260028&key=yt6&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&source=youtube&signature=C477C99E17B03B018627FD49A7DA234D776096DD.81C28CE5029D272DE727DE8C98339797919F2CF7");
        video.setVideoURI(uri);

        // prograss dialog
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Buffering video please wait...");
        pd.show();

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //close the progress dialog when buffering is done
                pd.dismiss();
            }
        });

        //2.
        // create an object of media controller
        MediaController mediaController = new MediaController(getActivity());
        // set media controller object for a video view
        video.setMediaController(mediaController);

        //3.
        video.requestFocus();
        video.start();


        return root;
    }

}
