package rang.afterflight.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;

import rang.afterflight.Post;
import rang.afterflight.R;

/**
 * Rang Salih
 * rangsalih@gmail.com
 * 10690972
 */
public class SelectedPostFragment extends Fragment {
    TextView airportPost, datePost, timePost, personsPost, addressPost,
            flightnrPost, postedbyPost, contactPost;

    ImageButton profilepicPost, clipButton;
    Bitmap bmp;

    String username;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_selectedpost, container, false);

        airportPost = (TextView) rootView.findViewById(R.id.airport_post);
        datePost = (TextView) rootView.findViewById(R.id.date_post);
        timePost = (TextView) rootView.findViewById(R.id.time_post);
        personsPost = (TextView) rootView.findViewById(R.id.persons_post);
        addressPost = (TextView) rootView.findViewById(R.id.address_post);
        flightnrPost = (TextView) rootView.findViewById(R.id.flightnr_post);
        postedbyPost = (TextView) rootView.findViewById(R.id.postedby_post);
        contactPost = (TextView) rootView.findViewById(R.id.contact_post);
        profilepicPost = (ImageButton) rootView.findViewById(R.id.image_post);
        clipButton = (ImageButton) rootView.findViewById(R.id.clip_button);

        clickOnPicture();
        clickOnClip();
        loadPostInfo();

        return rootView;
    }

    // opens pictureDialog
    public void clickOnPicture(){
        profilepicPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });
    }

    // copies contact info to Clipboard
    public void clickOnClip(){
        clipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                String copyString = (String) contactPost.getText();
                android.content.ClipData clip = android.content.ClipData.newPlainText("text label", copyString);
                clipboard.setPrimaryClip(clip);
            }
        });
    }

    // loads post info from previous fragment
    public void loadPostInfo(){
        Post selectedPost = getArguments().getParcelable("data");

        String airport = (String) selectedPost.get("airport");
        String date = (String) selectedPost.get("date");
        String time = (String) selectedPost.get("time");
        String persons = (String) selectedPost.get("persons");
        String address = (String) selectedPost.get("address");
        String flightnr = (String) selectedPost.get("flightnr");
        username = (String) selectedPost.get("username");
        String contact = (String) selectedPost.get("contact");

        ParseFile profilepic = (ParseFile) selectedPost.getParseFile("profilepic");

        if(profilepic != null){
            profilepic.getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {

                    if (e == null)
                    {
                        bmp = BitmapFactory .decodeByteArray(data, 0, data.length);

                        profilepicPost.setImageBitmap(bmp);
                    }
                    else
                    {

                    }

                }
            });
        }

        // Give all TextViews a String value
        airportPost.setText(airport);
        datePost.setText(date);
        timePost.setText(time);
        personsPost.setText(persons);
        addressPost.setText(address);
        flightnrPost.setText(flightnr);
        postedbyPost.setText(username);
        contactPost.setText(contact);
    }

    // creates and opens pictureDialog
    private void showPictureDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View view = factory.inflate(R.layout.dialog_picture, null);
        ImageView image= (ImageView) view.findViewById(R.id.imageViewItem);
        image.setImageBitmap(bmp);

        builder.setView(view);
        builder.setTitle(username);
        builder.setMessage("Profile picture");
        builder.setCancelable(false);
        builder.setPositiveButton("GO BACK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
