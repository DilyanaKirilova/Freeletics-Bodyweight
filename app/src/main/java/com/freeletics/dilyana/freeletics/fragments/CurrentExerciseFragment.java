package com.freeletics.dilyana.freeletics.fragments;


import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.freeletics.dilyana.freeletics.MainActivity;
import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.model.actions.Action;
import com.freeletics.dilyana.freeletics.model.actions.Exercise;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentExerciseFragment extends Fragment {

    private TextView rewardValue;
    private TextView equipmentValue;
    private TextView beatYourValue;
    private Spinner spinnerRepetitions;
    private Button nextButton;
    private ImageView exerciseImage;
    private Action action;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_current_exercise, container, false);

        rewardValue = (TextView) root.findViewById(R.id.reward_value);
        equipmentValue = (TextView) root.findViewById(R.id.equipment_value);
        beatYourValue = (TextView) root.findViewById(R.id.beat_your_value);
        spinnerRepetitions = (Spinner) root.findViewById(R.id.repetitions_value);
        nextButton = (Button) root.findViewById(R.id.next_button);
        exerciseImage = (ImageView) root.findViewById(R.id.exercise_image);

        final Bundle bundle = this.getArguments();
        action = null;
        if (bundle != null) {
            action = (Action) bundle.getSerializable("action");
            rewardValue.setText(action.getPoints() + " Points");
            equipmentValue.setText(action.getEquipment());

            ArrayAdapter adapterRep = ArrayAdapter.createFromResource(getActivity(), action.getRepetitionsList(), R.layout.support_simple_spinner_dropdown_item);
            spinnerRepetitions.setAdapter(adapterRep);

            spinnerRepetitions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String rep = parent.getItemAtPosition(position).toString();
                    action.setRepetitions(Integer.parseInt(rep));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        nextButton.setText("Do your first " + action.getName().toString());

        final Action finalAction = action;
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ActionFragment actionFragment = new ActionFragment();
                actionFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, actionFragment).commit();
            }
        });


        return root;
    }
}
