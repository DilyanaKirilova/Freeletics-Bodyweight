package com.freeletics.dilyana.freeletics.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.model.users.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeekScheduleFragment extends Fragment implements View.OnClickListener{


    public WeekScheduleFragment() {
        // Required empty public constructor
    }


    Button btnMonday;
    Button btnTuesday;
    Button btnWednesday;
    Button btnThursday;
    Button btnFriday;
    Button btnSaturday;
    Button btnSunday;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_week_shedule, container, false);

        btnMonday    = (Button) root.findViewById(R.id.btn_monday);
        btnTuesday   = (Button) root.findViewById(R.id.btn_tuesday);
        btnWednesday = (Button) root.findViewById(R.id.btn_wednesday);
        btnThursday  = (Button) root.findViewById(R.id.btn_thursday);
        btnFriday    = (Button) root.findViewById(R.id.btn_friday);
        btnSaturday  = (Button) root.findViewById(R.id.btn_saturday);
        btnSunday    = (Button) root.findViewById(R.id.btn_sunday);

        btnMonday.setOnClickListener(this);
        btnTuesday.setOnClickListener(this);
        btnWednesday.setOnClickListener(this);
        btnThursday.setOnClickListener(this);
        btnFriday.setOnClickListener(this);
        btnSaturday.setOnClickListener(this);
        btnSunday.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {

        ScheduleFragment scheduleFragment = new ScheduleFragment();
        Bundle bundle = new Bundle();

        if(v.getId() == btnMonday.getId()){
            bundle.putSerializable("day", User.Day.MONDAY);
        }
        else if(v.getId() == btnTuesday.getId()){
            bundle.putSerializable("day", User.Day.TUESDAY);
        }
        else if(v.getId() == btnWednesday.getId()){
            bundle.putSerializable("day", User.Day.WEDNESDAY);
        }
        else if(v.getId() == btnThursday.getId()){
            bundle.putSerializable("day", User.Day.THURSDAY);
        }
        else if(v.getId() == btnFriday.getId()){
            bundle.putSerializable("day", User.Day.FRIDAY);
        }
        else if(v.getId() == btnSaturday.getId()){
            bundle.putSerializable("day", User.Day.SATURDAY);
        }
        else if(v.getId() == btnSunday.getId()){
            bundle.putSerializable("day", User.Day.SUNDAY);
        }

        scheduleFragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, scheduleFragment).commit();
    }
}
