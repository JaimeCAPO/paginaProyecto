package com.example.pizzeria;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class DialogDelete extends DialogFragment{

    DataBase dataBase;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        OrdersDescription desc=(OrdersDescription)getActivity();
        Orders order=desc.getOrder();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("DELETE")
                .setMessage(getString(R.string.questionDelete))
                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (order != null) {
                            dataBase = new DataBase(getActivity());
                            SQLiteDatabase db = dataBase.getWritableDatabase();

                            db.execSQL("DELETE FROM ORDERS WHERE idorder=" + order.getIdPedido());
                            desc.setOrder(null);
                            Log.i("DELETE", "Succesfull");
                            db.close();
                            dataBase.close();
                            desc.finish();
                        }else{
                            Toast.makeText(desc, getString(R.string.orderNotExists), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("DELETE","Canceled");
                        Toast.makeText(getActivity(), "diste no ", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();


    }
}