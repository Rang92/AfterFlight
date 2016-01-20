package rang.afterflight.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import rang.afterflight.R;

/**
 * Created by rang on 11-1-2016.
 */
public class FinishPostFragment extends Fragment {

    EditText airport_finish, date_finish, time_finish,
            persons_finish, address, flightnr_finish;


    Button dateButton;
    static final int DIALOG_ID = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_finishpost, container, false);

        airport_finish = (EditText) rootView.findViewById(R.id.airport_finish);
        date_finish = (EditText) rootView.findViewById(R.id.date_finish);

        String data = getArguments().getString("data");
        airport_finish.setText(data);

        dateButton = (Button) rootView.findViewById(R.id.pick_date);
        dateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dateDialogFragment();
            }
        });


        return rootView;
    }

    public void dateDialogFragment(){
        DateDialogFragment dpf = new DateDialogFragment().newInstance();
        dpf.setCallBack(onDate);
        dpf.show(getFragmentManager().beginTransaction(), "DatePickerFragment");
    }

    DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int dayOfMonth, int monthOfYear,
                              int year) {
            monthOfYear +=1;
            date_finish.setText(String.valueOf(year) + "-" + String.valueOf(monthOfYear)
                    + "-" + String.valueOf(dayOfMonth));
        }
    };


}




//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.finish_post_button, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.add_post_finish:
//                // User chose the "Favorite" action, mark the current item
//                // as a favorite...
//                FragmentManager fm = getActivity().getFragmentManager();
//                fm.beginTransaction().replace(R.id.content_main, new DemandFragment()).commit();
//                return true;
//
//            default:
//                // If we got here, the user's action was not recognized.
//                // Invoke the superclass to handle it.
//                return super.onOptionsItemSelected(item);
//        }
//    }


