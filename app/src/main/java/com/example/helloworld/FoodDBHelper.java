package com.example.helloworld;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
//import android.content.ContentValues;
//import android.database.DatabaseErrorHandler;
//import android.database.Cursor;
//import java.util.ArrayList;
//import java.util.List;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;

public class FoodDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "FoodDB.db";
    public static final String TABLE_FOOD = "FoodDB";
    public static final String TABLE_FOODLIST = "FoodListDB";
    public static final String KEY_ID = "code";
    public static final String KEY_NAME = "product_name";
    public static final String KEY_QUANTITY = "quantity";
    public static final String KEY_BRANDS = "brands";
    public static final String KEY_CATEGORIES = "categories";
    public static final String KEY_LABELS = "labels";
    public static final String KEY_STORES = "stores";
    public static String USER_SEARCH = null;
    public static String USER_DELETE = null;
    public static String ITEM_ID = null;
    public static String ITEM_NAME = null;
    public static String ITEM_QUANTITY = null;
    public static String ADD_ITEM_ID = null;
    public static String ADD_ITEM_NAME = null;
    public static String ADD_ITEM_QUANTITY = null;

    private static SQLiteOpenHelper mcontext;

    public SQLiteDatabase mDatabase;



    public FoodDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        //creates two tables
        String CREATE_FOOD_TABLE = "CREATE TABLE " + TABLE_FOOD + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_QUANTITY + " TEXT"
                + ")";

        String CREATE_FOODLIST_TABLE = "CREATE TABLE " + TABLE_FOODLIST + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_QUANTITY + " TEXT"
                + ")";
        DB.execSQL(CREATE_FOOD_TABLE);
        System.out.println("Table created successfully");
        DB.execSQL(CREATE_FOODLIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        // Drop existing old tables
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_FOODLIST);
        // Creates tables again
        onCreate(DB);
    }

    Cursor readAllData() {
        //reads all data in db and sends it to cursor
        String query = "SELECT * FROM " + TABLE_FOOD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    Cursor justThatPiece2() {
        // adds this sqlite query into a string variable
        String query = "INSERT INTO " + TABLE_FOODLIST + "(code, product_name, quantity) VALUES("+ ITEM_ID+ "," + ITEM_NAME + ","+ITEM_QUANTITY + ")";
        // grabs this database
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        //returns the information
        return cursor;
    }

    public static void justThatPiece(String thisITEM_ID, String thisITEM_NAME, String thisITEM_QUANTITY){
        // kinda constructor for getting item id, name and quantity to adding to my list.
        ITEM_ID=thisITEM_ID;
        ITEM_NAME=thisITEM_NAME;
        ITEM_QUANTITY=thisITEM_QUANTITY;
    }

    // puts user search into variable
    public static void searchInput(String searchInput){
        USER_SEARCH = searchInput;
    }

    //searches for data in database that matches the user search
    Cursor SearchData() {
        // adds this sqlite query into a string variable
        String query = "SELECT * FROM " + TABLE_FOOD + " WHERE product_name " + "LIKE " + "'%"+USER_SEARCH+"%'";
        // grabs this database
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        //returns the information
        return cursor;
    }

    public static void addThis(String itemID, String itemName, String itemQuantity){
        ADD_ITEM_ID = itemID;
        ADD_ITEM_NAME = itemName;
        ADD_ITEM_QUANTITY = itemQuantity;
    }

    Cursor funcAddThis(){
        // adds this sqlite query into a string variable which adds to rows with user input
        String query = "INSERT INTO " + TABLE_FOOD+"(code, product_name, quantity) VALUES"+"("+ADD_ITEM_ID+","+ADD_ITEM_NAME+","+ADD_ITEM_QUANTITY+")";
        // grabs this database
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        //returns the information
        return cursor;
    }


    public static void deleteThis(String inputString) {
        //passes on the user input for deletion
        USER_DELETE = inputString;
    }

    Cursor funcdeleteThis() {
        // adds this sqlite query into a string variable which deletes all rows that include what the user puts in
        String query = "DELETE FROM " + TABLE_FOOD + " WHERE product_name " + "LIKE " + "'%"+USER_DELETE+"%'";
        // grabs this database
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        //returns the information
        return cursor;
    }
}
