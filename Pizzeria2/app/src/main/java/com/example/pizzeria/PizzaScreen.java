package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PizzaScreen extends AppCompatActivity {

    private Button btnCarbonara;
    private Button btnAlemana;
    private Button btnfungui;
    private Button btn4quesos;
    private Button btnMargarita;
    private Button btnHawaiana;
    private Button btnVegetal;
    private Button btnCustom;

    private User usuarioIni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_screen);

        Intent intent=getIntent();
        setUser(intent.getStringExtra("username"));

        btnCarbonara=(Button) findViewById(R.id.btnCarbonara);
        btnAlemana=(Button) findViewById(R.id.btnAlemana);
        btnfungui=(Button) findViewById(R.id.btnChampiñones);
        btn4quesos=(Button) findViewById(R.id.btn4quesos);
        btnMargarita=(Button) findViewById(R.id.btnMargarita);
        btnHawaiana=(Button) findViewById(R.id.btnHawaiana);
        btnVegetal=(Button) findViewById(R.id.btnVegetal);
        btnCustom=(Button) findViewById(R.id.btnCustomiced);

        btnCarbonara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPizzaDescription("Carbonara");
            }
        });
        btnAlemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPizzaDescription("Alemana");
            }
        });
        btnfungui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPizzaDescription("Champiñones");
            }
        });
        btn4quesos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPizzaDescription("Cuatro quesos");
            }
        });
        btnMargarita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPizzaDescription("Margarita");
            }
        });
        btnHawaiana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPizzaDescription("Hawaiana");
            }
        });
        btnVegetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPizzaDescription("Vegetal");
            }
        });
        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usuarioIni!=null){
                    openPizzaCreationScreen();
                }else{
                    Toast.makeText(getApplicationContext(), getString(R.string.notLogged), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void openPizzaDescription(String type){
        Intent intent=new Intent(getApplicationContext(),PizzaDescScreen.class);
        intent.putExtra("type",type.trim());
        startActivity(intent);
    }
    public void openPizzaCreationScreen(){
        Intent intent=new Intent(getApplicationContext(),CreatePizza.class);
        startActivity(intent);
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
                        usuarioIni=new User(cUser.getString(0),cUser.getString(1),cUser.getString(2),cUser.getString(3),direction,cUser.getInt(5));
                        System.out.println(usuarioIni);
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