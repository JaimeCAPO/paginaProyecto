package com.example.pizzeria;

import android.database.sqlite.SQLiteDatabase;

public class Sentencias {
    public void insertData(SQLiteDatabase db){

        //pizzas
        db.execSQL("INSERT INTO PIZZA VALUES('Carbonara','Champiñones, salsa carbonara, bacon, cebolla, ajo',10.00)");
        db.execSQL("INSERT INTO PIZZA VALUES('Margarita','Tomate, jamon, queso',7.80)");
        db.execSQL("INSERT INTO PIZZA VALUES('Alemana','Tomate, queso, bacon, huevo cocido y salchichas',11.75)");
        db.execSQL("INSERT INTO PIZZA VALUES('Cuatro quesos','Queso parmesano, mozzarella, provolone y roqueford',10.10)");
        db.execSQL("INSERT INTO PIZZA VALUES('Hawaiana','Tomate, jamon, queso y piña',8.80)");
        db.execSQL("INSERT INTO PIZZA VALUES('Champiñones','Tomate, champiñones, pimientos rojos y queso',9.80)");
        db.execSQL("INSERT INTO PIZZA VALUES('Vegetal','Queso, tomate, pimiento rojo, verde, amarillo, cebolla, calabacín y brócoli',10.80)");


        db.execSQL("INSERT INTO DRINK VALUES('Nestea',2.30)");
        db.execSQL("INSERT INTO DRINK VALUES('Kas',2.20)");
        db.execSQL("INSERT INTO DRINK VALUES('Coca Cola',2.20)");
        db.execSQL("INSERT INTO DRINK VALUES('Aquarius',2.30)");
        db.execSQL("INSERT INTO DRINK VALUES('Red bull',2.50)");


        db.execSQL("INSERT INTO DIRECTION (street,town,number,postalCode) VALUES('O Sineiro','O Grove',19,36980)");
        db.execSQL("INSERT INTO USER VALUES('jaime','abc123.','Jaime','Cabaleiro',1,645975493)");
        db.execSQL("INSERT INTO ORDERS (username,coment) VALUES('jaime','Sin lactosa')");
        db.execSQL("INSERT INTO ADDS (idorder,dName)VALUES(1,'Nestea')");
        db.execSQL("INSERT INTO CONTAINS (idorder,pName)VALUES(1,'Carbonara')");

    }
}
