package com.freeletics.dilyana.freeletics.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.model.users.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoUserFragment extends Fragment {


    public InfoUserFragment() {
        // Required empty public constructor
    }

    private TextView tvTitleOne;
    private TextView tvTitleTwo;
    private Button btnOptOne;
    private Button btnOptTwo;
    private Button btnOptThree;


    // user data

    private User.Gender gender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_info_user, container, false);

        tvTitleOne = (TextView) root.findViewById(R.id.tv_fiu_title_one);
        tvTitleTwo = (TextView) root.findViewById(R.id.tv_fiu_title_two);
        btnOptOne  = (Button) root.findViewById(R.id.btn_fiu_opt_one);
        btnOptTwo  = (Button) root.findViewById(R.id.btn_fiu_opt_two);
        btnOptThree  = (Button) root.findViewById(R.id.btn_fiu_opt_three);

        return root;
    }

}
