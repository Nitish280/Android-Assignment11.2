package com.example.lenovo.android112;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 8/10/2017.
 */

public class SQLiteItemSearch extends SQLiteOpenHelper {
    public SQLiteItemSearch(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "producManager";
    // products table name
    private static final String DATABASE_TABLE = "SearchItem";
    //products Coloumn Name
    private static final String DATABASE_COLUMN_NAME = "item_name";
    private static final String CREATE_DATABASE = (" CREATE TABLE " + DATABASE_TABLE +
            " (item_id INTEGER PRIMARY KEY AUTOINCREMENT," + "item_name TEXT NOT NULL)");
    private SQLiteDatabase db = null;
    //oncreate method
    public void onCreate(SQLiteDatabase db) {
        //creating database
        db.execSQL(CREATE_DATABASE);
        this.db=db;
        //here we are inserting item in the database
        insertSearchItem("Laptop Lenovo");
        insertSearchItem("Laptop Dell");
        insertSearchItem("Laptop Hp");
        insertSearchItem("Laptop Acer");
        insertSearchItem("Television Samsung");
        insertSearchItem("Television Sansui");
        insertSearchItem("Television LG");
    }

   @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       db.execSQL("DROP TABLE IF EXISTS employees");
       onCreate(db);

   }
    //here we are opening the database
    public void openDb() {
        if (this.db == null) {
            this.db = this.getWritableDatabase();
        }
    }
    //here we are close the db
    public void closeDb() {
        if (this.db != null) {
            if (this.db.isOpen())
                this.db.close();
        }

    }
    //here we are creating method insertSearchITem
    public long insertSearchItem(String ItemName) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DATABASE_COLUMN_NAME, ItemName);

        return this.db.insert(DATABASE_TABLE, null, contentValue);
    }

    //here we are creating method getAllItemSearch
    public String[] getAllItemSearch() {
        //here we are selecting a query
        Cursor c = this.db.query(DATABASE_TABLE, new String[]
                {DATABASE_COLUMN_NAME}, null, null, null, null, null);
        //Applying condition
        if (c.getCount() > 0) {
            String[] string = new String[c.getCount()];
            int i = 0;

            while (c.moveToNext()) {
                string[i] = c.getString(c.getColumnIndex(DATABASE_COLUMN_NAME));

                i++;
            }
            return string;
        } else {
            return new String[]{};

        }

    }
}
