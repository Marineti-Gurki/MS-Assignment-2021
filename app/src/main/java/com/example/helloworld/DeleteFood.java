package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteFood extends AppCompatActivity {
    Button button;
    EditText InputListName;
    String InputString;
    RecyclerView recyclerView;
    FoodDBManager foodDBManager;
    ArrayList<String> item_id, item_name, item_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_food);

        foodDBManager = new FoodDBManager(DeleteFood.this);
        foodDBManager.open();
        recyclerView = findViewById(R.id.rvPrograms);

        item_id = new ArrayList<>();
        item_name = new ArrayList<>();
        item_quantity = new ArrayList<>();

        button = (Button) findViewById(R.id.deletebutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputListName = findViewById(R.id.InputListName);
                InputString = InputListName.getText().toString();
                FoodDBHelper.deleteThis(InputString);

                deleteData();
            }
        });


    }
    // Deletes data from database
    void deleteData() {
        //instansiates fooddbhelper object
        FoodDBHelper foodDBHelper = new FoodDBHelper(this);
        //makes new cursor object and calls deletefunction from fooddbhelper
        Cursor cursor = foodDBHelper.funcdeleteThis();
        if(cursor.getCount() == 0){
            //if nothing there, say no data
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }else{
            //moves onto next collum to delete
            while (cursor.moveToNext()){
                item_id.remove(cursor.getString(0));
                item_name.remove(cursor.getString(1));
                item_quantity.remove(cursor.getString(2));
            }
        }
        System.out.println("Deletion successful");
    }


}