package rang.afterflight.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import rang.afterflight.R;

/**
 * Rang Salih
 * rangsalih@gmail.com
 * 10690972
 */
public class MainFragment extends Fragment{

    ImageButton addPost;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        addPost = (ImageButton)rootView.findViewById(R.id.postButtonMain);
        clickOnAddPost();

        return rootView;
    }

    // opens AirportPostFragment
    public void clickOnAddPost(){
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getFragmentManager();
                fm.beginTransaction().replace(R.id.content_main,
                        new AirportPostFragment(getActivity())).commit();
            }
        });
    }


}
