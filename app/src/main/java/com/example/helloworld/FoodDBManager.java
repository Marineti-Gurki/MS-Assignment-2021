package com.example.helloworld;

import static com.example.helloworld.FoodDBHelper.KEY_BRANDS;
import static com.example.helloworld.FoodDBHelper.KEY_CATEGORIES;
import static com.example.helloworld.FoodDBHelper.KEY_ID;
import static com.example.helloworld.FoodDBHelper.KEY_LABELS;
import static com.example.helloworld.FoodDBHelper.KEY_NAME;
import static com.example.helloworld.FoodDBHelper.KEY_QUANTITY;
import static com.example.helloworld.FoodDBHelper.KEY_STORES;
import static com.example.helloworld.FoodDBHelper.TABLE_FOOD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class FoodDBManager {
    Context context;
    private FoodDBHelper fooddbhelper;
    private SQLiteDatabase database;
    List<Food> foodList = new ArrayList<>();

    public FoodDBManager(Context context) {
        this.context = context;
    }

    public FoodDBManager open() throws SQLException {
        fooddbhelper = new FoodDBHelper(context);
        database = fooddbhelper.getWritableDatabase();
        return this;
    }

    public void close() {
        fooddbhelper.close();
    }

    void addFood(Food food) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.get_name());
        values.put(KEY_QUANTITY, food.get_quantity());

        long result = database.insert(TABLE_FOOD, null, values);
        if(result == -1) Toast.makeText(context, "DB insert Failed", Toast.LENGTH_SHORT).show();
    }

    Food getFood(int id) {
        Cursor cursor = database.query(TABLE_FOOD, new String[]{KEY_ID, KEY_NAME, KEY_QUANTITY}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        Food food = new Food(
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return food;
    }

    public Cursor getAllFoods(){
        String selectQuery = "SELECT * FROM " + TABLE_FOOD;
        SQLiteDatabase db = this.fooddbhelper.getReadableDatabase();

        Cursor cursor = null;
        if(db !=null){
            cursor = db.rawQuery(selectQuery, null);
        }
        return cursor;
    }


    public int updateFood(Food food) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.get_name());
        values.put(KEY_QUANTITY, food.get_quantity());

        return database.update(TABLE_FOOD, values, KEY_ID + " = ?", new String[]{String.valueOf(food.getId())});
    }

    public void deleteFood(Food food) {
        database.delete(TABLE_FOOD, KEY_ID + " = ?", new String[]{String.valueOf(food.getId())});
    }

    public int getFoodsCount() {
        String countQuery = "SELECT * FROM " + TABLE_FOOD;

        Cursor cursor = database.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

}
