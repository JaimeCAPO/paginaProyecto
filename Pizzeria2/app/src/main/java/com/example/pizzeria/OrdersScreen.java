package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class OrdersScreen extends AppCompatActivity {

    private TextView tvOrdersVacio;
    private ListView lvOrders;

    private User usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_screen);

        Intent intent=getIntent();
        setUser(intent.getStringExtra("username").trim());

        lvOrders=(ListView) findViewById(R.id.lvOrders);

        ArrayList<Orders> orders=getOrders(usuario);

        for (Orders order: orders){
            System.out.println(order);
        }

        ArrayAdapter<Orders> adapter=new ArrayAdapter<Orders>(this, android.R.layout.simple_list_item_1,orders);
        lvOrders.setAdapter(adapter);

        lvOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //abrir descripcion del order;
                openOrderDesc(orders.get(position));
            }
        });
    }

    public void openOrderDesc(Orders order){
        Intent intOrder= new Intent(getApplicationContext(),OrdersDescription.class);
        intOrder.putExtra("idorder",order.getIdPedido()+"");
        startActivity(intOrder);
    }

    public ArrayList<Orders> getOrders(User usuario){
        tvOrdersVacio=(findViewById(R.id.tvOrderVacio));

        ArrayList<Orders> orders= new ArrayList<Orders>();

        DataBase dataBase= new DataBase(getApplicationContext());
        SQLiteDatabase db=dataBase.getWritableDatabase();

        Cursor cOrders= db.rawQuery("SELECT * FROM ORDERS WHERE username='"+usuario.getUsername()+"'",null);
        if (cOrders.moveToFirst()){
            do {
                ArrayList<Pizza> pizzas=new ArrayList<Pizza>();
                ArrayList<Drink> drinks= new ArrayList<Drink>();

                Cursor cContains=db.rawQuery("SELECT * FROM CONTAINS WHERE idorder="+cOrders.getInt(0),null);
                if (cContains.moveToFirst()){
                    do {
                        Cursor cPizza= db.rawQuery("SELECT * FROM PIZZA WHERE pName='"+cContains.getString(2)+"'",null);
                        if (cPizza.moveToFirst()){
                         do {
                             Pizza pizza=new Pizza(cPizza.getString(0),cPizza.getString(1),cPizza.getDouble(2));
                             pizzas.add(pizza);
                         }while(cPizza.moveToNext());
                        }
                    }while(cContains.moveToNext());
                }
                Cursor cAdds=db.rawQuery("SELECT * FROM ADDS WHERE idorder="+cOrders.getInt(0),null);
                if (cAdds.moveToFirst()){
                    do {
                        Cursor cDrink= db.rawQuery("SELECT * FROM DRINK WHERE dName='"+cAdds.getString(2)+"'",null);
                        if (cDrink.moveToFirst()){
                            do {
                                Drink drink=new Drink(cDrink.getString(0),cDrink.getDouble(1));
                                drinks.add(drink);
                            }while(cDrink.moveToNext());
                        }
                    }while(cContains.moveToNext());
                }

                Orders order=new Orders(cOrders.getInt(0),usuario,pizzas,drinks,cOrders.getString(2));
                orders.add(order);
            }while(cOrders.moveToNext());
        }else
            tvOrdersVacio.setText(getString(R.string.NoOrders));

        db.close();
        dataBase.close();

        return orders;
    }

    public void setUser(String username){
        DataBase dataBase= new DataBase(getApplicationContext());
        SQLiteDatabase db= dataBase.getWritableDatabase();

        Cursor cUser=db.rawQuery("SELECT * FROM USER WHERE username='"+username+"'",null);
        if (cUser.moveToFirst()){
            do {
                Cursor cDir=db.rawQuery("SELECT * FROM DIRECTION WHERE directionid="+cUser.getInt(4),null);
                if (cDir.moveToFirst()){
                    do {
                        Direction direction= new Direction(cDir.getInt(0),cDir.getString(1),cDir.getString(2),cDir.getInt(3),cDir.getInt(4));
                        usuario=new User(cUser.getString(0),cUser.getString(1),cUser.getString(2),cUser.getString(3),direction,cUser.getInt(5));
                        System.out.println(usuario);
                    }while(cDir.moveToNext());
                }
            }while (cUser.moveToNext());
        }

        db.close();
        dataBase.close();
    }

    public void back(View view) {
        finish();
    }
}