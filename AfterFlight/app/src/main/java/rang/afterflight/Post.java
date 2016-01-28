package rang.afterflight;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.Serializable;
import java.util.List;

/**
 * Rang Salih
 * rangsalih@gmail.com
 * 10690972
 */
@ParseClassName("Post")
public class Post extends ParseObject implements Serializable, Parcelable {

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

    public String getContactParse(){
        return getString("contact");
    }

    public void setContactParse(String contact){
        if(contact != null){
            put("contact", contact);
        }
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(getAirportParse());
//        dest.writeString(getAddressParse());
    }

    protected Post(Parcel in) {
//        setAirportParse(in.readString());
//        setAddressParse(in.readString());
    }

    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>() {
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
