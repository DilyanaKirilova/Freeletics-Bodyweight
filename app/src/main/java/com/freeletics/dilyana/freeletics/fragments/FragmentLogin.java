package com.freeletics.dilyana.freeletics.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.freeletics.dilyana.freeletics.model.users.User;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment {

    private EditText email;
    private EditText password;
    private LoginButton facebookButton;
    private Button emailButton;
    private CallbackManager callbackManager;
    public static User u;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_fragment_login, container, false);

        callbackManager = CallbackManager.Factory.create();
        email = (EditText) root.findViewById(R.id.email_login);
        password = (EditText) root.findViewById(R.id.password_login);
        facebookButton = (LoginButton) root.findViewById(R.id.facebook_login_button_login);
        emailButton = (Button) root.findViewById(R.id.email_login_button_login);

        facebookButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validData(email.getText().toString(), password.getText().toString())){
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                }
            }
        });

        return root;
    }

    private boolean validData(String email, String password) {
        boolean flag = false;
        ArrayList<User> users = MainActivity.getRegisterUsers();
        if(users.size() > 0) {
            for (User user : users) {
                if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                    flag = true;
                    this.u = user;
                    break;
                }
            }
        }
        return flag;
    }

}
