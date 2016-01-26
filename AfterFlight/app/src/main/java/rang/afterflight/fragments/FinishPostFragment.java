package rang.afterflight.fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import rang.afterflight.Post;
import rang.afterflight.R;

/**
 * Created by rang on 11-1-2016.
 */
public class FinishPostFragment extends Fragment {
    EditText airport_finish, date_finish, time_finish,
            persons_finish, address_finish, flightnr_finish;

    Button dateButton, timeButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_finishpost, container, false);

        airport_finish = (EditText) rootView.findViewById(R.id.airport_finish);
        date_finish = (EditText) rootView.findViewById(R.id.date_finish);
        time_finish = (EditText) rootView.findViewById(R.id.time_finish);
        persons_finish = (EditText) rootView.findViewById(R.id.persons_finish);
        address_finish = (EditText) rootView.findViewById(R.id.address_finish);
        flightnr_finish = (EditText) rootView.findViewById(R.id.flightnr_finish);

        dateButton = (Button) rootView.findViewById(R.id.pick_date);
        timeButton = (Button) rootView.findViewById(R.id.pick_time);

        // get string airport of ListView of AirportPostFragment
        String data = getArguments().getString("data");
        airport_finish.setText(data);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialogFragment(v);
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // view TimePickerDialog
                timeDialogFragment(v);
            }
        });



        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void dateDialogFragment(View v){
        DateDialogFragment dpf = new DateDialogFragment().newInstance();
        dpf.setCallBack(onDate);
        dpf.show(getFragmentManager().beginTransaction(), "DatePickerFragment");
    }

    DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            monthOfYear +=1;
            date_finish.setText(String.valueOf(year) + "-" + String.valueOf(monthOfYear)
                    + "-" + String.valueOf(dayOfMonth));
        }
    };

    public void timeDialogFragment(View v){
        TimeDialogFragment tdf = new TimeDialogFragment().newInstance();
        tdf.setCallBack(onTime);
        tdf.show(getFragmentManager().beginTransaction(), "TimePickerFragment");
    }

    TimePickerDialog.OnTimeSetListener onTime = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            String hourString;
            if (hourOfDay < 10) {
                hourString = "0" + hourOfDay;
            }
            else {
                hourString = "" + hourOfDay;
            }

            String minuteString;
            if (minute < 10) {
                minuteString = "0" + minute;
            }
            else {
                minuteString = "" + minute;
            }


            // Set text into EditText
            time_finish.setText(String.valueOf(hourString) + ":" + String.valueOf(minuteString));
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.finish_post_button, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_post_finish:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                final FragmentManager fm = getActivity().getFragmentManager();
                final Fragment fragment = new DemandFragment();

                // put strings to listview in DemandFragment
                ParseObject post = ParseObject.create("Post");

                post.put("airport", airport_finish.getText().toString());
                post.put("date", date_finish.getText().toString());
                post.put("time", time_finish.getText().toString());
                post.put("persons", persons_finish.getText().toString());
                post.put("address", address_finish.getText().toString());
                post.put("flightnr", flightnr_finish.getText().toString());
                ParseUser currentUser = ParseUser.getCurrentUser();
                post.put("username", currentUser.getUsername());
                if(currentUser.getParseFile("profilepic") != null){
                    post.put("profilepic", currentUser.getParseFile("profilepic"));
                }

                String dateString = date_finish.getText().toString();
                String timeString = time_finish.getText().toString();
                String personsString = persons_finish.getText().toString();
                String addressString = address_finish.getText().toString();
                String flightnrString = flightnr_finish.getText().toString();

                // Snackbar if date EditText is empty
                if (dateString.trim().equals("")) {
                    Snackbar.make(getView(), "Do not forget the date!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                // Snackbar if time EditText is empty
                else if(timeString.trim().equals("")){
                    Snackbar.make(getView(), "Do not forget the time!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                // Snackbar if persons EditText is empty
                else if(personsString.trim().equals("")){
                    Snackbar.make(getView(), "Do not forget the number of persons!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                // Snackbar if address EditText is empty
                else if(addressString.trim().equals("")){
                    Snackbar.make(getView(), "Do not forget the address of your final destination!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                // Snackbar if flight number EditText is empty
                else if(flightnrString.trim().equals("")){
                    Snackbar.make(getView(), "Do not forget your flight number!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                // Else add post
                else{
                    post.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            fm.beginTransaction().replace(R.id.content_main, fragment).commit();
                        }
                    });
                }

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
