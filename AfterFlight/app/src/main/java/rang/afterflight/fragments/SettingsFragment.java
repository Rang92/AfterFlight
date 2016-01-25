package rang.afterflight.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import rang.afterflight.R;

/**
 * Created by rang on 11-1-2016.
 */
public class SettingsFragment extends Fragment {

    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 2;

    Button changePictureButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);


        changePictureButton = (Button) rootView.findViewById(R.id.profilepic_button);
        changePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                imageDialog();
            }
        });

        return rootView;
    }



//    private void imageDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Upload an image..");
//        CharSequence options[] = new CharSequence[] {"Camera","From device"};
//
//        builder.setItems(options, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int pos) {
//                switch (pos) {
//                    case 1: // camera
//                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        startActivityForResult(intent, REQUEST_CAMERA);
//                        break;
//                    case 2: // from gallery
//                        intent = new Intent(
//                                Intent.ACTION_PICK,
//                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        intent.setType("image/*");
//                        startActivityForResult(
//                                Intent.createChooser(intent, "Select File"),
//                                SELECT_FILE);
//                        break;
//                }
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }

}
