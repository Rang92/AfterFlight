package rang.afterflight.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashSet;

import rang.afterflight.R;

/**
 * Created by rang on 11-1-2016.
 */
public class AirportPostFragment extends Fragment {

    String testlijst[];
    private ListView lista;
    private EditText edit;
    ArrayAdapter<String> adapter;

    HashSet airport_list;
    HashSet<String> list = new HashSet<>();
    HashSet<String> newList = new HashSet<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_airportpost, container, false);

        lista = (ListView) rootView.findViewById(R.id.listView_airports);
        edit = (EditText) rootView.findViewById(R.id.search_airport);
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, testlijst);

        lista.setAdapter(adapter);

        searchAirport();

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


        try {
            airport_list = readFeed();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        testlijst = (String[]) airport_list.toArray( new String[airport_list.size()]);
    }

    public void searchAirport(){
        edit.addTextChangedListener(new TextWatcher() {
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


//    public HashSet newHashSet(String query){
//        for(String items: list){
//            if (items == query){
//                newList.add(items);
//            }
//        }
//        return newList;
//    }

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

    public AirportPostFragment(Context context){
        mycontext = context;
        parser = mycontext.getResources().getXml(R.xml.airports);
    }


    // parse xml file and return the list
    HashSet<String> readFeed() throws XmlPullParserException, IOException {
        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                String name = parser.getName();
                // Starts by looking for the item tag
                if (name.equals("item")) {
                    if(parser.next() == XmlPullParser.TEXT){
                        String item = parser.getText();
                        list.add(item);
                    }
                }
            }
            parser.next();
        }
        Log.d("Listtest", " " + list);
        return list;
    }
}

