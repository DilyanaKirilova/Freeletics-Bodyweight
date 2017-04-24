package com.freeletics.dilyana.freeletics.dialog_fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.fragments.ScheduleFragment;
import com.freeletics.dilyana.freeletics.model.actions.Action;
import com.freeletics.dilyana.freeletics.model.users.UsersManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerificationFragment extends DialogFragment {


    private Button btnYes;
    private Button btnNo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_verification, container, false);

        btnYes = (Button) root.findViewById(R.id.btn_fv_yes);
        btnNo = (Button) root.findViewById(R.id.btn_fv_no);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = getArguments();
                if (bundle.getSerializable("action") != null) {
                    Action action = (Action) bundle.getSerializable("action");
                    int day = action.getDay();
                    UsersManager.getInstance().getLoggedUser().deleteAction(day, action);


                    ScheduleFragment scheduleFragment = new ScheduleFragment();
                    bundle.putInt("day", day);
                    scheduleFragment.setArguments(bundle);

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, scheduleFragment).commit();


                    VerificationFragment.this.dismiss();
                }
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerificationFragment.this.dismiss();
            }
        });
        return root;
    }
}
