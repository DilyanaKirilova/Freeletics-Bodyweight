package com.freeletics.dilyana.freeletics.fragments;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.freeletics.dilyana.freeletics.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChronometerFragment extends Fragment {


    private Chronometer chronometer;
    private Button start;
    private Button stop;
    private Button restart;
    private Button count;
    private TextView counerTv;
    private static int counter = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_chronometer, container, false);

        counter=1;
        chronometer = (Chronometer) root.findViewById(R.id.chronometer);
        start = (Button) root.findViewById(R.id.start_button);
        stop = (Button) root.findViewById(R.id.stop_button);
        restart = (Button) root.findViewById(R.id.restart_button);
        count = (Button) root.findViewById(R.id.count_button);
        counerTv = (TextView) root.findViewById(R.id.counter_tv);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
            }
        });

        count.setOnClickListener(new View.OnClickListener() {
             //int i = 1;
            @Override
            public void onClick(View v) {
                counerTv.setText(String.valueOf(counter));
                counter++;
            }
        });

        return root;
    }

}
