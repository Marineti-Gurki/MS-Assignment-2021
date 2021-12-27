package com.example.helloworld;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddFood extends AppCompatActivity {

    RecyclerView recyclerView;
    FoodDBManager foodDBManager;
    FoodsAdapter fdAdapter;
    EditText addfoodinput;
    String InputString;
    Button button;
    ArrayList<String> item_id, item_name, item_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        FoodDBManager dbManager = new FoodDBManager(this);
        dbManager.open();
        recyclerView = findViewById(R.id.rvPrograms);

        foodDBManager = new FoodDBManager(AddFood.this);
        item_id = new ArrayList<>();
        item_name = new ArrayList<>();
        item_quantity = new ArrayList<>();
        button = (Button) findViewById(R.id.addFood);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addfoodinput = findViewById(R.id.addfoodinput);
                InputString = addfoodinput.getText().toString();
                FoodDBHelper.searchInput(InputString);

                Intent intent = new Intent(AddFood.this, AllFood.class);
                startActivity(intent);
            }
        });
        addFood();

    }

    void addFood() {
        FoodDBHelper foodDBHelper = new FoodDBHelper(this);
        Cursor cursor = foodDBHelper.funcAddThis();
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