package com.example.pizzeria;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnPizzas;
    private Button btnPedidos;
    private Button btnCreateOrder;

    //usuario de la sesión.
    private User usuarioIni;

    private final int COD_LOG=1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(R.id.itemLogin==id){
            //crear intent de login.
                if(usuarioIni!=null){
                    usuarioIni=null;
                    Toast.makeText(this, getString(R.string.unLogged), Toast.LENGTH_SHORT).show();
                }else{
                    openLoggin();
                }

        }else if(R.id.itemProfile==id){
            //crear intent de perfil
            if (usuarioIni!=null){
                openProfile();
            }else{
                Toast.makeText(this, getString(R.string.notLogged), Toast.LENGTH_SHORT).show();
            }
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDB();

        btnPizzas= (Button) findViewById(R.id.btnPizzas);
        btnCreateOrder=(Button) findViewById(R.id.btnCreateOrder);
        btnPedidos= (Button) findViewById(R.id.btnPedido);

        btnPizzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //abrir screen pizzas
                if(usuarioIni!=null)
                    openCard(usuarioIni.getUsername().trim());
                else
                    openCard();
            }
        });

        btnCreateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usuarioIni!=null){
                    openCreateOrder();
                }else{
                    Toast.makeText(MainActivity.this, getString(R.string.notLogged), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mirar si estas loggeado o no y mandar mensaje
                if(usuarioIni!=null)
                    openOrders();
                else
                    Toast.makeText(MainActivity.this, getString(R.string.notLogged), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openCreateOrder(){
        Intent intent= new Intent(getApplicationContext(),CreateOrderScreen.class);
        intent.putExtra("username",usuarioIni.getUsername().trim());
        startActivity(intent);
    }

    public void createDB(){
        DataBase dataBase= new DataBase(getApplicationContext());
        SQLiteDatabase db= dataBase.getWritableDatabase();
        try{
            dataBase.onCreate(db);
        }catch(Exception error){}
    }

    public void openLoggin(){
        Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
        startActivityForResult(intent,COD_LOG);
    }

    public void openProfile(){
        Intent profile= new Intent(getApplicationContext(),ProfileSreen.class);
        profile.putExtra("username",usuarioIni.getUsername().trim());
        startActivity(profile);
    }
    public void openOrders(){
        Intent orders= new Intent(getApplicationContext(),OrdersScreen.class);
        orders.putExtra("username",usuarioIni.getUsername().trim());
        startActivity(orders);
    }

    public void openCard(){
        Intent cards= new Intent(getApplicationContext(),PizzaScreen.class);
        startActivity(cards);
    }

    public void openCard(String username){
        Intent cards= new Intent(getApplicationContext(),PizzaScreen.class);
        cards.putExtra("username",username);
        startActivity(cards);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COD_LOG) {
            if (resultCode == RESULT_OK) {
                if (data.hasExtra("username")){
                    //obtener usuario;
                    setUser(data.getStringExtra("username").trim());
                }
            } else
                Toast.makeText(this, getString(R.string.backNLoggin), Toast.LENGTH_SHORT).show();
        }
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
}