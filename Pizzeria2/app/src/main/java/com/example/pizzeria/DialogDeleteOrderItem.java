package com.example.pizzeria;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class DialogDeleteOrderItem extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        CreateOrderScreen createOrderScreen = (CreateOrderScreen) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("DELETE")
                .setMessage(getString(R.string.deleteOrderItem))
                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("DELETE", "Canceled");
                        Toast.makeText(getActivity(), "diste no ", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();


    }
}