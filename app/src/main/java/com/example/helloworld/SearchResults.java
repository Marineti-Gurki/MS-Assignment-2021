package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchResults extends AppCompatActivity {


    RecyclerView recyclerView;
    FoodDBManager foodDBManager;
    FoodsAdapter fdAdapter;
    ArrayList<String> item_id, item_name, item_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_food);

        FoodDBManager dbManager = new FoodDBManager(this);
        dbManager.open();
        recyclerView = findViewById(R.id.rvPrograms);

        foodDBManager = new FoodDBManager(SearchResults.this);
        item_id = new ArrayList<>();
        item_name = new ArrayList<>();
        item_quantity = new ArrayList<>();

        storesearchInArrays();
        fdAdapter = new FoodsAdapter(SearchResults.this,this,item_id, item_name, item_quantity);
        recyclerView.setAdapter(fdAdapter);
        recyclerView.setLayoutManager((new LinearLayoutManager(SearchResults.this)));
    }

    void storesearchInArrays(){
        FoodDBHelper foodDBHelper = new FoodDBHelper(this);
        Cursor cursor = foodDBHelper.SearchData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                item_id.add(cursor.getString(0));
                item_name.add(cursor.getString(1));
                item_quantity.add(cursor.getString(2));
            }
        }
    }

}