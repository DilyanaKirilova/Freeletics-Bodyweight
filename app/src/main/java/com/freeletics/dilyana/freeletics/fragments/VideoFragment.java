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
        /*
             get the full YouTube url form - https://weibomiaopai.com/online-video-downloader/youtube

 	    */

        if(getArguments() != null && getArguments().getString("url") != null) {

            String url = getArguments().getString("url");

            video = (VideoView) root.findViewById(R.id.videoView);

            Uri uri = Uri.parse(url);
            //Uri uri = Uri.parse("https://redirector.googlevideo.com/videoplayback?requiressl=yes&ms=au&ei=qeb0WNe-D4Oy4wK9rJaYBw&mv=m&mt=1492444764&mn=sn-a5mekn7y&clen=2498618&ratebypass=yes&gir=yes&id=o-ACRGkWKc5kwghy5frTM2upIwNzP4oj1AQ_P6oNrADfYW&initcwndbps=943750&source=youtube&expire=1492466441&dur=56.749&lmt=1427704337058587&ip=2001%3A19f0%3A7001%3Ad32%3A5400%3Aff%3Afe58%3A19e7&key=yt6&upn=uRJUxGye_QI&ipbits=0&mm=31&itag=18&pl=48&sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&mime=video%2Fmp4&signature=22353C4304A9A000AE81EAB810834C1632741334.9F5040988E48640F45BD08CC1726CCF2A1E8B6D4");

            video.setVideoURI(uri);

            MediaController mediaController = new MediaController(getActivity());
            video.setMediaController(mediaController);

            video.start();
        }

        return root;
    }

}
