package com.freeletics.dilyana.freeletics.fragments;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.freeletics.dilyana.freeletics.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountDownFragment extends Fragment {



    private TextView tvCountDown;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_count_down, container, false);
        tvCountDown = (TextView) root.findViewById(R.id.tv_count_down);

        MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), R.raw.sound_file_down_counting_2);
        mediaPlayer.start();
        new CountDownTimer(7000, 1000) {

            int count = 5;
            public void onTick(long millisUntilFinished) {
                tvCountDown.setText( count-- +"");

                if(tvCountDown.getText().toString().equals("0")){
                    tvCountDown.setText("GO!");
                }
            }

            public void onFinish() {

                /*
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                */
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ChronometerFragment chronometerFragment = new ChronometerFragment();
                chronometerFragment.setArguments(getArguments());
                fragmentTransaction.replace(R.id.fragment_container, chronometerFragment).commit();
            }

        }.start();

        return root;
    }
}
