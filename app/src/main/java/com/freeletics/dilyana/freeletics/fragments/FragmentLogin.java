package com.freeletics.dilyana.freeletics.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.ProfileTracker;
import com.freeletics.dilyana.freeletics.MainActivity;
import com.freeletics.dilyana.freeletics.data_base.DBManager;
import com.freeletics.dilyana.freeletics.model.users.*;

import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.freeletics.dilyana.freeletics.HomeActivity;
import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.model.users.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment {

    private EditText email;
    private EditText password;
    private LoginButton facebookButton;
    private Button emailButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    public static User u;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getContext());
        View root = inflater.inflate(R.layout.fragment_fragment_login, container, false);


        email = (EditText) root.findViewById(R.id.email_login);
        password = (EditText) root.findViewById(R.id.password_login);
        facebookButton = (LoginButton) root.findViewById(R.id.login_button);
        emailButton = (Button) root.findViewById(R.id.email_login_button_login);
        callbackManager = MainActivity.callbackManager;

        facebookButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Profile profile = Profile.getCurrentProfile();
                if (profile != null) {
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    User u = new User(profile.getFirstName().toString(), profile.getLastName());
                    UsersManager.getInstance().setLoggedUser(u);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "No profile", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity(), "Canceled login with Facebook!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getActivity(), "Canceled login with Facebook!", Toast.LENGTH_SHORT).show();
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UsersManager.getInstance().isValidLogin(email.getText().toString(), password.getText().toString())) {

                    DBManager.getInstance(getContext()).userLogged(true);

                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                    getActivity().finish();

                } else {

                    email.setError("Invalid email or password");
                    email.setText("");
                    password.setText("");
                }
            }
        });


        return root;
    }


}
