package edu.csulb.recipes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final String[] imgid;

    public CustomListAdapter(Activity context, String[] itemname, String[] imgid) {
        super(context, R.layout.mylist, itemname);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.itemname = itemname;
        this.imgid = imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylist, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        Integer[] imageID = new Integer[12];

        for (int i = 0; i < imgid.length; i++)
            if (imgid[i] != null)
                if (imgid[i].equalsIgnoreCase("R.drawable.aamras")) {
                    imageID[i] = R.drawable.aamras;
                } else if (imgid[i].equalsIgnoreCase("R.drawable.sandwich")) {
                    imageID[i] = R.drawable.sandwich;
                } else if (imgid[i].equalsIgnoreCase("R.drawable.bhurji")) {
                    imageID[i] = R.drawable.bhurji;
                } else if (imgid[i].equalsIgnoreCase("R.drawable.biryani")) {
                    imageID[i] = R.drawable.biryani;
                } else if (imgid[i].equalsIgnoreCase("R.drawable.breadpizza")) {
                    imageID[i] = R.drawable.breadpizza;
                } else if (imgid[i].equalsIgnoreCase("R.drawable.chicken")) {
                    imageID[i] = R.drawable.chicken;
                } else if (imgid[i].equalsIgnoreCase("R.drawable.fruit")) {
                    imageID[i] = R.drawable.fruit;
                } else if (imgid[i].equalsIgnoreCase("R.drawable.kolhapuri")) {
                    imageID[i] = R.drawable.kolhapuri;
                } else if (imgid[i].equalsIgnoreCase("R.drawable.manchurian")) {
                    imageID[i] = R.drawable.manchurian;
                } else if (imgid[i].equalsIgnoreCase("R.drawable.mutton")) {
                    imageID[i] = R.drawable.mutton;
                } else if (imgid[i].equalsIgnoreCase("R.drawable.paneer")) {
                    imageID[i] = R.drawable.paneer;
                } else {
                    imageID[i] = R.drawable.raita;
                }

        txtTitle.setText(itemname[position]);
        System.out.print(imageID);
        imageView.setImageResource(imageID[position]);
        return rowView;

    }
}

