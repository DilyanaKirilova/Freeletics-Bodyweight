package com.freeletics.dilyana.freeletics.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.freeletics.dilyana.freeletics.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoInfoFragment extends Fragment {


    public VideoInfoFragment() {
        // Required empty public constructor
    }


    ImageButton btnImg;
    TextView tvActionName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_video_info, container, false);

        btnImg = (ImageButton) root.findViewById(R.id.ib_fvi_img);
        tvActionName = (TextView) root.findViewById(R.id.tv_fvi_action_name);

        if(getArguments() != null){

            Bundle bundle = getArguments();
            final String url = bundle.getString("url");
            String name = bundle.getString("name");

            tvActionName.setText(name);

            btnImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    VideoFragment videoFragment = new VideoFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("url", url);
                    videoFragment.setArguments(bundle);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, videoFragment).commit();
                }
            });
        }

        return root;
    }

}
