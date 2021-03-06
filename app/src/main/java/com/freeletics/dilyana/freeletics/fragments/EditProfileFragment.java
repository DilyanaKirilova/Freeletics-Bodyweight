package com.freeletics.dilyana.freeletics.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.freeletics.dilyana.freeletics.HomeActivity;
import com.freeletics.dilyana.freeletics.MainActivity;
import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.model.users.User;
import com.freeletics.dilyana.freeletics.model.users.UsersManager;

import static android.app.Activity.RESULT_OK;
import static com.freeletics.dilyana.freeletics.fragments.RegisterFragment.EMAIL_REGEX;
import static com.freeletics.dilyana.freeletics.fragments.RegisterFragment.NAME_REGEX;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends Fragment {


    public EditProfileFragment() {
        // Required empty public constructor
    }

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etPasswordOld;
    private EditText etPasswordNew;
    private EditText etPasswordNewConfirm;
    private Spinner spGender;
    private Spinner spAge;
    private Spinner spWeight;
    private Spinner spHeight;
    private Button btnSaveChanges;
    private Button btnChangePassword;

    // user data
    private User.Gender gender;
    private String genderStr;
    private int age;
    private int weight;
    private int height;

    ArrayAdapter adapterGender;
    ArrayAdapter adapterAge;
    ArrayAdapter adapterWeight;
    ArrayAdapter adapterHeight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        etFirstName          = (EditText) root.findViewById(R.id.et_fr_first_name);
        etLastName           = (EditText) root.findViewById(R.id.et_fr_last_name);
        etEmail              = (EditText) root.findViewById(R.id.et_fr_email);
        etPasswordOld        = (EditText) root.findViewById(R.id.et_fep_old_password);
        etPasswordNew        = (EditText) root.findViewById(R.id.et_fep_new_password);
        etPasswordNewConfirm = (EditText) root.findViewById(R.id.et_fep_confirm_new_password);
        spGender             = (Spinner) root.findViewById(R.id.spinner_gender);
        spAge                = (Spinner) root.findViewById(R.id.spinner_age);
        spWeight             = (Spinner) root.findViewById(R.id.spinner_weight);
        spHeight             = (Spinner) root.findViewById(R.id.spinner_height);
        btnChangePassword    = (Button) root.findViewById(R.id.btn_fep_change_password);
        btnSaveChanges       = (Button) root.findViewById(R.id.btn_fep_save_changes);

        adapterGender = ArrayAdapter.createFromResource(getActivity(), R.array.gender, R.layout.support_simple_spinner_dropdown_item);
        spGender.setAdapter(adapterGender);

        adapterAge = ArrayAdapter.createFromResource(getActivity(), R.array.age, R.layout.support_simple_spinner_dropdown_item);
        spAge.setAdapter(adapterAge);

        adapterWeight = ArrayAdapter.createFromResource(getActivity(), R.array.weight, R.layout.support_simple_spinner_dropdown_item);
        spWeight.setAdapter(adapterWeight);

        adapterHeight = ArrayAdapter.createFromResource(getActivity(), R.array.height, R.layout.support_simple_spinner_dropdown_item);
        spHeight.setAdapter(adapterHeight);

        setLoggedUserDataToEditTexts();
        if(UsersManager.getInstance().getLoggedUser().getHeight()!= 0) {
            setAllSpinnerValues();
        }

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String oldPassword = etPasswordOld.getText().toString();
                String newPassword = etPasswordNew.getText().toString();
                String confirmPassword = etPasswordNewConfirm.getText().toString();

                if (((MainActivity) getActivity()).isEmptyField(oldPassword, etPasswordOld)) return;
                if (((MainActivity) getActivity()).isEmptyField(newPassword, etPasswordNew)) return;
                if (((MainActivity) getActivity()).isEmptyField(confirmPassword, etPasswordNewConfirm))
                    return;


                if (!UsersManager.getInstance().getLoggedUser().getPassword().equals(oldPassword)) {
                    etPasswordOld.setError("This is not your old password");
                    etPasswordOld.requestFocus();
                    return;
                }

                if (!newPassword.equals(confirmPassword)) {

                    etPasswordNew.setText("");
                    etPasswordNewConfirm.setText("");
                    etPasswordNew.setError("Passwords mismatch");
                    etPasswordNew.requestFocus();
                    return;
                }

                UsersManager.getInstance().changeLoggedUserPassword(confirmPassword);
                Toast.makeText(getActivity(), "Your password was successfully changed to: " + confirmPassword, Toast.LENGTH_SHORT).show();
            }

        });

        spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genderStr = parent.getItemAtPosition(position).toString();
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

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNameStr = etFirstName.getText().toString();
                String lastNameStr = etLastName.getText().toString();
                String emailStr = etEmail.getText().toString();


                if(etFirstName.getText().toString().trim().isEmpty()){
                    etFirstName.setError("Please fill out this field");
                    etFirstName.requestFocus();
                    return;
                }
                if (!firstNameStr.matches(NAME_REGEX)) {
                    etFirstName.setError("Name must contains only alphabetic symbols \n" +
                            "Name must be between 2-15 characters in length");
                    return;
                }

                if(etLastName.getText().toString().trim().isEmpty()){
                    etLastName.setError("Please fill out this field");
                    etLastName.requestFocus();
                    return;
                }

                if (!lastNameStr.matches(NAME_REGEX)) {
                    etLastName.setError("Name must contains only alphabetic symbols \n" +
                            "Name must be between 2-15 characters in length");
                    etLastName.requestFocus();
                    return;
                }

                //if((UsersManager.getInstance().getLoggedUser().getEmail() != null)) {
                    if (etEmail.getText().toString().trim().isEmpty()) {
                        etEmail.setError("Please fill out this field");
                        etEmail.requestFocus();
                        return;
                    }

                    if (!emailStr.matches(EMAIL_REGEX)) {

                        etEmail.setError("Invalid email");
                        etEmail.requestFocus();
                        return;
                    }

                    if (genderStr.equals("Male")) {
                        gender = User.Gender.MALE;
                    } else {
                        gender = User.Gender.FEMALE;
                    }

                User u = new User(firstNameStr, lastNameStr, weight, height, age, gender, emailStr, UsersManager.getInstance().getLoggedUser().getPassword());
                UsersManager.getInstance().registerUser(u);
                UsersManager.getInstance().setLoggedUser(u);
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
                getActivity().finish();
                
            }
        });

        return root;
    }

    private void setLoggedUserDataToEditTexts() {

        User u = UsersManager.getInstance().getLoggedUser();
        etFirstName.setText(u.getFirstName());
        etLastName.setText(u.getLastName());
        etEmail.setText(u.getEmail());

    }

    private void setLoggedUserDataToSpinner(String userData, Spinner spinner) {

        ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
        int spinnerPosition = adapter.getPosition(userData);

        //set the default according to value
        spinner.setSelection(spinnerPosition);
    }

    private void setAllSpinnerValues() {

        User u = UsersManager.getInstance().getLoggedUser();

        if (u != null) {
            setLoggedUserDataToSpinner(u.getStringGender(), spGender);
            setLoggedUserDataToSpinner(String.valueOf(u.getAge()), spAge);
            setLoggedUserDataToSpinner(String.valueOf(u.getHeight()), spHeight);
            setLoggedUserDataToSpinner(String.valueOf(u.getWeight()), spWeight);
        }
    }
}
