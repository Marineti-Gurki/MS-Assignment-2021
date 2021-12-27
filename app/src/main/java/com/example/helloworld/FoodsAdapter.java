package com.example.helloworld;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.ViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList item_id, item_name, item_quantity;
    RecyclerView rvPrograms;
    int _id = 0;
    public String _name = "";
    public String _quantity = "";


    public FoodsAdapter(MainActivity activity, Context context ,ArrayList<String> item_id, ArrayList<String> item_name, ArrayList<String> item_quantity) {
        this.activity = activity;
        this.context = context;
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_quantity = item_quantity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowID;
        TextView rowName;
        TextView rowQuantity;
        int position;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowID = itemView.findViewById(R.id.item_id);
            rowName = itemView.findViewById(R.id.item_name);
            rowQuantity = itemView.findViewById(R.id.item_quantity);

            itemView.findViewById(R.id.addfoodtolistbutton).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //gets item id, name and quantity and tries to select that row in the database.
                    FoodDBHelper.justThatPiece(AllFood.getItem_id().get(getAdapterPosition()), AllFood.getItem_name().get(getAdapterPosition()), AllFood.getItem_quantity().get(getAdapterPosition()));
                }
            });

        }
    }

    public FoodsAdapter(Activity activity, Context context, ArrayList item_id, ArrayList item_name, ArrayList item_quantity){
        this.activity = activity;
        this.context = context;
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_quantity = item_quantity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item123, parent, false);
        return new ViewHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull FoodsAdapter.ViewHolder holder, int position) {
        holder.rowID.setText(String.valueOf(item_id.get(position)));
        holder.rowName.setText(String.valueOf(item_name.get(position)));
        holder.rowQuantity.setText(String.valueOf(item_quantity.get(position)));

    }
    @Override
    public int getItemCount() {
        return item_id.size();
    }

}
