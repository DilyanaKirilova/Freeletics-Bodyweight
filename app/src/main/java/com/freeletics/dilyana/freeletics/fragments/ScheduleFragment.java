package com.freeletics.dilyana.freeletics.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.adapters.ActionAdapter;
import com.freeletics.dilyana.freeletics.model.actions.Action;
import com.freeletics.dilyana.freeletics.model.users.UsersManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment{

    private RecyclerView dayWorkouts;
    private ActionAdapter actionAdapter;
    private FloatingActionButton floatingActionButton;
    private int day;
    private List<Action> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_exercise, container, false);
        dayWorkouts = (RecyclerView) root.findViewById(R.id.recycler_view_exercise);

        if(getArguments() != null){
            Bundle bundle = getArguments();
            if (bundle.getInt("day", 0) != 0){

                day = (int) bundle.getInt("day");

                actionAdapter = new ActionAdapter((AppCompatActivity)getActivity(), UsersManager.getInstance().getLoggedUser().getSchedule(day));
                dayWorkouts.setAdapter(actionAdapter);
                actionAdapter.notifyDataSetChanged();
                dayWorkouts.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        }

        floatingActionButton = (FloatingActionButton) root.findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(list != null && list.size() >= 5){
                    Toast.makeText(getActivity(), "You should not do more than 5 actions at one day", Toast.LENGTH_SHORT).show();
                    return;
                }

                AddEventFragment addEventFragment = new AddEventFragment();
                if(getArguments() != null) {
                    addEventFragment.setArguments(getArguments());
                }
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, addEventFragment).commit();
            }
        });


        return root;
    }
}
