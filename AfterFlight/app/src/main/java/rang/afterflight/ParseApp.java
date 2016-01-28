package rang.afterflight;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Rang Salih
 * rangsalih@gmail.com
 * 10690972
 */
public class ParseApp extends Application {

    public void onCreate() {
        ParseObject.registerSubclass(Post.class);
        Parse.initialize(this, "o93e7SMoCvVHZ6CUPOT6pK6otklYdCQpRelt2ZPY",
                "GHyPVlarrF64FAHGxRvEXkaCApxgxrUqqUh3oIEh");
        super.onCreate();
    }

}
