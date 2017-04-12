package com.freeletics.dilyana.freeletics.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.model.actions.Exercise;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentExerciseFragment extends Fragment {

    private Exercise exercise;
    private TextView rewardValue;
    private TextView equipmentValue;
    private TextView beatYourValue;
    private EditText repetitionsValue;
    private Button nextButton;
    private ImageView exerciseImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_current_exercise, container, false);

        rewardValue = (TextView) root.findViewById(R.id.reward_value);
        equipmentValue = (TextView) root.findViewById(R.id.equipment_value);
        beatYourValue = (TextView) root.findViewById(R.id.beat_your_value);
        repetitionsValue = (EditText) root.findViewById(R.id.repetitions_value);
        nextButton = (Button) root.findViewById(R.id.next_button);
        exerciseImage = (ImageView) root.findViewById(R.id.exercise_image);

        Bundle bundle = this.getArguments();
        Exercise exercise = null;
        if (bundle != null) {
           exercise = (Exercise) bundle.getSerializable("exercise");
            rewardValue.setText(exercise.getName().getPoints() +" Points");
            if(exercise.getEquipment() == null) {
                equipmentValue.setText("No Equipment");
            }
            else{
                equipmentValue.setText(exercise.getEquipment());
            }
            if(exercise.getPicture()!=0){
                exerciseImage.setImageResource(exercise.getPicture());
            }
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return root;
    }

}
