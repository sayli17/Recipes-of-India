package edu.csulb.recipes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Fridge extends ActionBarActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    MainActivity mainActivity;
    Result result;
    DatabaseHandler db;

    List<String> Veggies;
    List<String> Meat;
    List<String> Milk_Products;
    List<String> Fruits;
    List<String> Spices;
    List<String> Pantry;

    float percent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expLv);

        LinearLayout footerLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.footerview, null);
        expListView.addFooterView(footerLayout);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, (ArrayList<String>) listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

//        databaseHelper = new DatabaseHelper(getApplicationContext());
//        db = new DatabaseHelper(getApplicationContext()).getReadableDatabase();
        mainActivity = new MainActivity();


        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        db.insertIntoDB(1, "Aamras", "R.drawable.aamras", "http://www.vegrecipesofindia.com/aamras-recipe/");
        db.insertIntoDB(2, "Egg Bhurji", "R.drawable.bhurji", "http://indianhealthyrecipes.com/egg-bhurji-andhra-egg-porutu/");
        db.insertIntoDB(3, "Veg.Biryani", "R.drawable.biryani", "http://www.vegrecipesofindia.com/hyderabadi-veg-biryani-recipe/");
        db.insertIntoDB(4, "Bread Pizza", "R.drawable.breadpizza", "http://www.vegrecipesofindia.com/bread-pizza-recipe-vegetable-bread-pizza/");
        db.insertIntoDB(5, "Chicken Tikka Masala", "R.drawable.chicken", "http://indianhealthyrecipes.com/chicken-tikka-masala-recipe-sanjeev-kapoor/");
        db.insertIntoDB(6, "Fruit Salad/Custard", "R.drawable.fruit", "http://www.vegrecipesofindia.com/fruit-custard-mixed-fruit-custard/");
        db.insertIntoDB(7, "Veg. Kolhapuri", "R.drawable.kolhapuri", "http://www.vegrecipesofindia.com/veg-kolhapuri-recipe/");
        db.insertIntoDB(8, "Veg. Manchurian", "R.drawable.manchurian", "http://www.vegrecipesofindia.com/veg-manchurian-dry-recipe/");
        db.insertIntoDB(9, "Mutton Kheema Pattice", "R.drawable.mutton", "http://www.khaanawaana.com/recipe/kheema-pattice");
        db.insertIntoDB(10, "Paneer Tawa Masala", "R.drawable.paneer", "http://www.vegrecipesofindia.com/tawa-paneer-masala-recipe-paneer-recipes/");
        db.insertIntoDB(11, "Veg. Raita", "R.drawable.raita", "http://www.vegrecipesofindia.com/tomato-raita-recipe/");
        db.insertIntoDB(12, "Veg. toast sandwich", "R.drawable.sandwich", "http://www.vegrecipesofindia.com/bombay-veg-toast-sandwich/");
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Veggies");
        listDataHeader.add("Meat");
        listDataHeader.add("Milk Products");
        listDataHeader.add("Fruits");
        listDataHeader.add("Spices");
        listDataHeader.add("Pantry");

        // Adding child data
        Veggies = new ArrayList<String>();
        Veggies.add("Onion");
        Veggies.add("Tomato");
        Veggies.add("Potato");
        Veggies.add("Carrot");
        Veggies.add("Capsicum");
        Veggies.add("Cabbage");
        Veggies.add("Cauliflower");
        Veggies.add("Green Peas");
        Veggies.add("French Beans");

        Meat = new ArrayList<String>();
        Meat.add("Chicken");
        Meat.add("Mutton");
        Meat.add("Salmon");
        Meat.add("Pomfret");
        Meat.add("Prawns");

        Milk_Products = new ArrayList<String>();
        Milk_Products.add("Cottage Cheese");
        Milk_Products.add("Milk");
        Milk_Products.add("Yogurt");
        Milk_Products.add("Mozzarella");
        Milk_Products.add("Butter");

        Fruits = new ArrayList<String>();
        Fruits.add("Apple");
        Fruits.add("Mango");
        Fruits.add("Banana");
        Fruits.add("Grapes");
        Fruits.add("Strawberry");
        Fruits.add("Blueberry");
        Fruits.add("Watermelon");
        Fruits.add("Pineapple");

        Spices = new ArrayList<String>();
        Spices.add("Chillies");
        Spices.add("Cilantro");
        Spices.add("Mint");
        Spices.add("Ginger");
        Spices.add("Garlic");
        Spices.add("Pepper");
        Spices.add("Cumin");

        Pantry = new ArrayList<String>();
        Pantry.add("Salt");
        Pantry.add("Sugar");
        Pantry.add("Eggs");
        Pantry.add("Rice");
        Pantry.add("Oil");
        Pantry.add("Chickpeas Flour");

        listDataChild.put(listDataHeader.get(0), Veggies);
        listDataChild.put(listDataHeader.get(1), Meat);
        listDataChild.put(listDataHeader.get(2), Milk_Products);
        listDataChild.put(listDataHeader.get(3), Fruits);
        listDataChild.put(listDataHeader.get(4), Spices);
        listDataChild.put(listDataHeader.get(5), Pantry);
    }

    public void onDone(View v) {

        ArrayList<Integer> category_index = mainActivity.selCategory;

        if (listAdapter.selectedItems.size() == 0) {
            new AlertDialog.Builder(Fridge.this)
                    .setTitle("Try again!")
                    .setMessage("Select at least one item")
                    .show();
        }

        String aamras[] = {"Dessert", "mango", "sugar"};
        String bhurji[] = {"Lunch", "eggs", "onion", "oil", "salt", "pepper"};
        String biryani[] = {"Lunch", "rice", "oil", "salt", "onion", "tomato", "potato", "carrot", "cauliflower", "green_peas",
                "french_beans", "yogurt", "cumin", "ginger", "garlic"};
        String breadpizza[] = {"Snacks", "onion", "tomato", "capsicum", "salt"};
        String chicken[] = {"Lunch", "chicken", "onion", "capsicum", "tomato", "chillies", "ginger", "garlic", "salt", "oil", "cumin"};
        String fruit[] = {"Dessert", "apple", "banana", "mango", "grapes", "strawberry", "milk", "sugar"};
        String kolhapuri[] = {"Lunch", "oil", "salt", "chillies", "carrot", "potato", "onion", "tomato", "cauliflower", "green_peas", "french_beans", "ginger", "garlic"};
        String manchurian[] = {"Snacks", "cabbage", "oil", "chillies", "onion", "capsicum", "salt", "ginger", "garlic", "pepper", "sugar"};
        String mutton[] = {"Snacks", "mutton", "onion", "potato", "ginger", "garlic", "oil", "salt"};
        String paneer[] = {"Lunch", "cottage_cheese", "butter", "salt", "onion", "tomato", "capsicum", "ginger", "garlic"};
        String raita[] = {"Lunch", "onion", "tomato", "yogurt", "salt", "sugar", "cilantro"};
        String sandwich[] = {"Breakfast", "onion", "tomato", "potato", "chillies", "mint", "cilantro", "salt", "butter", "capsicum"};

        String category = "";
        ArrayList<String> selectedRecipes = new ArrayList<String>();

        for (int i = 0; i < category_index.size(); i++) {
            switch (category_index.get(i)) {
                case 0:
                    category = "Breakfast";
                    break;
                case 1:
                    category = "Lunch";
                    break;
                case 2:
                    category = "Snacks";
                    break;
                case 3:
                    category = "Dessert";
                    break;
                default:
                    selectedRecipes.add("aamras");
                    selectedRecipes.add("bhurji");
                    selectedRecipes.add("biryani");
                    selectedRecipes.add("breadpizza");
                    selectedRecipes.add("chicken");
                    selectedRecipes.add("fruit");
                    selectedRecipes.add("kolhapuri");
                    selectedRecipes.add("manchurian");
                    selectedRecipes.add("mutton");
                    selectedRecipes.add("paneer");
                    selectedRecipes.add("raita");
                    selectedRecipes.add("sandwich");
            }

            if (aamras[0].equalsIgnoreCase(category)) {
                selectedRecipes.add("aamras");
            } else if (bhurji[0].equalsIgnoreCase(category)) {
                selectedRecipes.add("bhurji");
            } else if (biryani[0].equalsIgnoreCase(category)) {
                selectedRecipes.add("biryani");
            } else if (breadpizza[0].equalsIgnoreCase(category)) {
                selectedRecipes.add("breadpizza");
            } else if (chicken[0].equalsIgnoreCase(category)) {
                selectedRecipes.add("chicken");
            } else if (fruit[0].equalsIgnoreCase(category)) {
                selectedRecipes.add("fruit");
            } else if (kolhapuri[0].equalsIgnoreCase(category)) {
                selectedRecipes.add("kolhapuri");
            } else if (manchurian[0].equalsIgnoreCase(category)) {
                selectedRecipes.add("manchurian");
            } else if (mutton[0].equalsIgnoreCase(category)) {
                selectedRecipes.add("mutton");
            } else if (paneer[0].equalsIgnoreCase(category)) {
                selectedRecipes.add("paneer");
            } else if (raita[0].equalsIgnoreCase(category)) {
                selectedRecipes.add("raita");
            } else {
                selectedRecipes.add("sandwich");
            }
        }

        ArrayList<Integer> finalRecipes = new ArrayList<Integer>();

        for (int i = 0; i < selectedRecipes.size(); i++) {
            int count = 0;
            int totalIngredients = 0;

            if (selectedRecipes.get(i).equalsIgnoreCase("aamras")) {
                totalIngredients = aamras.length - 1;
                ArrayList<String> aamras1 = new ArrayList<String>(Arrays.asList(aamras));
                aamras1.retainAll(listAdapter.selectedItems);
                count = aamras1.size();

                percent = (float) count / totalIngredients;
                if (percent > 0.49) {
                    finalRecipes.add(1);
                }
            } else if (selectedRecipes.get(i).equalsIgnoreCase("bhurji")) {
                totalIngredients = bhurji.length - 1;
                ArrayList<String> bhurji1 = new ArrayList<String>(Arrays.asList(bhurji));
                bhurji1.retainAll(listAdapter.selectedItems);
                count = bhurji1.size();

                percent = (float) count / totalIngredients;
                if (percent > 0.49) {
                    finalRecipes.add(2);
                }
            } else if (selectedRecipes.get(i).equalsIgnoreCase("biryani")) {
                totalIngredients = biryani.length - 1;
                ArrayList<String> biryani1 = new ArrayList<String>(Arrays.asList(biryani));
                biryani1.retainAll(listAdapter.selectedItems);
                count = biryani1.size();

                percent = (float) count / totalIngredients;
                if (percent > 0.49) {
                    finalRecipes.add(3);
                }
            } else if (selectedRecipes.get(i).equalsIgnoreCase("breadpizza")) {
                totalIngredients = breadpizza.length - 1;
                ArrayList<String> breadpizza1 = new ArrayList<String>(Arrays.asList(breadpizza));
                breadpizza1.retainAll(listAdapter.selectedItems);
                count = breadpizza1.size();

                percent = (float) count / totalIngredients;
                if (percent > 0.49) {
                    finalRecipes.add(4);
                }
            } else if (selectedRecipes.get(i).equalsIgnoreCase("chicken")) {
                totalIngredients = chicken.length - 1;
                ArrayList<String> chicken1 = new ArrayList<String>(Arrays.asList(chicken));
                chicken1.retainAll(listAdapter.selectedItems);
                count = chicken1.size();

                percent = (float) count / totalIngredients;
                if (percent > 0.49) {
                    finalRecipes.add(5);
                }
            } else if (selectedRecipes.get(i).equalsIgnoreCase("fruit")) {
                totalIngredients = fruit.length - 1;
                ArrayList<String> fruit1 = new ArrayList<String>(Arrays.asList(fruit));
                fruit1.retainAll(listAdapter.selectedItems);
                count = fruit1.size();

                percent = (float) count / totalIngredients;
                if (percent > 0.49) {
                    finalRecipes.add(6);
                }
            } else if (selectedRecipes.get(i).equalsIgnoreCase("kolhapuri")) {
                totalIngredients = kolhapuri.length - 1;
                ArrayList<String> kolhapuri1 = new ArrayList<String>(Arrays.asList(kolhapuri));
                kolhapuri1.retainAll(listAdapter.selectedItems);
                count = kolhapuri1.size();

                percent = (float) count / totalIngredients;
                if (percent > 0.49) {
                    finalRecipes.add(7);
                }
            } else if (selectedRecipes.get(i).equalsIgnoreCase("manchurian")) {
                totalIngredients = manchurian.length - 1;
                ArrayList<String> manchurian1 = new ArrayList<String>(Arrays.asList(manchurian));
                manchurian1.retainAll(listAdapter.selectedItems);
                count = manchurian1.size();

                percent = (float) count / totalIngredients;
                if (percent > 0.49) {
                    finalRecipes.add(8);
                }
            } else if (selectedRecipes.get(i).equalsIgnoreCase("mutton")) {
                totalIngredients = mutton.length - 1;
                ArrayList<String> mutton1 = new ArrayList<String>(Arrays.asList(mutton));
                mutton1.retainAll(listAdapter.selectedItems);
                count = mutton1.size();

                percent = (float) count / totalIngredients;
                if (percent > 0.49) {
                    finalRecipes.add(9);
                }
            } else if (selectedRecipes.get(i).equalsIgnoreCase("paneer")) {
                totalIngredients = paneer.length - 1;
                ArrayList<String> paneer1 = new ArrayList<String>(Arrays.asList(paneer));
                paneer1.retainAll(listAdapter.selectedItems);
                count = paneer1.size();

                percent = (float) count / totalIngredients;
                if (percent > 0.49) {
                    finalRecipes.add(10);
                }
            } else if (selectedRecipes.get(i).equalsIgnoreCase("raita")) {
                totalIngredients = raita.length - 1;
                ArrayList<String> raita1 = new ArrayList<String>(Arrays.asList(raita));
                raita1.retainAll(listAdapter.selectedItems);
                count = raita1.size();

                percent = (float) count / totalIngredients;
                if (percent > 0.49) {
                    finalRecipes.add(11);
                }
            } else {
                totalIngredients = sandwich.length - 1;
                ArrayList<String> sandwich1 = new ArrayList<String>(Arrays.asList(sandwich));
                sandwich1.retainAll(listAdapter.selectedItems);
                count = sandwich1.size();

                percent = (float) count / totalIngredients;
                if (percent > 0.49) {
                    finalRecipes.add(12);
                }
            }
        }


        db = new DatabaseHandler(getApplicationContext());
        List[] lists = db.getDetails(finalRecipes);
        if (finalRecipes.size() == 0) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Oops! You don't have sufficient ingredients!")
                    .setMessage("Please choose from all our recipes");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();


                    Intent i = new Intent(Fridge.this, Result.class);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("Names", (ArrayList<String>) db.Recipe_name);
                    bundle.putStringArrayList("Images", (ArrayList<String>) db.ImgId);
                    bundle.putStringArrayList("Links", (ArrayList<String>) db.Link);
                    i.putExtras(bundle);
                    startActivity(i);
                }
            });

            builder.show();
        } else {

            Intent i = new Intent(Fridge.this, Result.class);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("Names", (ArrayList<String>) db.Recipe_name);
            bundle.putStringArrayList("Images", (ArrayList<String>) db.ImgId);
            bundle.putStringArrayList("Links", (ArrayList<String>) db.Link);
            i.putExtras(bundle);
            startActivity(i);
        }

    }
}

