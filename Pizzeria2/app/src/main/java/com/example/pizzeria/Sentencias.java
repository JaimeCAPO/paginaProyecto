package com.example.pizzeria;

import android.database.sqlite.SQLiteDatabase;

public class Sentencias {
    public void insertData(SQLiteDatabase db){
        db.execSQL("INSERT INTO PIZZA VALUES('Carbonara','champiñones, salsa carbonara, bacon, cebolla, ajo',9.80)");
        db.execSQL("INSERT INTO DRINK VALUES('Nestea',2.30)");
        db.execSQL("INSERT INTO DIRECTION (street,town,number,postalCode) VALUES('O Sineiro','O Grove',19,36980)");
        db.execSQL("INSERT INTO USER VALUES('jaime','abc123.','Jaime','Cabaleiro',1,645975493)");
        db.execSQL("INSERT INTO ORDERS (username,coment) VALUES('jaime','Sin lactosa')");
        db.execSQL("INSERT INTO ADDS (idorder,dName)VALUES(1,'Nestea')");
        db.execSQL("INSERT INTO CONTAINS (idorder,pName)VALUES(1,'Carbonara')");

    }
}
