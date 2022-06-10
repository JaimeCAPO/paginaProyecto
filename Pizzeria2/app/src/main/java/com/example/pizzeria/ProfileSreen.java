package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileSreen extends AppCompatActivity {

    private ImageView imgUser;

    private User usuarioIni;

    private TextView tvUsername;
    private TextView tvName;
    private TextView tvSurname;
    private TextView tvPhone;
    private TextView tvEmployee;
    private TextView tvStreet;
    private TextView tvTown;
    private TextView tvNumber;
    private TextView tvPostalCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_sreen);

        imgUser=(ImageView) findViewById(R.id.imgUser);

        Intent intent= getIntent();
        setUser(intent.getStringExtra("username"));

        completeCamps();
    }

    public void completeCamps(){
        tvUsername=(TextView) findViewById(R.id.tvCampUsername);
        tvName=(TextView) findViewById(R.id.tvCampName);
        tvSurname=(TextView) findViewById(R.id.tvCampSurname);
        tvPhone=(TextView) findViewById(R.id.tvCampPhone);
        tvEmployee=(TextView) findViewById(R.id.tvCampEmployee);
        tvStreet=(TextView) findViewById(R.id.tvCampStreet);
        tvTown=(TextView) findViewById(R.id.tvCampTown);
        tvNumber=(TextView) findViewById(R.id.tvCampNumber);
        tvPostalCode=(TextView) findViewById(R.id.tvCampPostalCode);

        tvUsername.setText(usuarioIni.getUsername().trim());
        tvName.append(" "+usuarioIni.getName().trim());
        tvSurname.append(" "+usuarioIni.getSurname().trim());
        tvPhone.append(" "+usuarioIni.getPhoneNumber());
        if (usuarioIni.isEmpleado())
            tvEmployee.append(" S");
        else
            tvEmployee.append(" N");
        tvStreet.append(" "+usuarioIni.getDirection().getStreet().trim());
        tvTown.append(" "+usuarioIni.getDirection().getTown().trim());
        tvNumber.append(" "+usuarioIni.getDirection().getNumber());
        tvPostalCode.append(" "+usuarioIni.getDirection().getPostalCode());
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