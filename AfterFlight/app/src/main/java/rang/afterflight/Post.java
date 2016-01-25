package rang.afterflight;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import java.util.List;

/**
 * Created by rang on 21-1-16.
 */
@ParseClassName("Post")
public class Post extends ParseObject {
    String airport, date, time, persons, address, flightnr;



    public Post(String airport, String date, String time, String persons, String address, String flightnr){
        super();

        this.airport = airport;
        this.date = date;
        this.time = time;
        this.persons = persons;
        this.address = address;
        this.flightnr = flightnr;
    }

    public String getId(){
        return getString("objectId");
    }

    public void setId(String id){
        put("objectId", id);
    }

    public String getAirportParse(){
        return getString("airport");
    }

    public void setAirportParse(String airport){
        put("airport", airport);
    }

    //////////


    public String getDateParse(){
        return getString("date");
    }

    public void setDateParse(String date){
        put("date", date);
    }


    //////////


    public String getTimeParse(){
        return getString("time");
    }

    public void setTimeParse(String time){
        put("time", time);
    }


    //////////


    public String getPersonsParse(){
        return getString("persons");
    }

    public void setPersonsParse(String persons){
        put("persons", persons);
    }


    //////////


    public String getAddressParse(){
        return getString("address");
    }

    public void setAddressParse(String address){
        put("address", address);
    }

    public String getFlightnrParse(){
        return getString("flightnr");
    }

    public void setFlightnrParse(String flightnr){
        put("flightnr", flightnr);
    }


    public ParseFile getImageFile(){
        return getParseFile("image");
    }

    public void setImageFile(ParseFile file) {
        if (file != null) {
            put("image", file);
        }
    }


    public List<String> getPosts(){
        return getList("Post");
    }
    public void setIngredients(List<String> postsLists){
        if (postsLists != null)
            put("Post", postsLists);
    }
}
