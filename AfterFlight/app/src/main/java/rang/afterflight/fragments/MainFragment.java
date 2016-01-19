package rang.afterflight.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import rang.afterflight.R;

/**
 * Created by rang on 11-1-2016.
 */
public class MainFragment extends Fragment implements View.OnClickListener{

    ImageButton floatButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        floatButton = (ImageButton)rootView.findViewById(R.id.postButtonMain);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getFragmentManager();
                fm.beginTransaction().replace(R.id.content_main, new AirportPostFragment(getActivity())).commit();
            }
        });

        return rootView;
    }

    @Override
    public void onClick(View v) {

    }
}
