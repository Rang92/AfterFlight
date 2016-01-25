package rang.afterflight.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

import rang.afterflight.Post;
import rang.afterflight.R;


/**
 * Created by rang on 11-1-2016.
 */
public class DemandFragment extends Fragment {

    ListView lv;
    ArrayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_demand, container, false);

        if(rootView != null){
            lv = (ListView) rootView.findViewById(R.id.listDemand);

            ArrayList<String> arraypost = getArguments().getStringArrayList("listPost");

            adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arraypost);

            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // clicked on item show post
                    FragmentManager fm = getActivity().getFragmentManager();
                    Fragment fragment = new ViewPostFragment();
                    fm.beginTransaction().replace(R.id.content_main, fragment).commit();
                }
            });
        }

        return rootView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

}