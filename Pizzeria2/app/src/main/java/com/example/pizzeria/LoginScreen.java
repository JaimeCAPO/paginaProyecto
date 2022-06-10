package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;

    Button btnLogin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        //editTexts
        etUsername=(EditText) findViewById(R.id.etUsername);
        etPassword=(EditText) findViewById(R.id.etPassword);
        //Buttons
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnRegister=(Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=etUsername.getText().toString().trim();
                String password=etPassword.getText().toString().trim();
                if(username.isEmpty()){
                    Toast.makeText(LoginScreen.this, getString(R.string.userEmpty), Toast.LENGTH_SHORT).show();
                }else{
                    DataBase dataBase= new DataBase(getApplicationContext());
                    SQLiteDatabase db= dataBase.getWritableDatabase();
                    Cursor cUser=db.rawQuery("SELECT * FROM USER WHERE username='"+username+"'",null);
                    if (cUser.moveToFirst()){
                        do {
                            if (password.isEmpty())
                                Toast.makeText(LoginScreen.this, getString(R.string.passwordEmpty), Toast.LENGTH_SHORT).show();
                            else {
                                if (password.trim().equals(cUser.getString(1).trim())) {
                                    Log.i("LOGGIN", "Succesfull");
                                    exitLoggin(username);
                                } else {
                                    Log.i("LOGIN","Failed, wrong password");
                                    Toast.makeText(LoginScreen.this, getString(R.string.incorrectPassword), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }while(cUser.moveToNext());
                    }else{
                        Toast.makeText(LoginScreen.this, getString(R.string.userNotExists), Toast.LENGTH_SHORT).show();
                    }
                    db.close();
                    dataBase.close();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });
    }

    private void openRegister(){
        Intent register= new Intent(getApplicationContext(),RegisterSreen.class);
        startActivity(register);
    }

    private void exitLoggin(String username){
        Intent intent = new Intent();
        System.out.println(username);
        intent.putExtra("username",username);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void back(View view) {
        finish();
    }
}