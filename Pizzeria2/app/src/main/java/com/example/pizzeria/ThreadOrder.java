package com.example.pizzeria;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class ThreadOrder extends Thread{

    private Orders order;
    private ArrayList<Pizza> pizzas;
    private ArrayList<Drink> drinks;
    private User user;
    private String comentary;
    private DataBase dataBase;

    public ThreadOrder(ArrayList<Pizza> pizzas, ArrayList<Drink> drinks, User user, String comentary, DataBase dataBase) {
        this.pizzas = pizzas;
        this.drinks = drinks;
        this.user = user;
        this.comentary = comentary;
        this.dataBase = dataBase;
    }

    @Override
    public void run() {
        SQLiteDatabase db=dataBase.getWritableDatabase();

        if (pizzas.size()!=0 && pizzas!=null){
            if (drinks.size()!=0 && drinks!=null){

                db.execSQL("INSERT INTO ORDERS (username,coment) VALUES('"+user.getUsername().trim()+"','"+comentary+"')");

                Cursor cOrder=db.rawQuery("SELECT * FROM ORDERS ORDER BY idorder DESC",null);
                if (cOrder.moveToFirst()) {
                    do {
                        order = new Orders(cOrder.getInt(0),user,pizzas,drinks,comentary);
                    } while (cOrder.moveToFirst());
                }
                for (Pizza pizza: pizzas) {
                    db.execSQL("INSERT INTO CONTAINS (idorder,pName)VALUES(" + order.getIdPedido() + ",'" + pizza.getName() + "')");
                }
                for (Drink drink:drinks) {
                    db.execSQL("INSERT INTO ADDS (idorder,dName)VALUES(" + order.getIdPedido() + ",'" + drink.getName() + "')");
                }
                Log.i("CREATE_ORDER","Succesfull. Order:"+order);
            }else{
                //dialog de si quiere prosegir sin bebidas.
            }
        }else{
        }

    }
}
