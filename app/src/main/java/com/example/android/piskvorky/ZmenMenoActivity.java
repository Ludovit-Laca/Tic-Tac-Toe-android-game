package com.example.android.piskvorky;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ZmenMenoActivity extends AppCompatActivity {

    EditText hrac1;
    EditText hrac2;

    String menoHraca1;
    String menoHraca2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zmen_meno);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        hrac1 = (EditText) findViewById(R.id.editext1);
        hrac2 = (EditText) findViewById(R.id.editext2);
    }

    public void odosliUdaje(View view) {

        menoHraca1 = hrac1.getText().toString().trim();
        menoHraca2 = hrac2.getText().toString().trim();

        if (menoHraca1.equals("")) {
            menoHraca1 = "Hráč 1";
        }
        if (menoHraca2.equals("")) {
            menoHraca2 = "Hráč 2";
        }

        Intent intentNavrat = new Intent();
        intentNavrat.putExtra("newMeno1", menoHraca1);
        intentNavrat.putExtra("newMeno2", menoHraca2);
        setResult(RESULT_OK, intentNavrat);
        finish();
    }

    @Override
    public void onBackPressed() {}
}
