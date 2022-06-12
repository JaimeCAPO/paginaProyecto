package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class OrdersDescription extends AppCompatActivity {

    private TextView tvIdOrder;
    private TextView tvUsername;
    private TextView tvFullname;
    private TextView tvPhone;
    private TextView tvDir;
    private TextView tvPizzas;
    private TextView tvDrinks;
    private TextView tvOrderPrice;
    private TextView tvComentary;
    private ScrollView miScrollView;
    private Button btnDelete;

    private Orders order;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_description);

        Button btnDelete=(Button) findViewById(R.id.btnDelete);
        miScrollView=(ScrollView) findViewById(R.id.scrollviewPrincipal);
        tvPizzas = (TextView) findViewById(R.id.tvOrderPizzas);
        tvDrinks = (TextView) findViewById(R.id.tvOrderDrinks);

        applyOrder();
        rellenarCampos();

        miScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                findViewById(R.id.tvOrderPizzas).getParent()
                        .requestDisallowInterceptTouchEvent(false);
                findViewById(R.id.tvOrderDrinks).getParent()
                        .requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });

        tvPizzas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        tvPizzas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDelete delete= new DialogDelete();
                FragmentManager fm=getSupportFragmentManager();
                delete.show(fm,"ARE YOU SURE?");
            }
        });
    }

    public void rellenarCampos(){
        if (order!=null) {
            tvIdOrder = (TextView) findViewById(R.id.tvIdOrder);
            tvUsername = (TextView) findViewById(R.id.tvOrderUsername);
            tvFullname = (TextView) findViewById(R.id.tvOrderFullName);
            tvPhone = (TextView) findViewById(R.id.tvOrderContact);
            tvDir = (TextView) findViewById(R.id.tvOrderDir);
            tvPizzas = (TextView) findViewById(R.id.tvOrderPizzas);
            tvDrinks = (TextView) findViewById(R.id.tvOrderDrinks);
            tvOrderPrice = (TextView) findViewById(R.id.tvOrderPrice);
            tvComentary = (TextView) findViewById(R.id.tvComentary);

            tvIdOrder.append(" " + order.getIdPedido());
            tvUsername.append(" " + order.getUserPedido().getUsername());
            tvFullname.append(" " + order.getUserPedido().getName() + " " + order.getUserPedido().getSurname());
            tvPhone.append(" " + order.getUserPedido().getPhoneNumber());
            tvDir.append(" City:" + user.getDirection().getTown() + ", C:" + user.getDirection().getStreet() + ", Nº:" + user.getDirection().getNumber());
            for (Pizza pizza : order.getPizzas()) {
                tvPizzas.append(pizza.getName() + " - - - " + pizza.getPrice() + "$\n");
            }
            for (Drink drink : order.getBebidas()) {
                tvDrinks.append(drink.getName() + " - - - " + drink.getPrice() + "$\n");
            }
            tvComentary.setText(order.getComent());
            tvOrderPrice.append(" " + order.getPrice()+"$");
        }else{
            tvIdOrder = (TextView) findViewById(R.id.tvIdOrder);
            tvUsername = (TextView) findViewById(R.id.tvOrderUsername);
            tvFullname = (TextView) findViewById(R.id.tvOrderFullName);
            tvPhone = (TextView) findViewById(R.id.tvOrderContact);
            tvDir = (TextView) findViewById(R.id.tvOrderDir);
            tvPizzas = (TextView) findViewById(R.id.tvOrderPizzas);
            tvDrinks = (TextView) findViewById(R.id.tvOrderDrinks);
            tvOrderPrice = (TextView) findViewById(R.id.tvOrderPrice);

            tvIdOrder.append(" null");
            tvUsername.append(" null");
            tvFullname.append(" null");
            tvPhone.append(" null");
            tvDir.append(" null");
            tvPizzas.append(" null");
            tvDrinks.append(" null");
            tvOrderPrice.append(" null");
        }
    }

    public void applyOrder(){
        Intent intent=getIntent();
        int idorder=Integer.parseInt(intent.getStringExtra("idorder").trim());

        DataBase dataBase= new DataBase(getApplicationContext());
        SQLiteDatabase db=dataBase.getWritableDatabase();
        Cursor cOrder=db.rawQuery("SELECT * FROM ORDERS WHERE idorder="+idorder,null);
        if (cOrder.moveToFirst()){
            do {
                //obtener informacion de usuario
                Cursor cUser=db.rawQuery("SELECT * FROM USER WHERE username='"+cOrder.getString(1)+"'",null);
                if (cUser.moveToFirst()){
                    do {
                        Cursor cDir=db.rawQuery("SELECT * FROM DIRECTION WHERE directionid="+cUser.getInt(4),null);
                        if (cDir.moveToFirst()){
                            do {
                                Direction direction= new Direction(cDir.getInt(0),cDir.getString(1),cDir.getString(2),cDir.getInt(3),cDir.getInt(4));
                                user=new User(cUser.getString(0),cUser.getString(1),cUser.getString(2),cUser.getString(3),direction,cUser.getInt(5));
                            }while(cDir.moveToNext());
                        }
                    }while (cUser.moveToNext());
                }

                ArrayList<Pizza> pizzas=new ArrayList<Pizza>();
                ArrayList<Drink> drinks= new ArrayList<Drink>();

                //obtener lista de pizzas
                Cursor cContains=db.rawQuery("SELECT * FROM CONTAINS WHERE idorder="+cOrder.getInt(0),null);
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

                //obtener lista de bebidas
                Cursor cAdds=db.rawQuery("SELECT * FROM ADDS WHERE idorder="+cOrder.getInt(0),null);
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

                order=new Orders(cOrder.getInt(0),user,pizzas,drinks,cOrder.getString(2));
                Log.i("ORDER_DESCRIPTION","Order obtention succesfull");
                System.out.println(order);
            }while(cOrder.moveToNext());
            db.close();
            dataBase.close();
        }
    }

    public void back(View view) {
        finish();
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
}