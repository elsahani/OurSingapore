package sg.edu.rp.c346.id20023841.oursingapore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Island> versionList;

    public CustomAdapter(Context context, int resource, ArrayList<Island> objects) {
        super(context,resource,objects);

        parent_context = context;
        layout_id = resource;
        versionList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvIslandName = rowView.findViewById(R.id.tvIslandName);
        TextView tvIslandSQKM = rowView.findViewById(R.id.tvSQKM);
        RatingBar rbs = rowView.findViewById(R.id.ratingBarStars2);
        TextView tvIslandDescription = rowView.findViewById(R.id.tvIslandDescription);
        ImageView image = rowView.findViewById(R.id.imageViewNew);

        // Obtain the Android Version information based on the position
        Island currentVersion = versionList.get(position);

        // Set values to the TextView to display the corresponding information
        tvIslandName.setText(currentVersion.getName());
        tvIslandSQKM.setText(Integer.toString(currentVersion.getSqkm()));
        //tvRating.setText(currentVersion.toString());

        rbs.setRating(currentVersion.getStars());
        tvIslandDescription.setText(currentVersion.getDescription());

        int sqkm = currentVersion.getSqkm();

        if (sqkm >= 5){
            image.setImageResource(R.drawable.newfamous);

        }else{
            image.setVisibility(View.INVISIBLE);
        }
        return rowView;
    }

}
