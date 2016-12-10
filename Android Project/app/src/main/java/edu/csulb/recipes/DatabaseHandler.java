package edu.csulb.recipes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RecipesDB";
    private static final String TABLE_NAME = "Recipe";
    private static final String KEY_ID = "Rid";
    private static final String NAME = "Rname";
    private static final String IMG = "Img";
    private static final String LINK = "Link";

    public List<String> Recipe_name, ImgId, Link;


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY," +
                NAME + " TEXT," + IMG + " TEXT, " + LINK + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertIntoDB(Integer id, String name, String img, String link) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(NAME, name);
        values.put(IMG, img);
        values.put(LINK, link);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List[] getDetails(ArrayList<Integer> finalRecipes) {

        Recipe_name = new ArrayList<String>();
        ImgId = new ArrayList<String>();
        Link = new ArrayList<String>();

        String selectQuery = "";

        if (finalRecipes.size() == 0) {
            selectQuery = "SELECT * FROM " + TABLE_NAME;


        } else {

            for (int i = 0; i < finalRecipes.size(); i++) {

                selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + "=" + finalRecipes.get(i) + " UNION ";
            }
            selectQuery = selectQuery.substring(0, selectQuery.length() - 6);
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Recipe_name.add(cursor.getString(1));
                ImgId.add(cursor.getString(2));
                Link.add(cursor.getString(3));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();


        return new List[]{Recipe_name, ImgId, Link};
    }
}
