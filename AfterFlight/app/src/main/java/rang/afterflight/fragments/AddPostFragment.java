package rang.afterflight.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashSet;
import java.util.zip.Inflater;

import rang.afterflight.R;

/**
 * Created by rang on 11-1-2016.
 */
public class AddPostFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_add_post, container, false);

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_menu_post, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_post:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                FragmentManager fm = getActivity().getFragmentManager();
                fm.beginTransaction().replace(R.id.content_main, new DemandFragment()).commit();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    Context mycontext;
    XmlPullParser parser;
    HashSet<String> list = new HashSet<>();
    HashSet<String> cloneSet = new HashSet<>();

//    public WordLoad(Context context){
//        mycontext = context;
//        parser = mycontext.getResources().getXml(R.xml.airports);
//    }


//    // parse xml file and return the list
//    HashSet<String> readFeed() throws XmlPullParserException, IOException {
//        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
//            if (parser.getEventType() == XmlPullParser.START_TAG) {
//                String name = parser.getName();
//                // Starts by looking for the item tag
//                if (name.equals("item")) {
//                    if(parser.next() == XmlPullParser.TEXT){
//                        String item = parser.getText();
//                        list.add(item);
//                    }
//                }
//            }
//            parser.next();
//        }
//        return list;
//    }
//}

}