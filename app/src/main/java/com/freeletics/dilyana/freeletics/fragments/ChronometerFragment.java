package com.freeletics.dilyana.freeletics.fragments;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.model.actions.Action;
import com.freeletics.dilyana.freeletics.model.actions.Exercise;
import com.freeletics.dilyana.freeletics.model.actions.Workout;
import com.freeletics.dilyana.freeletics.model.users.User;
import com.freeletics.dilyana.freeletics.model.users.UsersManager;

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
    private static int counter;
    private Button finishExcercise;
    private long timeWhenStopped = 0;
    private long timeOfExercise;
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
        finishExcercise = (Button) root.findViewById(R.id.finish_button);

        UsersManager manager = UsersManager.getInstance();
        final User user = manager.getLoggedUser();


        timeOfExercise = SystemClock.elapsedRealtime() - chronometer.getBase();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                chronometer.start();
                showElapsedTime();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                chronometer.stop();
                showElapsedTime();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                timeWhenStopped=0;
                showElapsedTime();
            }
        });

        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counerTv.setText(String.valueOf(counter));
                counter++;
            }
        });

        finishExcercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
               //user.addFinishedExc();
                user.setLevel();
                Bundle bundle = getArguments();
                if(bundle!=null) {
                    Workout workout = (Workout) bundle.getSerializable("action");
                    user.addFinishedAction(workout);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    MyProfileFragment myProfileFragment = new MyProfileFragment();
                    myProfileFragment.setArguments(bundle);
                    ft.replace(R.id.fragment_container, myProfileFragment).commit();
                }
                Toast.makeText(getActivity(), "Your training time is: "+timeOfExercise, Toast.LENGTH_SHORT).show();
                timeOfExercise = 0;

            }
        });

        return root;
    }
    private void showElapsedTime() {
        long elapsedsecond = (SystemClock.elapsedRealtime() - chronometer.getBase())/1000;
        Toast.makeText(getActivity(), "Training seconds: " + elapsedsecond,
                Toast.LENGTH_SHORT).show();
    }

}
