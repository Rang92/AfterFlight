package rang.afterflight;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rang on 21-1-16.
 */
@ParseClassName("Post")
public class Post extends ParseObject implements Serializable {

    public Post(){
        super();
    }

    public String getId(){
        return getString("objectId");
    }

    public void setId(String id){
        put("objectId", id);
    }

    //////////

    public String getUsername(){
        return getString("username");
    }

    public void setUsername(String username){
        put("username", username);
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


    public Bitmap getImageFile(){
        Bitmap bmp = null;
        ParseFile image = getParseFile("profilepic");
        if(image != null){
            try {
                byte[] data = image.getData();
                bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
            } catch (ParseException e) {
                e.printStackTrace();
                }
        }
        return bmp;
    }

    public void setImageFile(ParseFile file) {
        if (file != null) {
            put("profilepic", file);
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
