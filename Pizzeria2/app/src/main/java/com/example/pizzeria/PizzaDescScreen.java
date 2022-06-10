package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PizzaDescScreen extends AppCompatActivity {

    private Pizza pizza;

    private ImageView imgPizza;

    private TextView tvPizza;
    private TextView tvDescription;
    private TextView tvPizzaPrice;

    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_desc_screen);

        imgPizza=(ImageView) findViewById(R.id.imgPizza);

        getPizza();
        completeCamps();
    }

    public void getPizza(){
        Intent intent=getIntent();
        String pName=intent.getStringExtra("type").trim();

        dataBase=new DataBase(getApplicationContext());
        SQLiteDatabase db=dataBase.getWritableDatabase();

        Cursor cPizza=db.rawQuery("SELECT * FROM PIZZA WHERE pName='"+pName+"'",null);
        if(cPizza.moveToFirst()){
            do {
                pizza= new Pizza(cPizza.getString(0),cPizza.getString(1),cPizza.getDouble(2));
            }while (cPizza.moveToNext());
        }
        db.close();
        dataBase.close();
    }

    public void completeCamps(){
        if(pizza!=null){
            tvPizza=(TextView) findViewById(R.id.pizzaName);
            tvDescription=(TextView) findViewById(R.id.tvPizzaDescription);
            tvPizzaPrice=(TextView) findViewById(R.id.tvPizzaPrice);

            tvPizza.setText(pizza.getName());
            tvDescription.setText(pizza.getDescription());
            tvPizzaPrice.append(pizza.getPrice()+"");

        }
    }

    public void back(View view) {
        finish();
    }
}