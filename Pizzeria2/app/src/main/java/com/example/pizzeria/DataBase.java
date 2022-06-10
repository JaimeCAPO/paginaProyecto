package com.example.pizzeria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    private static DataBase sInstance;
    private final String nombreBD="PIZZERIA";
    private Sentencias sentencias;
    private String sentenciaUsers="CREATE TABLE USER(" +
            "username VARCHAR(50) PRIMARY KEY," +
            "password VARCHAR(300) NOT NULL,"+
            "name VARCHAR(50)," +
            "surname VARCHAR(50)," +
            "directionid INTEGER ," +
            "phone INTEGER," +
            "FOREIGN KEY (directionid) REFERENCES DIRECTION (directionid) ON UPDATE CASCADE ON DELETE SET NULL) ";

    private String sentenciaDirections="CREATE TABLE DIRECTION(" +
            "directionid INTEGER PRIMARY KEY AUTOINCREMENT," +
            "street VARCHAR(250)," +
            "town VARCHAR(100) NOT NULL," +
            "number INTEGER," +
            "postalCode INTEGER)";

    private String sentenciaPizza="CREATE TABLE PIZZA(" +
            "pName VARCHAR(50) PRIMARY KEY," +
            "description TEXT NOT NULL," +
            "price DOUBLE NOT NULL)";

    private String sentenciaDrink="CREATE TABLE DRINK(" +
            "dName VARCHAR(50) PRIMARY KEY," +
            "price DOUBLE NOT NULL)";

    private String sentenciaOrders="CREATE TABLE ORDERS(" +
            "idorder INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username VARCHAR(50) NOT NULL, " +
            "coment TEXT," +
            "FOREIGN KEY (username) REFERENCES USER (username) ON UPDATE CASCADE ON DELETE SET NULL)";

    private  String sentenciaContains="CREATE TABLE CONTAINS(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idorder INTEGER NOT NULL, " +
            "pName VARCHAR(50) NOT NULL," +
            "FOREIGN KEY (idorder) REFERENCES ORDERS (idorder) ON UPDATE CASCADE ON DELETE CASCADE," +
            "FOREIGN KEY (pName) REFERENCES PIZZA (pName) ON UPDATE CASCADE ON DELETE SET NULL)";

    private  String sentenciaAdds="CREATE TABLE ADDS(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idorder INTEGER NOT NULL, " +
            "dName VARCHAR(50) NOT NULL," +
            "FOREIGN KEY (idorder) REFERENCES ORDERS (idorder) ON UPDATE CASCADE ON DELETE CASCADE," +
            "FOREIGN KEY (dName) REFERENCES DRINK (dName) ON UPDATE CASCADE ON DELETE SET NULL)";




    public DataBase(@Nullable Context context) {
        super(context, "PIZZERIA", null, 1);
        //constructor
    }

    public static DataBase getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (sInstance == null) {
            sInstance = new DataBase(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sentenciaDirections);
        db.execSQL(sentenciaUsers);
        db.execSQL(sentenciaPizza);
        db.execSQL(sentenciaDrink);
        db.execSQL(sentenciaOrders);
        db.execSQL(sentenciaContains);
        db.execSQL(sentenciaAdds);
        sentencias=new Sentencias();
        sentencias.insertData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USER");
        db.execSQL("DROP TABLE IF EXISTS DIRECTION");
        db.execSQL("DROP TABLE IF EXISTS ADDS");
        db.execSQL("DROP TABLE IF EXISTS CONTAINS");
        db.execSQL("DROP TABLE IF EXISTS PIZZAS");
        db.execSQL("DROP TABLE IF EXISTS DRINKS");
        db.execSQL("DROP TABLE IF EXISTS ORDERS");
        onCreate(db);
    }
}
