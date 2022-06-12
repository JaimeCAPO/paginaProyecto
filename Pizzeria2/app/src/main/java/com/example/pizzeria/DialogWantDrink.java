package com.example.pizzeria;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class DialogWantDrink extends DialogFragment {

    DataBase dataBase;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        CreateOrderScreen createOrderScreen=(CreateOrderScreen) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("DRINK")
                .setMessage(getString(R.string.questionDrink))
                .setPositiveButton(getText(R.string.dontDrink), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        createOrderScreen.createOrder();
                    }
                }).setNegativeButton(R.string.getDrink, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("DELETE", "Canceled");

                        Toast.makeText(getActivity(), "diste no ", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}