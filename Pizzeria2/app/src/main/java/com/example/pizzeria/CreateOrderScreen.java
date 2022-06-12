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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateOrderScreen extends AppCompatActivity {

    private Spinner spinPizzas;
    private Spinner spinDrinks;
    private Button btnAddPizza;
    private Button btnAddDrink;
    private ListView lvPizzas;
    private ListView lvDrinks;
    private EditText etComentary;
    private Button btnCreate;


    private ArrayList<Pizza> pizzasCard;
    private ArrayList<Drink> drinksCard;

    private ArrayList<Pizza> pizzas;
    private ArrayList<Drink> drinks;

    private ArrayList<Pizza> newPizzas;
    private ArrayList<Drink> newDrinks;

    private User user;
    private Orders order;

    private ArrayAdapter<String> listPizzaAdapter;
    private ArrayAdapter<String> listDrinkAdapter;

    private ScrollView miScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order_screen);
        spinPizzas=(Spinner) findViewById(R.id.spinPizzas);
        spinDrinks=(Spinner) findViewById(R.id.spinDrinks);

        lvPizzas=(ListView) findViewById(R.id.lvPizzas);
        lvDrinks=(ListView) findViewById(R.id.lvDrinks);

        pizzas= new ArrayList<Pizza>();
        drinks= new ArrayList<Drink>();

        Intent intent= getIntent();
        setUser(intent.getStringExtra("username"));

        llenarCatalogoPizzas();
        llenarCatalogoDrinks();

        miScrollView =(ScrollView) findViewById(R.id.scrollviewPrincipal);

        miScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                findViewById(R.id.lvPizzas).getParent()
                        .requestDisallowInterceptTouchEvent(false);
                findViewById(R.id.lvDrinks).getParent()
                        .requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });

        lvPizzas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        lvDrinks.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        lvPizzas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                newPizzas= new ArrayList<Pizza>();
                for(Pizza pizza:pizzas){
                    if(pizza.equals(pizzas.get(i))){
                        continue;
                    }
                    newPizzas.add(pizza);
                }
                pizzas=newPizzas;
                ArrayList<String> pizzaSelected=new ArrayList<String>();
                for (Pizza selPizza:newPizzas){
                    pizzaSelected.add(selPizza.getName().trim());
                }
                listPizzaAdapter= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,pizzaSelected);
                lvPizzas.setAdapter(listPizzaAdapter);
            }
        });

        lvDrinks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                newDrinks= new ArrayList<Drink>();
                for(Drink drink:drinks){
                    if(drink.equals(drinks.get(i))){
                        continue;
                    }
                    newDrinks.add(drink);
                }
                drinks=newDrinks;
                ArrayList<String> drinkSelected=new ArrayList<String>();
                for (Drink drink:newDrinks){
                    drinkSelected.add(drink.getName().trim());
                }
                listDrinkAdapter= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,drinkSelected);
                lvDrinks.setAdapter(listDrinkAdapter);
            }
        });


        btnAddPizza=(Button) findViewById(R.id.btnAddPizza);
        btnAddDrink=(Button) findViewById(R.id.btnAddDrink);
        btnCreate=(Button) findViewById(R.id.btnCreateOrder);

        btnAddPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pizzaName=spinPizzas.getSelectedItem().toString().trim();
                for (Pizza pizza:pizzasCard){
                    if (pizza.getName().equals(pizzaName.trim()))
                        pizzas.add(pizza);
                    ArrayList<String> pizzaSelected=new ArrayList<String>();
                    for (Pizza selPizza:pizzas){
                        pizzaSelected.add(selPizza.getName().trim());
                    }
                    listPizzaAdapter= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,pizzaSelected);
                    lvPizzas.setAdapter(listPizzaAdapter);

                }
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(user);
                System.out.println(pizzas);
                System.out.println(drinks);
                if (pizzas.size()!=0 && pizzas!=null){
                    if (drinks.size()!=0 && drinks!=null){
                        createOrder();
                    }else{
                        youWontDrink();
                    }
                }else{
                    Toast.makeText(CreateOrderScreen.this, getString(R.string.pizzasListEmpty), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAddDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String drinkName=spinDrinks.getSelectedItem().toString().trim();
                for (Drink drink:drinksCard){
                    if (drink.getName().equals(drinkName.trim()))
                        drinks.add(drink);
                    ArrayList<String> drinkSelected=new ArrayList<String>();
                    for (Drink selDrink:drinks){
                        drinkSelected.add(selDrink.getName().trim());
                    }
                    listDrinkAdapter= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,drinkSelected);
                    lvDrinks.setAdapter(listDrinkAdapter);

                }
            }
        });

    }


    public void youWontDrink(){
        DialogWantDrink wantDrink= new DialogWantDrink();
        FragmentManager fm=getSupportFragmentManager();
        wantDrink.show(fm,"ARE YOU SURE?");
    }

    public void createOrder(){
        etComentary=(EditText) findViewById(R.id.etComentary);
        DataBase dataBase= new DataBase(getApplicationContext());
        SQLiteDatabase db=dataBase.getWritableDatabase();

        db.execSQL("INSERT INTO ORDERS (username,coment) VALUES('"+user.getUsername().trim()+"','"+etComentary.getText().toString()+"');");

        Cursor cOrder=db.rawQuery("SELECT * FROM ORDERS ORDER BY idorder DESC;",null);
        if (cOrder.moveToFirst()) {
            order = new Orders(cOrder.getInt(0),user,pizzas,drinks,etComentary.getText().toString().trim());
        }

        for (Pizza pizza: pizzas) {
            db.execSQL("INSERT INTO CONTAINS (idorder,pName)VALUES(" + order.getIdPedido() + ",'" + pizza.getName() + "');");
        }
        for (Drink drink:drinks) {
            db.execSQL("INSERT INTO ADDS (idorder,dName)VALUES(" + order.getIdPedido() + ",'" + drink.getName() + "');");
        }
        Toast.makeText(CreateOrderScreen.this, getString(R.string.orderCreated), Toast.LENGTH_SHORT).show();
        Log.i("CREATE_ORDER","Succesfull. Order:"+order);

        db.close();
        dataBase.close();
        finish();
    }


    public void llenarCatalogoPizzas(){
        spinPizzas=(Spinner) findViewById(R.id.spinPizzas);

        pizzasCard=new ArrayList<Pizza>();

        DataBase dataBase=new DataBase(getApplicationContext());
        SQLiteDatabase db=dataBase.getWritableDatabase();

        ArrayList<String> pizzasNames=new ArrayList<String>();
        Cursor cPizzas= db.rawQuery("SELECT * FROM PIZZA",null);
        if (cPizzas.moveToFirst()){
            do {
                Pizza object=new Pizza(cPizzas.getString(0),cPizzas.getString(1),cPizzas.getDouble(2));
                String name=cPizzas.getString(0);
                pizzasCard.add(object);
                pizzasNames.add(name);

            }while (cPizzas.moveToNext());
        }

        ArrayAdapter<String> pizzasAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,pizzasNames);
        spinPizzas.setAdapter(pizzasAdapter);
        db.close();
        dataBase.close();
    }

    public void llenarCatalogoDrinks(){
        spinDrinks=(Spinner) findViewById(R.id.spinDrinks);

        DataBase dataBase=new DataBase(getApplicationContext());
        SQLiteDatabase db=dataBase.getWritableDatabase();

        drinksCard=new ArrayList<Drink>();

        ArrayList<String> drinksNames=new ArrayList<String>();
        Cursor cDrink= db.rawQuery("SELECT * FROM DRINK",null);
        if (cDrink.moveToFirst()){
            do {
                Drink object=new Drink(cDrink.getString(0),cDrink.getDouble(1));
                String name=cDrink.getString(0);
                drinksCard.add(object);
                drinksNames.add(name);

            }while (cDrink.moveToNext());
        }

        ArrayAdapter<String> drinksAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,drinksNames);
        spinDrinks.setAdapter(drinksAdapter);
        db.close();
        dataBase.close();
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
                        user=new User(cUser.getString(0),cUser.getString(1),cUser.getString(2),cUser.getString(3),direction,cUser.getInt(5));
                        System.out.println(user);
                    }while(cDir.moveToNext());
                }
            }while (cUser.moveToNext());
        }
        db.close();
        dataBase.close();
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<Drink> drinks) {
        this.drinks = drinks;
    }

    public ArrayList<Pizza> getNewPizzas() {
        return newPizzas;
    }

    public ArrayList<Drink> getNewDrinks() {
        return newDrinks;
    }

    public void back(View view) {
        finish();
    }
}