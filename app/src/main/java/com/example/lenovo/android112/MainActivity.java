package com.example.lenovo.android112;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity {
    //declaration
    AutoCompleteTextView autoCompleteTextView;
    private SQLiteItemSearch sqLiteItemSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        //here we creating object of sqLiteItemSearch
        sqLiteItemSearch = new SQLiteItemSearch(MainActivity.this);
        //here we are opening db
        sqLiteItemSearch.openDb();
        //here we are getting all item and puting
        //in the new string product
        final String[] product = sqLiteItemSearch.getAllItemSearch();

        int i = 0;
        //here we are initializating product
        for (i = 0; i < product.length; i++) ;
        {

        }
        //here we are creating an Adapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, product);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1 , int arg2, long arg3) {
                arg0.getItemAtPosition(arg2);
            }

        });
    }
    //here we are creating onDestroy Method
    public void onDestroy() {
        super.onDestroy();
        //here we are closing the db
        sqLiteItemSearch.closeDb();

    }
}
