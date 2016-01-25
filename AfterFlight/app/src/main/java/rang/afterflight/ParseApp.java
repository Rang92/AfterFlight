package rang.afterflight;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApp extends Application {

    public void onCreate() {
        Parse.initialize(this, "o93e7SMoCvVHZ6CUPOT6pK6otklYdCQpRelt2ZPY", "GHyPVlarrF64FAHGxRvEXkaCApxgxrUqqUh3oIEh");
        super.onCreate();
    }

}
