package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterSreen extends AppCompatActivity {

    DataBase dataBase;

    private User newUser;
    private Direction newDir;
    EditText etNewUsername;
    EditText etNewPassword;
    EditText etNewName;
    EditText etNewSurname;
    EditText etNewPhone;
    EditText etNewStreet;
    EditText etNewTown;
    EditText etNewNumber;
    EditText etNewPostalCode;

    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_sreen);

        dataBase= new DataBase(this.getApplicationContext());

        etNewUsername=(EditText) findViewById(R.id.etNewUsername);
        etNewPassword=(EditText) findViewById(R.id.etNewPassword);
        etNewName=(EditText) findViewById(R.id.etNewName);
        etNewSurname=(EditText) findViewById(R.id.etNewSurname);
        etNewPhone=(EditText) findViewById(R.id.etNewPhone);
        etNewStreet=(EditText) findViewById(R.id.etNewStreet);
        etNewTown=(EditText) findViewById(R.id.etNewTown);
        etNewNumber=(EditText) findViewById(R.id.etNewNumber);
        etNewPostalCode=(EditText) findViewById(R.id.etNewPostalCode);

        btnConfirm= (Button) findViewById(R.id.btnNewConfim);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=etNewUsername.getText().toString().trim();
                String password=etNewPassword.getText().toString().trim();
                String name=etNewName.getText().toString().trim();
                String surname=etNewSurname.getText().toString().trim();
                String street=etNewStreet.getText().toString().trim();
                String town=etNewTown.getText().toString().trim();


                DataBase dataBase= new DataBase(getApplicationContext());
                SQLiteDatabase db=dataBase.getWritableDatabase();

                if (username.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty() || etNewPhone.getText().toString().isEmpty() || street.isEmpty() || town.isEmpty() || etNewNumber.getText().toString().isEmpty() || etNewPostalCode.getText().toString().isEmpty()){
                    Toast.makeText(RegisterSreen.this, getString(R.string.CamposIncompletos), Toast.LENGTH_SHORT).show();
                }else{
                    int phone=Integer.parseInt(etNewPhone.getText().toString().trim());
                    int number=Integer.parseInt(etNewNumber.getText().toString().trim());
                    int postalcode=Integer.parseInt(etNewPostalCode.getText().toString());
                    Cursor cUser=db.rawQuery("SELECT username  FROM USER WHERE username='"+username+"'",null);
                    if (cUser.moveToFirst())
                        Toast.makeText(RegisterSreen.this, getString(R.string.userExists), Toast.LENGTH_SHORT).show();
                    else{
                        //creamos la direccion
                        db.execSQL("INSERT INTO DIRECTION (street,town,number,postalCode) VALUES('"+street+"','"+town+"',"+number+","+postalcode+")");

                        //buscamos esa misma dirección para obtener el directionid que establece automaticamente.
                        Cursor cDir=db.rawQuery("SELECT * FROM DIRECTION WHERE street='"+street+"' and town='"+town+"' and number="+number+" and postalCode="+postalcode,null);
                        if (cDir.moveToFirst()){
                            do {
                                db.execSQL("INSERT INTO USER VALUES('"+username+"','"+password+"','"+name+"','"+surname+"',"+cDir.getInt(0)+","+phone+")");
                                Log.i("REGISTER","Succesfull. User:"+username+" created.");
                                Toast.makeText(RegisterSreen.this, getString(R.string.registerCompleted), Toast.LENGTH_SHORT).show();
                                finish();
                            }while(cDir.moveToNext());
                        }
                    }
                }
            }
        });
    }

    public void back(View view) {
        finish();
    }
}