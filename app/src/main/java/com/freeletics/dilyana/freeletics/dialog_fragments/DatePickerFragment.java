package com.freeletics.dilyana.freeletics.dialog_fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

import com.freeletics.dilyana.freeletics.HomeActivity;
import com.freeletics.dilyana.freeletics.MainActivity;

import java.util.Calendar;

/**
 * Created by Dilyana on 4/13/2017.
 */

public class DatePickerFragment extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return  new DatePickerDialog(getActivity(), (HomeActivity)getActivity(), year, month, day);
    }
}
