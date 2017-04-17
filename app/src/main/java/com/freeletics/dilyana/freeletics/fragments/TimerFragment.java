package com.freeletics.dilyana.freeletics.fragments;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freeletics.dilyana.freeletics.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends Fragment {


    private TextView timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_timer, container, false);

        timer = (TextView) root.findViewById(R.id.timer);

        MyCount count = new MyCount(5000,1000);
        count.start();
        return root;
    }


    public class MyCount extends CountDownTimer {

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            timer.setText("Done");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            timer.setText("Left: " + millisUntilFinished/1000);

        }

    }
}
