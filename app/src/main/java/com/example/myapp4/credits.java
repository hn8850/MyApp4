package com.example.myapp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.InputStream;


public class credits extends AppCompatActivity {
    Intent si;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        tv = findViewById(R.id.TEXT);
        tv.setText("Author : Harel Navon\n" +
                "\n" +
                "Description: This is a small application desgined to try out custom Alert Dialog options!!");

    }

    public void go(View view) {
        /**
         *  Sends the user to the main activity.
         */
        si = new Intent(this, MainActivity.class);
        startActivity(si);
    }
}


