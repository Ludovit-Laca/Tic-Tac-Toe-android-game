package com.example.android.piskvorky;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class VitazActivity extends AppCompatActivity {

    TextView vitazText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitaz);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        vitazText = (TextView) findViewById(R.id.textPreVitaza);
        String meno = getIntent().getStringExtra("vitaz");
        vitazText.setText(meno);
    }

    public void newGame(View view) {
        Intent intentNavrat = new Intent();
        setResult(RESULT_OK,intentNavrat);
        finish();
    }

    @Override
    public void onBackPressed() {}
}
