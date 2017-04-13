package com.freeletics.dilyana.freeletics.dialog_fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;

import com.freeletics.dilyana.freeletics.HomeActivity;

import java.util.Calendar;

/**
 * Created by Dilyana on 4/13/2017.
 */

public class TimePickerFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return  new TimePickerDialog(getActivity(), (HomeActivity)getActivity(),
                hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
}
