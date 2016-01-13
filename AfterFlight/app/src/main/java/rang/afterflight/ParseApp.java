package rang.afterflight;

import android.app.Application;
import com.parse.Parse;

public class ParseApp extends Application {

    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "o93e7SMoCvVHZ6CUPOT6pK6otklYdCQpRelt2ZPY", "GHyPVlarrF64FAHGxRvEXkaCApxgxrUqqUh3oIEh");
    }

}
