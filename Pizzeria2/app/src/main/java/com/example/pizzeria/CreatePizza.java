package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePizza extends AppCompatActivity {

    private EditText etPizzaName;
    private EditText etPizzaDesc;

    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pizza);

        btnCreate=(Button) findViewById(R.id.btnCreatePizza);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPizza();
            }
        });
    }

    public void createPizza(){
        etPizzaName=(EditText) findViewById(R.id.etNewPizzaN);
        etPizzaDesc=(EditText) findViewById(R.id.etNewPizzaD);

        String name=etPizzaName.getText().toString().trim();
        String desc=etPizzaDesc.getText().toString().trim();

        if(!name.isEmpty() && !desc.isEmpty()){
            DataBase dataBase=new DataBase(getApplicationContext());
            SQLiteDatabase db=dataBase.getWritableDatabase();

            //Buscar que no exista esa pizza e introducirla.
            Cursor cPizzas=db.rawQuery("SELECT * FROM PIZZA WHERE pName='"+name+"'",null);
            if (cPizzas.moveToFirst())
                Toast.makeText(this, getString(R.string.pizzaExists), Toast.LENGTH_SHORT).show();
            else
                db.execSQL("INSERT INTO PIZZA VALUES('"+name+"','"+desc+"',"+12.00+")");
            //Al acabar cerrar la pestaña e introducir Toast.
            Toast.makeText(this, getString(R.string.pizzaCreated), Toast.LENGTH_SHORT).show();
            Log.i("PIZZA_CREATION","Succesfull Name:"+name);

            db.close();
            dataBase.close();

            finish();

        }else{
            Toast.makeText(this, getString(R.string.CamposIncompletos), Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View view) {
        finish();
    }
}