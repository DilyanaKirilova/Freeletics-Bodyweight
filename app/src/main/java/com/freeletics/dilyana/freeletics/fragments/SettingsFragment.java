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

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.freeletics.dilyana.freeletics.MainActivity;
import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.WelcomeActivity;
import com.freeletics.dilyana.freeletics.data_base.DBManager;
import com.freeletics.dilyana.freeletics.model.users.UsersManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FacebookSdk.sdkInitialize(getContext());
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        Button btnEditProfile   = (Button) root.findViewById(R.id.btn_fup_edit_profile);
        Button btnDeleteProfile = (Button) root.findViewById(R.id.btn_fup_delete_profile);
        Button btnLogOut        = (Button) root.findViewById(R.id.btn_fup_logout);

        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.fragment_container, new EditProfileFragment()).addToBackStack("edit_profile").commit();
            }
        });

        btnDeleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // DBManager.getInstance(getContext()).deleteUser(UsersManager.getInstance().getLoggedUser().getEmail());
                UsersManager.getInstance().deleteUserRegistration();
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                startActivity(intent);
                getActivity().finish();
                LoginManager.getInstance().logOut();
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DBManager.getInstance(getContext()).userLogged(false);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("request_code","login");
                startActivity(intent);
                getActivity().finish();
                LoginManager.getInstance().logOut();
                //fragmentTransaction.replace(R.id.activity_main, new FragmentLogin()).addToBackStack("settings").commit();
            }
        });

        return root;
    }

}
