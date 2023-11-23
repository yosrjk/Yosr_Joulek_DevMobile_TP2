package com.example.yosr_joulek_lsi3_mesure_de_glycemie.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yosr_joulek_lsi3_mesure_de_glycemie.R;
import com.example.yosr_joulek_lsi3_mesure_de_glycemie.controller.Controller;

public class MainActivity extends AppCompatActivity {
    private TextView tvAge;
    private SeekBar sbAge;
    private RadioButton rbIsFasting, rbIsNotFasting;
    private EditText etValue;
    private Button btnConsulter;
    private TextView tvResult;
    private Controller controller = Controller.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("information", "onProgressChanged: " + i);
                tvAge.setText("Votre Age : " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int age;
                float value;
                boolean verifAge = false;
                boolean verifValue = false;
                String valueString = etValue.getText().toString();
                if (sbAge.getProgress() != 0) {
                    verifAge = true;
                } else {
                    Toast.makeText(MainActivity.this, "veuillez verifier votre age", Toast.LENGTH_SHORT).show();
                }

                if (!etValue.getText().toString().isEmpty()) {
                    verifValue = true;
                } else {
                    Toast.makeText(MainActivity.this, "veuillez verifier votre valeur", Toast.LENGTH_LONG).show();
                }

                if (verifAge && verifValue) {
                    age = sbAge.getProgress();
                    value = Float.valueOf(valueString); // Corrected this line
                    // Partie 2 du user action entre view et controller
                    controller.CreatePatient(age, value, rbIsFasting.isChecked());
                    tvResult.setText(controller.getResultat());
                }
            }
        });
    }

    private void init() {
        tvAge = findViewById(R.id.tvAge);
        sbAge = findViewById(R.id.sbAge);
        rbIsFasting = findViewById(R.id.rbtOui);
        rbIsNotFasting = findViewById(R.id.rbtNon);
        etValue = findViewById(R.id.etValue);
        btnConsulter = findViewById(R.id.btnConsulter);
        tvResult = findViewById(R.id.tvResult);
    }

    public void calculer(View v) {
        int age;
        float value;
        boolean verifAge = false;
        boolean verifValue = false;
        String valueString = etValue.getText().toString();
        if (sbAge.getProgress() != 0) {
            verifAge = true;
        } else {
            Toast.makeText(MainActivity.this, "veuillez verifier votre age", Toast.LENGTH_SHORT).show();
        }

        if (!etValue.getText().toString().isEmpty()) {
            verifValue = true;
        } else {
            Toast.makeText(MainActivity.this, "veuillez verifier votre valeur", Toast.LENGTH_LONG).show();
        }

        if (verifAge && verifValue) {
            age = sbAge.getProgress();
            value = Float.valueOf(valueString);
            if (rbIsFasting.isChecked()) {
                if (age >= 10) {
                    if (value < 5.0)
                        tvResult.setText("Le niveau de glycemie est tres bas ");
                    else if (value >= 5.0 && value <= 7.2)
                        tvResult.setText("Le niveau de glycemie est normal ");
                    else tvResult.setText("Le niveau de glycemie est elevé");

                    if (age >= 6 && age <= 12)
                        if (value < 5.0) tvResult.setText("Le niveau de glycemie est tres bas ");
                        else if (value >= 5.0 && value <= 10.5) tvResult.setText("Le niveau de glycemie est normal ");
                        else tvResult.setText("Le niveau de glycemie est elevé");
                    else if (value < 5.5) tvResult.setText("Le niveau de glycemie est bas");
                    else if (value >= 5.0 && value <= 10.0) tvResult.setText("Le niveau de glycemie est normal");
                    else tvResult.setText("Le niveau de glycemie est elevé");
                }
            } else if (value < 10.5) tvResult.setText("Le niveau de glycemie est normal");
            else tvResult.setText("Le niveau de glycemie est élevé");
        }
    }
}
