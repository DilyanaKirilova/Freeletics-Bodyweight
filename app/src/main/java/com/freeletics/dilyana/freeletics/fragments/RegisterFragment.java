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
import android.widget.EditText;

import com.freeletics.dilyana.freeletics.HomeActivity;
import com.freeletics.dilyana.freeletics.R;
import com.freeletics.dilyana.freeletics.model.users.User;
import com.freeletics.dilyana.freeletics.model.users.UsersManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    public RegisterFragment() {
        // Required empty public constructor
    }

    public final static String NAME_REGEX = "^[a-z]{2,15}$";
    /* Description:
        ^               #   Start of the line
        [a-z]	    #   Match characters and symbols in the list a-z
        {2,15}          #   Length at least 2 characters and maximum length of 15
$                       #   End of the line
     */
    public final static String PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    /*  Description:
        (               #   Start of group
        (?=.*\d)		#   must contains one digit from 0-9
        (?=.*[a-z])		#   must contains one lowercase characters
        (?=.*[A-Z])	    #   must contains one uppercase characters
        (?=.*[@#$%])    #   must contains one special symbols in the list "@#$%"
        .		        #   match anything with previous condition checking
        {6,20}	        #   length at least 6 characters and maximum of 20
        )		        #   End of group
     */
    public final static String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    /*  Description:
        ^			        #   start of the line
        [_A-Za-z0-9-\\+]+	#   must start with string in the bracket [ ], must contains one or more (+)
        (			        #   start of group #1
        \\.[_A-Za-z0-9-]+	#   follow by a dot "." and string in the bracket [ ], must contains one or more (+)
        )*			        #   end of group #1, this group is optional (*)
        @			        #   must contains a "@" symbol
        [A-Za-z0-9-]+       #   follow by string in the bracket [ ], must contains one or more (+)
        (			        #   start of group #2 - first level TLD checking
       \\.[A-Za-z0-9]+      #   follow by a dot "." and string in the bracket [ ], must contains one or more (+)
        )*		            #   end of group #2, this group is optional (*)
        (			        #   start of group #3 - second level TLD checking
       \\.[A-Za-z]{2,}      #   follow by a dot "." and string in the bracket [ ], with minimum length of 2
        )			        #   end of group #3
        $			        #end of the line
     */


    private Button btnCreateAccount;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etPassword1;
    private EditText etPassword2;

    private User.Gender gender;
    private int age;
    private int weight;
    private int height;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);

        etFirstName = (EditText) root.findViewById(R.id.et_fr_first_name);
        etLastName = (EditText) root.findViewById(R.id.et_fr_last_name);
        etEmail = (EditText) root.findViewById(R.id.et_fr_email);
        etPassword1 = (EditText) root.findViewById(R.id.et_fr_password_1);
        etPassword2 = (EditText) root.findViewById(R.id.et_fr_password_2);
        btnCreateAccount = (Button) root.findViewById(R.id.btn_fr_create_account);


        // get user info
        if(getArguments() != null){

            Bundle bundle = getArguments();

            if(bundle.getString("gender")!= null) {
                if (bundle.getString("gender").equals("Male")) {
                    gender = User.Gender.MALE;
                } else {
                    gender = User.Gender.FEMALE;
                }
            }
            age = bundle.getInt("age");
            height = bundle.getInt("height");
            weight = bundle.getInt("weight");
        }


        // check for valid registration and make registration
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstNameStr = etFirstName.getText().toString();
                String lastNameStr = etLastName.getText().toString();
                String password1Str = etPassword1.getText().toString();
                String password2Str = etPassword2.getText().toString();
                String emailStr = etEmail.getText().toString();

                if (isEmptyField(firstNameStr, etFirstName)) return;

                if (isEmptyField(lastNameStr, etLastName)) return;


                if (!firstNameStr.matches(NAME_REGEX)) {
                    etFirstName.setError("Name must contains only alphabetic symbols \n" +
                            "Name must be between 2-15 characters in length");
                    etFirstName.requestFocus();
                    return;
                }

                if (!lastNameStr.matches(NAME_REGEX)) {
                    etLastName.setError("Name must contains only alphabetic symbols \n" +
                            "Name must be between 2-15 characters in length");
                    etLastName.requestFocus();
                    return;
                }

                if (isEmptyField(password1Str, etPassword1)) return;

                /*
                if (!password1Str.matches(PASSWORD_REGEX)) {

                    etPassword1.setError("Password must contains: \n"+
                            "one digit from 0-9 \n"+
                            "one lowercase character \n"+
                            "one uppercase character \n"+
                            "one special symbol in the list \"@#$%\" \n"+
                            "Must be between 6-20 characters in length");
                    etPassword1.requestFocus();
                    return;
                }
                */

                if (isEmptyField(password2Str, etPassword2)) return;

                if(!password1Str.equals(password2Str)){

                    etPassword1.setText("");
                    etPassword2.setText("");
                    etPassword1.setError("Passwords mismatch");
                    etPassword1.requestFocus();
                    return;
                }

                /*
                if (!emailStr.matches(EMAIL_REGEX)){

                    etEmail.setError("Invalid email");
                    etEmail.requestFocus();
                    return;
                }
                */

                if (isEmptyField(emailStr, etEmail)) return;

                if(UsersManager.getInstance().existsUser(emailStr)){

                    etEmail.setError("This user already exists!");
                    etEmail.requestFocus();
                    return;
                }

                UsersManager.getInstance().registerUser(firstNameStr, lastNameStr, emailStr, password1Str, weight, height, age, gender);

                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return root;
    }

    public boolean isEmptyField(String txt, EditText et){
        if (txt.trim().isEmpty()) {
            et.setError("Please fill out this field");
            et.requestFocus();
            return true;
        }
        return false;
    }
}
