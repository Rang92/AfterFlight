package rang.afterflight.fragments;

import android.app.Fragment;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import rang.afterflight.R;

/**
 * Created by rang on 11-1-2016.
 */
public class DemandFragment extends Fragment {

    ImageButton floatButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_demand, container, false);

        floatButton = (ImageButton)rootView.findViewById(R.id.postButtonDemand);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        fav = menu.add("add");
//        fav.setIcon(R.drawable.ic_menu_home);
//        super.onCreateOptionsMenu(menu, inflater);
        //MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

//        return true;
    }
}