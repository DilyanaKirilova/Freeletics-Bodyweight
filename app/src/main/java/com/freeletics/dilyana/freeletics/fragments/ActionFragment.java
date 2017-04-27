package com.freeletics.dilyana.freeletics.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.adapters.ActionAdapter;
import com.freeletics.dilyana.freeletics.model.actions.Action;


public class ActionFragment extends Fragment {

    public ActionFragment() {
        // Required empty public constructor
    }

    private Button btnDoAction;
    private Action action = null;
    private TextView tvNumOfRepetitions;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_action, container, false);

        if(getArguments() != null && getArguments().getSerializable("action") != null){
            action = (Action) getArguments().getSerializable("action");
        }


        btnDoAction = (Button) root.findViewById(R.id.btn_fa_do_action);
        tvNumOfRepetitions = (TextView) root.findViewById(R.id.tv_fa_num_of_repetitions);
        recyclerView = (RecyclerView) root.findViewById(R.id.actions);

        if(action.getExercises() != null) {
            ActionAdapter actionAdapter = new ActionAdapter((AppCompatActivity) getActivity(), action.getExercises());
            recyclerView.setAdapter(actionAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        }
        tvNumOfRepetitions.setText(action.getRepetitions() + "");


        btnDoAction.setText("Do  " + action.getCategory());
        btnDoAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putSerializable("action", action);
                CountDownFragment countDownFragment = new CountDownFragment();
                countDownFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, countDownFragment).addToBackStack("action").commit();


            }
        });

        return root;
    }
}
