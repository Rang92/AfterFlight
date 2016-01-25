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


import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import rang.afterflight.ListViewAdapter;
import rang.afterflight.Post;
import rang.afterflight.R;


/**
 * Created by rang on 11-1-2016.
 */
public class DemandFragment extends Fragment {

    ListView lv;
    ArrayAdapter adapter;
    ArrayList<Post> mijncode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_demand, container, false);

        if(rootView != null){
            lv = (ListView) rootView.findViewById(R.id.listDemand);

//            adapter = new ListViewAdapter(getActivity(), R.layout.item_cardview, mijncode);
//
//            lv.setAdapter(adapter);

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

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");

        mijncode = new ArrayList<Post>();

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> postList, ParseException e) {
                if (e == null) {
                    for (ParseObject object : postList) {
                        Post newPost = new Post();

                        newPost.setAirportParse((String) object.get("airport"));
                        newPost.setDateParse((String) object.get("date"));
                        newPost.setTimeParse((String) object.get("time"));
                        newPost.setPersonsParse((String) object.get("persons"));
                        newPost.setAddressParse((String) object.get("address"));
                        newPost.setFlightnrParse((String) object.get("address"));
//                        newPost.setUsername((String) object.get("username"));

                        mijncode.add(newPost);


                    }

                    adapter = new ListViewAdapter(getActivity(), R.layout.item_cardview, mijncode);
                    lv.setAdapter(adapter);
                }
            }
        });
    }
}


