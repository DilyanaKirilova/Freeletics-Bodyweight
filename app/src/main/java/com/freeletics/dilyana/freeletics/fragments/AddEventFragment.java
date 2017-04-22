package com.freeletics.dilyana.freeletics.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.dialog_fragments.TimePickerFragment;
import com.freeletics.dilyana.freeletics.model.actions.Action;
import com.freeletics.dilyana.freeletics.model.actions.ActionsManager;
import com.freeletics.dilyana.freeletics.model.actions.Exercise;
import com.freeletics.dilyana.freeletics.model.actions.Workout;
import com.freeletics.dilyana.freeletics.model.users.User;
import com.freeletics.dilyana.freeletics.model.users.UsersManager;

import java.util.ArrayList;
import java.util.List;


public class AddEventFragment extends Fragment {

    private Spinner spinnerAction;
    private Spinner spinnerRepetitions;
    private Button btnSetTime;
    private TextView tvTime;
    private Button btnSave;

    private ArrayAdapter adapterRep;
    private ArrayAdapter adapterActions;

    private String time;
    private String repetitions;

    private User.Day day;

    private Action action;
    private ActionsManager.ActionName actionName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_event, container, false);

        if (getArguments() != null) {
            Bundle bundle = getArguments();
            if (bundle.getSerializable("day") != null) {
                day = (User.Day) bundle.getSerializable("day");
            }
        }

        spinnerAction = (Spinner) root.findViewById(R.id.spinner_action);
        spinnerRepetitions = (Spinner) root.findViewById(R.id.spinner_repetitions);
        btnSetTime = (Button) root.findViewById(R.id.btn_fae_time);
        btnSave = (Button) root.findViewById(R.id.btn_fae_save);
        tvTime = (TextView) root.findViewById(R.id.tv_fae_time);

        final List<String> actionsArray = new ArrayList<>();
        for (Workout.WorkoutName name : Workout.WorkoutName.values()) {
            actionsArray.add(name.toString());
        }
        for (Exercise.ExerciseName name : Exercise.ExerciseName.values()) {
            actionsArray.add(name.toString());
        }

        adapterRep = ArrayAdapter.createFromResource(getActivity(), R.array.workout_repetitions, R.layout.support_simple_spinner_dropdown_item);
        spinnerRepetitions.setAdapter(adapterRep);

        adapterActions = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, actionsArray);
        adapterActions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAction.setAdapter(adapterActions);

        spinnerAction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position >= Workout.WorkoutName.values().length) {
                    adapterRep = ArrayAdapter.createFromResource(getActivity(), R.array.exercise_repetitions, R.layout.support_simple_spinner_dropdown_item);
                    spinnerRepetitions.setAdapter(adapterRep);
                    actionName = StringToEnum(parent.getItemAtPosition(position).toString());
                    action = new Exercise(actionName);
                } else {
                    adapterRep = ArrayAdapter.createFromResource(getActivity(), R.array.workout_repetitions, R.layout.support_simple_spinner_dropdown_item);
                    spinnerRepetitions.setAdapter(adapterRep);
                    actionName = StringToEnum(parent.getItemAtPosition(position).toString());
                    action = new Workout(actionName);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerRepetitions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                repetitions = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                time = tvTime.getText().toString();
                if (time == null || time.isEmpty()) {
                    tvTime.setError("Please, choose time");
                    tvTime.requestFocus();
                    return;
                }

                action.setTime(time);
                action.setRepetitions(Integer.parseInt(repetitions));
                action.setDay(day);

                ScheduleFragment scheduleFragment = new ScheduleFragment();
                scheduleFragment.setArguments(getArguments());

                UsersManager.getInstance().getLoggedUser().addAction(action);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, scheduleFragment).commit();
            }
        });

        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
            }
        });


        return root;
    }

    public ActionsManager.ActionName StringToEnum(String nameStr){

        ActionsManager.ActionName actionName = null;
        for(Exercise.ExerciseName name : Exercise.ExerciseName.values()){

            if(nameStr.equals(name.toString())){
                actionName = name;
                break;
            }
        }

        for(Workout.WorkoutName name : Workout.WorkoutName.values()){

            if(nameStr.equals(name.toString())){
                actionName = name;
                break;
            }
        }

        return actionName;
    }
}
