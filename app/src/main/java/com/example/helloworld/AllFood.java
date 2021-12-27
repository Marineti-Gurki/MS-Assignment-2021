package com.example.helloworld;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AllFood extends AppCompatActivity {

    private static ArrayList<String> item_id, item_name, item_quantity;
    RecyclerView recyclerView;
    FoodDBManager foodDBManager;
    FoodsAdapter fdAdapter;

//    ArrayList<String> item_id, item_name, item_quantity;

    FloatingActionButton addfoodtolistbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_food);

        FoodDBManager dbManager = new FoodDBManager(this);
        dbManager.open();
        recyclerView = findViewById(R.id.rvPrograms);

        foodDBManager = new FoodDBManager(AllFood.this);
        item_id = new ArrayList<>();
        item_name = new ArrayList<>();
        item_quantity = new ArrayList<>();

        storeDataInArrays();
        fdAdapter = new FoodsAdapter(AllFood.this, this, item_id, item_name, item_quantity);
        recyclerView.setAdapter(fdAdapter);
        recyclerView.setLayoutManager((new LinearLayoutManager(AllFood.this)));
        addtolist();
//        addfoodtolistbutton = findViewById(R.id.addfoodtolistbutton);
//        addfoodtolistbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //todo
//                System.out.println(item_id.get(1));
//            }
//        });
    }

    void storeDataInArrays() {
        FoodDBHelper foodDBHelper = new FoodDBHelper(this);
        Cursor cursor = foodDBHelper.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                item_id.add(cursor.getString(0));
                item_name.add(cursor.getString(1));
                item_quantity.add(cursor.getString(2));
            }
        }
    }

    void addtolist(){
        FoodDBHelper foodDBHelper = new FoodDBHelper(this);
        Cursor cursor = foodDBHelper.justThatPiece2();

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

    public static ArrayList<String> getItem_id() {
        return item_id;
    }

    public static ArrayList<String> getItem_name() {
        return item_name;
    }

    public static ArrayList<String> getItem_quantity() {
        return item_quantity;
    }
}