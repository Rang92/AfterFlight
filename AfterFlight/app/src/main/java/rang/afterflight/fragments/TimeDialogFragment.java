package rang.afterflight.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.TimeFormatException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import java.util.Calendar;
import rang.afterflight.R;


/**
 * Rang Salih
 * rangsalih@gmail.com
 * 10690972
 * http://stackoverflow.com/questions/25065307/call-datepicker-from-a-fragment
 */
public class TimeDialogFragment extends DialogFragment {

    TimePickerDialog.OnTimeSetListener onTimeSet;

    public boolean isModal = false;

    public static TimeDialogFragment newInstance()
    {
        TimeDialogFragment frag = new TimeDialogFragment();
        frag.isModal = true;
        return frag;
    }

    public TimeDialogFragment() {
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), onTimeSet, hour, minute, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (isModal)
        {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            View rootView = inflater.inflate(R.layout.fragment_finishpost, container, false);
            return rootView;
        }
    }



    public void setCallBack(TimePickerDialog.OnTimeSetListener onTime) {
        onTimeSet = onTime;
    }
}
