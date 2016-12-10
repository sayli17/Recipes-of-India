package edu.csulb.recipes;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList selectedCategory;
    static ArrayList selCategory;
    RatingBar ratingBar = null;
    EditText editText;
    String color;
    String colortext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        colortext = settings.getString("color", "#f7ed9e");
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mainlayout);
        linearLayout.setBackgroundColor(Color.parseColor(colortext));

    }

    public void onClickHungry(View v) {
        final String items[] = {"Breakfast", "Lunch/Dinner", "Snacks", "Dessert", "All"};

        //array list to keep selected items
        selectedCategory = new ArrayList();

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Select your desired meal course")
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    // indexSelected contains the index of item (of which checkbox checked)
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                        if (isChecked) {
                            selectedCategory.add(indexSelected);
                        } else if (selectedCategory.contains(indexSelected)) {
                            // Else, if the item is already in the array
                            selectedCategory.remove(Integer.valueOf(indexSelected));
                        }

                    }
                });

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if (selectedCategory.size() == 0) {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Try again")
                            .setMessage("Please select at least one category");

                    alertDialog.show();
                } else {

                    Intent i = new Intent(getApplicationContext(), Fridge.class);
                    startActivity(i);
                }

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()

                {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }

        );
        builder.show();
        selCategory = selectedCategory;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.rate:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_rate, null);
                builder.setView(dialogView);

                ratingBar = (RatingBar) dialogView.findViewById(R.id.dialog_ratingbar);
                editText = (EditText) dialogView.findViewById(R.id.editText);

                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        int stars = ratingBar.getProgress();
                        String feedback = editText.getText().toString();
                        dialog.dismiss();
                        new Connection().execute(String.valueOf(stars),feedback,dialog);


                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Thank you")
                                .setMessage("Your feedback has been received");
                        alertDialog.show();

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()

                        {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        }
                );

                AlertDialog b = builder.create();
                b.show();

                return true;

            case R.id.theme:

                final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mainlayout);
                SharedPreferences settings = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                colortext = settings.getString("color", "#f7ed9e");

                if (colortext.equalsIgnoreCase("#f7ed9e")){
                    linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    colortext = "#FFFFFF";
                }else if(colortext.equalsIgnoreCase("#FFFFFF")){
                    linearLayout.setBackgroundColor(Color.parseColor("#f7ed9e"));
                    colortext = "#f7ed9e";
                }

                settings = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("color", colortext);
                editor.commit();

                Toast.makeText(MainActivity.this,"Preference Saved",Toast.LENGTH_LONG).show();

                return true;


            case R.id.uninstall:
                Uri packageUri = Uri.parse("package:edu.csulb.recipes");
                Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageUri);
                startActivity(uninstallIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
