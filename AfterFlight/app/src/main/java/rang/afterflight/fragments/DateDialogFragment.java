package rang.afterflight.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Calendar;
import rang.afterflight.R;

/**
 * Rang Salih
 * rangsalih@gmail.com
 * 10690972
 * http://stackoverflow.com/questions/25065307/call-datepicker-from-a-fragment
 */
public class DateDialogFragment extends DialogFragment{

    DatePickerDialog.OnDateSetListener onDateSet;
    private boolean isModal = false;

    public static DateDialogFragment newInstance()
    {
        DateDialogFragment frag = new DateDialogFragment();
        frag.isModal = true;
        return frag;
    }

    public DateDialogFragment(){}


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the picker
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), onDateSet, year, month, day);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(isModal)
        {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        else {
            View rootView = inflater.inflate(R.layout.fragment_finishpost, container, false);
            return rootView;
        }
    }

    public void setCallBack(DatePickerDialog.OnDateSetListener onDate) {
        onDateSet = onDate;
    }
}




