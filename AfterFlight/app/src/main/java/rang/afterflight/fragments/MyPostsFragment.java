package rang.afterflight.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

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
public class MyPostsFragment extends Fragment {

    ListView lv;
    ArrayAdapter adapter;
    ArrayList<Post> postArrayList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_myposts, container, false);

        lv = (ListView) rootView.findViewById(R.id.mypost_listview);

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ownPost();

        setHasOptionsMenu(true);


    }

    public void ownPost(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");

        ParseUser currentUser = ParseUser.getCurrentUser();
        String user = currentUser.getUsername();

        postArrayList = new ArrayList<Post>();

        query.whereEqualTo("username", user);

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
