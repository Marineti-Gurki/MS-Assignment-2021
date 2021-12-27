package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchFood extends AppCompatActivity {

    Button button;
    EditText InputListName;
    String InputString;
    RecyclerView recyclerView;
    FoodDBManager foodDBManager;
    FoodsAdapter fdAdapter;
    ArrayList<String> item_id, item_name, item_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);

        FoodDBManager dbManager = new FoodDBManager(this);
        dbManager.open();
        recyclerView = findViewById(R.id.rvPrograms);


        button = (Button) findViewById(R.id.newlistbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputListName = findViewById(R.id.InputListName);
                InputString = InputListName.getText().toString();
                FoodDBHelper.searchInput(InputString);

                Intent intent = new Intent(SearchFood.this, SearchResults.class);
                startActivity(intent);
            }
        });
    }


}