package com.freeletics.dilyana.freeletics.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.freeletics.dilyana.freeletics.MainActivity;
import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.WelcomeActivity;
import com.freeletics.dilyana.freeletics.model.users.UsersManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

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

        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.fragment_container, new EditProfileFragment()).commit();
            }
        });

        btnDeleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsersManager.getInstance().deleteUserRegistration();
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsersManager.getInstance().logOutUser();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("request_code","login");
                startActivity(intent);
                getActivity().finish();
                //fragmentTransaction.replace(R.id.activity_main, new FragmentLogin()).addToBackStack("settings").commit();
            }
        });

        return root;
    }

}
