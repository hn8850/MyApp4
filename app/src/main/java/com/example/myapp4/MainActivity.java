package com.example.myapp4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * @author Harel Navon harelnavon2710@gmail.com
 * @version 1.2
 * @since 18/12/2021 This is a small application desgined to try out custom Alert Dialog options!!
 * The user can input text and it will be turned into a Toast message, as well as choose colors
 * for the background!.
 */


public class MainActivity extends AppCompatActivity {
    AlertDialog.Builder adb1, adb2, adb3;
    AlertDialog ad1, ad2, ad3;
    final String[] namesOfColors = {"Red", "Green", "Blue"};
    LinearLayout linlay;
    String str;
    boolean doublecheck;
    Intent si;
    int[] count = {0, 0, 0};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adb1 = new AlertDialog.Builder(this);
        adb2 = new AlertDialog.Builder(this);
        adb3 = new AlertDialog.Builder(this);
        linlay = findViewById(R.id.ll);
    }



    public void setColor(View view) {
        /**
         * Displays a Dialog box that the user can choose one color (out of red, green and blue)
         * and changes the background color to it.
         */
        int[] color = {0, 0, 0};
        adb1.setTitle("Choose a color!");
        adb1.setCancelable(false);
        adb1.setItems(namesOfColors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                color[i] = 255;
                linlay.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
            }
        });

        ad1 = adb1.create();
        ad1.show();
    }

    public void combo(View view) {
        /**
         * Displays a Dialog box that the user can choose multiple color (out of red, green and blue)
         * and changes the background color to the combination selected.
         */
        int[] color = {0, 0, 0};
        doublecheck = false;
        for (int j = 0; j < 3; j++) count[j] = 0;

        adb2.setCancelable(false);
        adb2.setTitle("Choose some colors!");
        adb2.setMultiChoiceItems(namesOfColors, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                count[which] = count[which] + 1;
                if (count[which] % 2 == 0) count[which] = 0;
                if (isChecked) color[which] = 255;
                else if (color[which] == 255) color[which] = 0;
            }
        });
        adb2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                for (int j = 0; j < 3; j++) {
                    if (count[j] % 2 != 0) doublecheck = true;
                }
                if (doublecheck) {
                    linlay.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
                } else {
                    dialogInterface.dismiss();
                }
            }
        });

        ad2 = adb2.create();
        ad2.show();
    }

    public void reset(View view) {
        /**
         * Resets the background color back to white.
         */
        linlay.setBackgroundColor(Color.WHITE);
    }

    public void tost(View view) {
        /**
         * Displays a Dialog box that the user can input text and have it turned into a Toast.
         */
        adb3.setTitle("Enter some text please");
        adb3.setCancelable(false);
        final EditText et = new EditText(this);
        et.setHint("WRITE HERE");
        adb3.setView(et);
        adb3.setPositiveButton("Toast!!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!et.equals("")) str = et.getText().toString();
                else str = "";
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();


            }
        });

        adb3.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        ad3 = adb3.create();
        ad3.show();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        /**
         * Enables the user to be sent over to the credits Activity when using the options menu.
         */
        si = new Intent(this, credits.class);
        startActivity(si);
        return true;
    }


}