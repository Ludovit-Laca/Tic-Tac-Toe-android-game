package com.example.android.piskvorky;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private int ID;
    private TextView text;
    private TextView hracText;
    private boolean hracNaRade = true; // zistuje ktorý hráč je na rade
    public String hrac1Name = "Hráč 1";
    public String hrac2Name = "Hráč 2";
    public String pole[][] = { {"N", "N", "N" }, {"N", "N", "N" }, {"N", "N", "N" } };
    private int counter = 0; // počíta koľko políčok je plných
    private int x = 0; // x-ova súradnica
    private int y = 0; // y-ova súradnica

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        hracText = (TextView) findViewById(R.id.ktoJeNaRade);
        hracText.setText(hrac1Name + " je na rade!");
    }

    public void zmenMeno(View view) {
        Intent intentMeno = new Intent(this, ZmenMenoActivity.class);
        startActivityForResult(intentMeno,1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle vrateneData = data.getExtras();
                    hrac1Name = vrateneData.getString("newMeno1");
                    hrac2Name = vrateneData.getString("newMeno2");
                    if (hracNaRade) {
                        hracText.setText(hrac1Name + " je na rade!");
                    } else {
                        hracText.setText(hrac2Name + " je na rade!");
                    }
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    newGame();
                }
                break;
        }
    }

    public void zmenOkienko(View view) {
        ID = view.getId(); // podľa ID rozhodne o aké okienko ide :)

        if (ID == R.id.okienko1) {
            text = (TextView) findViewById(R.id.okienko1_view);
            x = 0;
            y = 0;
        } else if (ID == R.id.okienko2) {
            text = (TextView) findViewById(R.id.okienko2_view);
            x = 0;
            y = 1;
        } else if (ID == R.id.okienko3) {
            text = (TextView) findViewById(R.id.okienko3_view);
            x = 0;
            y = 2;
        } else if (ID == R.id.okienko4) {
            text = (TextView) findViewById(R.id.okienko4_view);
            x = 1;
            y = 0;
        } else if (ID == R.id.okienko5) {
            text = (TextView) findViewById(R.id.okienko5_view);
            x = 1;
            y = 1;
        } else if (ID == R.id.okienko6) {
            text = (TextView) findViewById(R.id.okienko6_view);
            x = 1;
            y = 2;
        } else if (ID == R.id.okienko7) {
            text = (TextView) findViewById(R.id.okienko7_view);
            x = 2;
            y = 0;
        } else if (ID == R.id.okienko8) {
            text = (TextView) findViewById(R.id.okienko8_view);
            x = 2;
            y = 1;
        } else {
            text = (TextView) findViewById(R.id.okienko9_view);
            x = 2;
            y = 2;
        }
        urobZmenu(text,x,y);
    }

    private void urobZmenu(TextView view, int xi, int yi) {
        if (view.getText().toString().isEmpty()) {
            if (hracNaRade) {
                view.setText("X");
                pole[xi][yi] = "X";
                hracNaRade = false;
                hracText.setText(hrac2Name + " je na rade!");
            }
            else {
                view.setText("O");
                pole[xi][yi] = "O";
                hracNaRade = true;
                hracText.setText(hrac1Name + " je na rade!");
            }
            counter++;
            if (counter == 9) {
                odosli("Hra skončila remízou!");
            } else {
                overHru();
            }

        }
    }

    private void overHru() {
        int pocetSpravnychX1 = 0;
        int pocetSpravnychY1 = 0;
        int pocetSpravnychX2 = 0;
        int pocetSpravnychY2 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (pole[i][j].equals("X")) {
                    pocetSpravnychX1++;
                } else if (pole[i][j].equals("O")) {
                    pocetSpravnychY1++;
                }

                if (pole[j][i].equals("X")) {
                    pocetSpravnychX2++;
                } else if (pole[j][i].equals("O")) {
                    pocetSpravnychY2++;
                }
            }
            if (pocetSpravnychX1 == 3 || pocetSpravnychX2 == 3) {
                odosli(hrac1Name + " vyhráva!");
                break;
            } else if (pocetSpravnychY1 == 3 ||pocetSpravnychY2 == 3) {
                odosli(hrac2Name + " vyhráva!");
                break;
            } else {
                pocetSpravnychX1 = 0;
                pocetSpravnychY1= 0;
                pocetSpravnychX2 = 0;
                pocetSpravnychY2 = 0;
                String odpoved = overKrizom();
                if (!(odpoved.equals("nič"))) {
                    odosli(odpoved);
                    break;
                }
            }

        }
    }

    private String overKrizom() {
        if (pole[0][0] == "X" && pole[1][1] == "X" && pole[2][2] == "X") {
            return(hrac1Name + " vyhráva!");
        } else if (pole[0][0] == "O" && pole[1][1] == "O" && pole[2][2] == "O") {
            return(hrac2Name + " vyhráva!");
        } else if (pole[0][2] == "X" && pole[1][1] == "X" && pole[2][0] == "X") {
            return(hrac1Name + " vyhráva!");
        } else if (pole[0][2] == "O" && pole[1][1] == "O" && pole[2][0] == "O") {
            return(hrac2Name + " vyhráva!");
        }
        return "nič";
    }

    public void odosli(String ivitaz) {
        Intent intent = new Intent(this, VitazActivity.class);
        intent.putExtra("vitaz", ivitaz);
        startActivityForResult(intent,2);
    }

    @Override
    public void onBackPressed() {}

    public void newGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pole[i][j] = "N";
            }
        }
        text = (TextView) findViewById(R.id.okienko1_view);
        text.setText("");
        text = (TextView) findViewById(R.id.okienko2_view);
        text.setText("");
        text = (TextView) findViewById(R.id.okienko3_view);
        text.setText("");
        text = (TextView) findViewById(R.id.okienko4_view);
        text.setText("");
        text = (TextView) findViewById(R.id.okienko5_view);
        text.setText("");
        text = (TextView) findViewById(R.id.okienko6_view);
        text.setText("");
        text = (TextView) findViewById(R.id.okienko7_view);
        text.setText("");
        text = (TextView) findViewById(R.id.okienko8_view);
        text.setText("");
        text = (TextView) findViewById(R.id.okienko9_view);
        text.setText("");
        counter = 0;

        String pom = hrac1Name;
        hrac1Name = hrac2Name;
        hrac2Name = pom;
        hracNaRade = true;
        hracText.setText(hrac1Name + " je na rade!");
    }
}
