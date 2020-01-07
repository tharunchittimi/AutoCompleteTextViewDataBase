package com.example.AutoCompleteTxtDataBase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView myAutoComplete;

    ArrayAdapter<String> myAdapter;

    DatabaseHandler databaseH;

    String[] item = new String[] {"Please search..."};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            databaseH = new DatabaseHandler(MainActivity.this);

            insertSampleData();

            myAutoComplete = (AutoCompleteTextView) findViewById(R.id.myautocomplete);

            myAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));

            myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
            myAutoComplete.setAdapter(myAdapter);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insertSampleData(){

        // CREATE
        databaseH.create( new MyObject("January") );
        databaseH.create( new MyObject("February") );
        databaseH.create( new MyObject("March") );
        databaseH.create( new MyObject("April") );
        databaseH.create( new MyObject("May") );
        databaseH.create( new MyObject("June") );
        databaseH.create( new MyObject("July") );
        databaseH.create( new MyObject("August") );
        databaseH.create( new MyObject("September") );
        databaseH.create( new MyObject("October") );
        databaseH.create( new MyObject("November") );
        databaseH.create( new MyObject("December") );
        databaseH.create( new MyObject("New Caledonia") );
        databaseH.create( new MyObject("New Zealand") );
        databaseH.create( new MyObject("Papua New Guinea") );
        databaseH.create( new MyObject("COFFEE-1K") );
        databaseH.create( new MyObject("coffee raw") );
        databaseH.create( new MyObject("authentic COFFEE") );
        databaseH.create( new MyObject("k12-coffee") );
        databaseH.create( new MyObject("view coffee") );
        databaseH.create( new MyObject("Indian-coffee-two") );

    }

    public String[] getItemsFromDb(String searchTerm){

        List<MyObject> products = databaseH.read(searchTerm);
        int rowCount = products.size();

        String[] item = new String[rowCount];
        int x = 0;

        for (MyObject record : products) {

            item[x] = record.objectName;
            x++;
        }
        return item;
    }

}
