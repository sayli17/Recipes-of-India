package edu.csulb.recipes;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Result extends AppCompatActivity {

    ListView list;
    TextView textView;
    ArrayList<String> items1;
    ArrayList<String> imgid1;
    ArrayList<String> links1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            items1 = extras.getStringArrayList("Names");
            imgid1 = extras.getStringArrayList("Images");
            links1 = extras.getStringArrayList("Links");
        }

        String items[] = new String[items1.size()];
        items = items1.toArray(items);

        String imgid[] = new String[imgid1.size()];
        imgid = imgid1.toArray(imgid);


        CustomListAdapter customListAdapter = new CustomListAdapter(this, items, imgid);
        list = (ListView) findViewById(R.id.listView);

        list.setAdapter(customListAdapter);
        Toast.makeText(this, "Select to view recipe in detail", Toast.LENGTH_LONG).show();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long lid) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(links1.get(position)));
                Intent chooser=Intent.createChooser(i, "Choose an application");
                startActivity(chooser);
            }
        });

    }
}
