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
import android.widget.Spinner;

import com.freeletics.dilyana.freeletics.HomeActivity;
import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.data_base.DBManager;
import com.freeletics.dilyana.freeletics.model.users.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends Fragment {

    private Spinner spGender;
    private Spinner spAge;
    private Spinner spWeight;
    private Spinner spHeight;


    // user data

    private String gender;
    private int age;
    private int weight;
    private int height;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_info_user, container, false);

        spGender = (Spinner) root.findViewById(R.id.spinner_gender);
        spAge = (Spinner) root.findViewById(R.id.spinner_age);
        spWeight = (Spinner) root.findViewById(R.id.spinner_weight);
        spHeight = (Spinner) root.findViewById(R.id.spinner_height);


        ArrayAdapter adapterGender = ArrayAdapter.createFromResource(getActivity(), R.array.gender, R.layout.support_simple_spinner_dropdown_item);
        spGender.setAdapter(adapterGender);

        ArrayAdapter adapterAge = ArrayAdapter.createFromResource(getActivity(), R.array.age, R.layout.support_simple_spinner_dropdown_item);
        spAge.setAdapter(adapterAge);

        ArrayAdapter adapterWeight = ArrayAdapter.createFromResource(getActivity(), R.array.weight, R.layout.support_simple_spinner_dropdown_item);
        spWeight.setAdapter(adapterWeight);

        ArrayAdapter adapterHeight = ArrayAdapter.createFromResource(getActivity(), R.array.height, R.layout.support_simple_spinner_dropdown_item);
        spHeight.setAdapter(adapterHeight);

        spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                age = Integer.parseInt((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spWeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                weight = Integer.parseInt((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spHeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                height = Integer.parseInt((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button btnSave = (Button) root.findViewById(R.id.btn_fiu_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putString("gender", gender);
                bundle.putInt("age", age);
                bundle.putInt("weight", weight);
                bundle.putInt("height", height);

                RegisterFragment registerFragment = new RegisterFragment();
                registerFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.activity_main, registerFragment).addToBackStack("info_user").commit();
            }
        });
        return root;
    }
}
