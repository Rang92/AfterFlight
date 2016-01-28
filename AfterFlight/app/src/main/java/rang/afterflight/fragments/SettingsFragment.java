package rang.afterflight.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import java.io.ByteArrayOutputStream;
import rang.afterflight.R;

/**
 * Rang Salih
 * rangsalih@gmail.com
 * 10690972
 */
public class SettingsFragment extends Fragment {
    private static final int FROM_GALLERY = 1;
    ImageView iv;
    Button changePictureButton, saveSettings;
    Bitmap yourSelectedImage;
    TextView user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        View navBarView = inflater.inflate(R.layout.nav_header_main_menu, container, false);

        iv = (ImageView) rootView.findViewById(R.id.pic_upload);
        changePictureButton = (Button) rootView.findViewById(R.id.profilepic_button);
        changePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, FROM_GALLERY);
            }
        });

        user = (TextView) navBarView.findViewById(R.id.username_nav);

        saveSettings = (Button) rootView.findViewById(R.id.save_settings);
        saveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(getView(), "Your profile picture is uploaded!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // Compress image to lower quality scale 1 - 100
                yourSelectedImage.compress(Bitmap.CompressFormat.PNG, 80, stream);


                byte[] image = stream.toByteArray();

                // file in parse
                ParseFile file = new ParseFile("profilepic", image);
                file.saveInBackground();

                ParseUser currentUser = ParseUser.getCurrentUser();
                currentUser.put("profilepic", file);


                currentUser.saveInBackground();
            }
        });


        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseFile image = (ParseFile) ParseUser.getCurrentUser().getParseFile("profilepic");

        if(image != null){
            image.getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {

                    if (e == null)
                    {
                        Bitmap bmp = BitmapFactory .decodeByteArray(data, 0, data.length);

                        iv.setImageBitmap(bmp);
                    }
                    else
                    {

                    }

                }
            });
        }

        setHasOptionsMenu(true);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case FROM_GALLERY:
                if(resultCode == Activity.RESULT_OK){
                    Uri uri = data.getData();
                    String[]projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    yourSelectedImage = BitmapFactory.decodeFile(filePath);
                    Drawable d = new BitmapDrawable(yourSelectedImage);

                    iv.setImageBitmap(yourSelectedImage);

                    iv.setBackground(d);
                }
        }

    }

}
