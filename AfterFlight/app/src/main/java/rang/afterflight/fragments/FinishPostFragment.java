package rang.afterflight.fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import rang.afterflight.R;

/**
 * Rang Salih
 * rangsalih@gmail.com
 * 10690972
 */
public class FinishPostFragment extends Fragment {
    EditText persons_finish, address_finish, flightnr_finish, contact_finish;
    TextView airport_finish, date_finish, time_finish;
    Button dateButton, timeButton;
    DateDialogFragment ddf;
    TimeDialogFragment tdf;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_finishpost, container, false);

        airport_finish = (TextView) rootView.findViewById(R.id.airport_finish);
        date_finish = (TextView) rootView.findViewById(R.id.date_finish);
        time_finish = (TextView) rootView.findViewById(R.id.time_finish);
        persons_finish = (EditText) rootView.findViewById(R.id.persons_finish);
        address_finish = (EditText) rootView.findViewById(R.id.address_finish);
        flightnr_finish = (EditText) rootView.findViewById(R.id.flightnr_finish);
        contact_finish = (EditText) rootView.findViewById(R.id.contact_finish);

        dateButton = (Button) rootView.findViewById(R.id.pick_date);
        timeButton = (Button) rootView.findViewById(R.id.pick_time);

        // get string airport of ListView of AirportPostFragment
        String data = getArguments().getString("data");
        airport_finish.setText(data);

        clickOnDateButton();
        clickOnTimeButton();

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    // DateDialog opens
    public void clickOnDateButton(){
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialogFragment(v);
            }
        });
    }

    // TimeDialog opens
    public void clickOnTimeButton(){
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // view TimePickerDialog
                timeDialogFragment(v);
            }
        });
    }

    public void dateDialogFragment(View v){
        ddf = new DateDialogFragment().newInstance();
        ddf.setCallBack(onDate);
        ddf.show(getFragmentManager().beginTransaction(), "DatePickerFragment");
    }

    DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // add one to months (begins with 0)
            monthOfYear +=1;
            date_finish.setText(String.valueOf(year) + "-" + String.valueOf(monthOfYear)
                    + "-" + String.valueOf(dayOfMonth));
        }
    };


    public void timeDialogFragment(View v){
        tdf = new TimeDialogFragment().newInstance();
        tdf.setCallBack(onTime);
        tdf.show(getFragmentManager().beginTransaction(), "TimePickerFragment");
    }

    TimePickerDialog.OnTimeSetListener onTime = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            // add 0 to get two numbers
            String hourString;
            if (hourOfDay < 10) {
                hourString = "0" + hourOfDay;
            }
            else {
                hourString = "" + hourOfDay;
            }

            // add 0 to get two numbers
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
                final FragmentManager fm = getActivity().getFragmentManager();
                final Fragment fragment = new DemandFragment();

                ParseObject post = ParseObject.create("Post");

                // put strings to ParseObject post
                post.put("airport", airport_finish.getText().toString());
                post.put("date", date_finish.getText().toString());
                post.put("time", time_finish.getText().toString());
                post.put("persons", persons_finish.getText().toString());
                post.put("address", address_finish.getText().toString());
                post.put("flightnr", flightnr_finish.getText().toString());
                post.put("contact", contact_finish.getText().toString());
                ParseUser currentUser = ParseUser.getCurrentUser();
                post.put("username", currentUser.getUsername());

                // only if the user has a profile picture push it to Database
                if(currentUser.getParseFile("profilepic") != null){
                    post.put("profilepic", currentUser.getParseFile("profilepic"));
                }

                String dateString = date_finish.getText().toString();
                String timeString = time_finish.getText().toString();
                String personsString = persons_finish.getText().toString();
                String addressString = address_finish.getText().toString();
                String flightnrString = flightnr_finish.getText().toString();
                String contactString = contact_finish.getText().toString();

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
                    Snackbar.make(getView(), "Do not forget the number of persons!",
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                // Snackbar if address EditText is empty
                else if(addressString.trim().equals("")){
                    Snackbar.make(getView(), "Do not forget the address of your final destination!",
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                // Snackbar if flight number EditText is empty
                else if(flightnrString.trim().equals("")){
                    Snackbar.make(getView(), "Do not forget your flight number!",
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                // Snackbar if flight number EditText is empty
                else if(contactString.trim().equals("")){
                    Snackbar.make(getView(), "Do not forget your Email address or Phone Number!",
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                // Else add post
                else{
                    post.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Snackbar.make(getView(), "Your post has been successfully published!",
                                    Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            fm.beginTransaction().replace(R.id.content_main, fragment).commit();
                        }
                    });
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
