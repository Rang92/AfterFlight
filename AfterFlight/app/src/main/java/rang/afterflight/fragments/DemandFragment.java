package rang.afterflight.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import rang.afterflight.ListViewAdapter;
import rang.afterflight.Post;
import rang.afterflight.R;


/**
 * Rang Salih
 * rangsalih@gmail.com
 * 10690972
 */
public class DemandFragment extends Fragment {

    ListView lv;
    ArrayAdapter adapter;
    ArrayList<Post> postArrayList;
    private EditText editSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_demand, container, false);

        if(rootView != null){
            lv = (ListView) rootView.findViewById(R.id.listDemand);

            editSearch = (EditText) rootView.findViewById(R.id.search_post);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // clicked on item show post
                    Post selectedPost = postArrayList.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("data", (Parcelable) selectedPost);
                    FragmentManager fm = getActivity().getFragmentManager();
                    Fragment fragment = new rang.afterflight.fragments.SelectedPostFragment();
                    fragment.setArguments(bundle);
                    fm.beginTransaction().replace(R.id.content_main, fragment).commit();
                }
            });
        }
        searchPost();
        return rootView;
    }

    public void searchPost(){
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");

        postArrayList = new ArrayList<Post>();

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
                        newPost.setFlightnrParse((String) object.get("flightnr"));
                        newPost.setUsername((String) object.get("username"));
                        newPost.setImageFile((ParseFile) object.get("profilepic"));
                        newPost.setContactParse((String) object.get("contact"));

                        postArrayList.add(newPost);
                    }
                    adapter = new ListViewAdapter(getActivity(), R.layout.item_cardview, postArrayList);
                    lv.setAdapter(adapter);
                }
            }
        });
    }
}


