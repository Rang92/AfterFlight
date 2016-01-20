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
import android.widget.AdapterView;
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

    String stringsList[];
    ArrayAdapter<String> adapter;
    private ListView listViewAirports;
    private EditText editSearch;
    Context myContext;
    XmlPullParser parser;
    HashSet airport_list;
    HashSet<String> list = new HashSet<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_airportpost, container, false);

        listViewAirports = (ListView) rootView.findViewById(R.id.listView_airports);
        editSearch = (EditText) rootView.findViewById(R.id.search_airport);
        adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, stringsList);

        listViewAirports.setAdapter(adapter);
        listViewAirports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                FragmentManager fm = getActivity().getFragmentManager();
                Fragment fragment = new FinishPostFragment();
                Bundle bundle = new Bundle();
                String itemString = listViewAirports.getItemAtPosition(position).toString();
                Log.d("Item string", itemString);
                bundle.putString("data", itemString);
                fragment.setArguments(bundle);
                fm.beginTransaction().replace(R.id.content_main, fragment).commit();
            }
        });

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

        stringsList = (String[]) airport_list.toArray( new String[airport_list.size()]);
    }

    public void searchAirport(){
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


//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.finish_post_button, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.add_post_finish:
//                // User chose the "Favorite" action, mark the current item
//                // as a favorite...
//                FragmentManager fm = getActivity().getFragmentManager();
//                fm.beginTransaction().replace(R.id.content_main, new DemandFragment()).commit();
//                return true;
//
//            default:
//                // If we got here, the user's action was not recognized.
//                // Invoke the superclass to handle it.
//                return super.onOptionsItemSelected(item);
//
//        }
//    }



    public AirportPostFragment(Context context){
        myContext = context;
        parser = myContext.getResources().getXml(R.xml.airports);
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
//        Log.d("Listtest", " " + list);
        return list;
    }
}

