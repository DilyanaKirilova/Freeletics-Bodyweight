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
import com.freeletics.dilyana.freeletics.model.actions.Action;

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
        View root = inflater.inflate(R.layout.fragment_video, container, false);
        video = (VideoView) root.findViewById(R.id.videoView);
        /*
             get the full YouTube url form - https://weibomiaopai.com/online-video-downloader/youtube

 	    */

        Uri uri = Uri.parse("https://redirector.googlevideo.com/videoplayback?ratebypass=yes&mv=m&source=youtube&ms=au&mime=video%2Fmp4&dur=56.749&pl=19&initcwndbps=6028750&id=o-AFGGZEJQDUvxpQTSJIZbVLCHyYRR529ggL7UIVLmz14c&itag=18&mn=sn-aigs6n7y&mm=31&ip=78.157.200.133&expire=1492502731&upn=kYquerpK8Z8&mt=1492481061&ei=a3T1WMjMIOmAoQOLrJuADg&lmt=1427704337058587&ipbits=0&key=yt6&clen=2498618&sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&gir=yes&requiressl=yes&signature=943689F63F15C0DD1DB75C9698697E5CA5FA4773.587A9DA8AC8376BE8AEA3E748E4A878D86A9C4EA");
        video.setVideoURI(uri);

        MediaController mediaController = new MediaController(getActivity());
        video.setMediaController(mediaController);

        video.start();

        return root;
    }

}
