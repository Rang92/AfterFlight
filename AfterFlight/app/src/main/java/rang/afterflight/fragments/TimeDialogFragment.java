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
 * Created by rang on 20-1-16.
 */
public class TimeDialogFragment extends DialogFragment {

    TimePickerDialog.OnTimeSetListener onTimeSet;

    public boolean isModal = false;

    public static TimeDialogFragment newInstance()
    {
        TimeDialogFragment frag = new TimeDialogFragment();
        frag.isModal = true; // WHEN FRAGMENT IS CALLED AS A DIALOG SET FLAG
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
        if (isModal) // AVOID REQUEST FEATURE CRASH
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
