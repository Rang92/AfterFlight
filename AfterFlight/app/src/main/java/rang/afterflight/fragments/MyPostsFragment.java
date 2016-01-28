package rang.afterflight.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.DeleteCallback;
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

    ListView myPostListView;
    ArrayAdapter adapter;
    ArrayList<Post> postArrayList;
    Post selectedPost;
    Bundle bundle;
    FragmentManager fm;
    Fragment fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_myposts, container, false);

        myPostListView = (ListView) rootView.findViewById(R.id.mypost_listview);

        clickOnPost();
        longClickOnPost();;

        return rootView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadOwnPosts();

        setHasOptionsMenu(true);
    }

    public void clickOnPost(){
        myPostListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // clicked on item show post
                selectedPost = postArrayList.get(position);
                bundle = new Bundle();
                bundle.putParcelable("data", (Parcelable) selectedPost);
                fm = getActivity().getFragmentManager();
                fragment = new rang.afterflight.fragments.SelectedPostFragment();
                fragment.setArguments(bundle);
                fm.beginTransaction().replace(R.id.content_main, fragment).commit();
            }

        });
    }

    public void longClickOnPost(){
        myPostListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPost = postArrayList.get(position);
                showDialog();
                return true;
            }
        });
    }

    public void loadOwnPosts(){
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
                        newPost.setId(object.getObjectId());
                        postArrayList.add(newPost);
                    }
                    adapter = new ListViewAdapter(getActivity(), R.layout.item_cardview,
                            postArrayList);
                    myPostListView.setAdapter(adapter);
                }
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View view = factory.inflate(R.layout.dialog_deletepost, null);
        builder.setView(view);
        builder.setCancelable(false);
        builder.setTitle("Are you sure you want to delete?");
        builder.setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("DELETE POST", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
                query.whereEqualTo("objectId", selectedPost.getId());
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> postList, ParseException e) {
                        if (e == null) {
                            for (ParseObject post : postList) {
                                post.deleteInBackground(new DeleteCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        loadOwnPosts();
                                    }
                                });
                            }
                        }
                    }
                });
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
