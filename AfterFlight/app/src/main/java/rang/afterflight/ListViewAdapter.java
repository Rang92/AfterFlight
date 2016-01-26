package rang.afterflight;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.parse.ParseImageView;
import java.util.ArrayList;

/**
 * Created by rang on 25-1-16.
 */
public class ListViewAdapter extends ArrayAdapter<Post> {
    Context context;
    int layoutResourceId;
    ArrayList<Post> data = new ArrayList<Post>();

    public ListViewAdapter(Context context, int layoutResourceId,
                           ArrayList<Post> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new RecordHolder();
            holder.airport_name = (TextView) row.findViewById(R.id.item_airport);
            holder.imageItem = (ParseImageView) row.findViewById(R.id.item_image);
            holder.date = (TextView) row.findViewById(R.id.item_date);
            holder.time = (TextView) row.findViewById(R.id.item_time);
            holder.username = (TextView) row.findViewById(R.id.item_username);

            row.setTag(holder);
        } else { // recycle view if any
            holder = (RecordHolder) row.getTag();
        }

        Post item = data.get(position);
        holder.airport_name.setText(item.getAirportParse());
        holder.date.setText(item.getDateParse());
        holder.time.setText(item.getTimeParse());
        holder.username.setText(item.getUsername());
        holder.imageItem.setImageBitmap(item.getImageFile());

        if (item.getImageFile() != null) {
            holder.imageItem.setImageBitmap(item.getImageFile());
        } else {
            holder.imageItem.setImageResource(R.drawable.profilepicture);
            holder.imageItem.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }

        return row;

    }

    static class RecordHolder {
        TextView airport_name;
        TextView date;
        TextView time;
        TextView username;
        ParseImageView imageItem;
    }
}
