package com.freeletics.dilyana.freeletics.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.freeletics.dilyana.freeletics.MainActivity;
import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.model.users.UsersManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {


    public SettingsFragment() {
        // Required empty public constructor
    }

    private Button btnEditProfile;
    private Button btnLogOut;
    private Button btnDeleteProfile;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        btnEditProfile   = (Button) root.findViewById(R.id.btn_fup_edit_profile);
        btnDeleteProfile = (Button) root.findViewById(R.id.btn_fup_delete_profile);
        btnLogOut        = (Button) root.findViewById(R.id.btn_fup_logout);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO go to EditProfileFragment
            }
        });

        btnDeleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO delete profile and go back to WelcomeActivity
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO log out and go back to MainActivity - Fragmentlogin
                UsersManager.getInstance().logOutUser();

                //FragmentManager fragmentManager = getFragmentManager();
                //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //fragmentTransaction.replace(R.id.activity_main, new FragmentLogin()).commit();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("request_code", "settings");
                startActivity(intent);
            }
        });

        return root;
    }

}
