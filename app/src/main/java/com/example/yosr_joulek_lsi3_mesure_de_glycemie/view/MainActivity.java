package com.example.yosr_joulek_lsi3_mesure_de_glycemie.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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
    private final int REQUEST_CODE = 1;


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
                    value = Float.valueOf(valueString);
                    controller.CreatePatient(age, value, rbIsFasting.isChecked());
                    Intent intent = new Intent(MainActivity.this, ConsultActivity.class);
                    intent.putExtra("reponse", controller.getResultat());
                    startActivityForResult(intent, REQUEST_CODE);

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_CANCELED) {
            Toast.makeText(MainActivity.this, "Erreur", Toast.LENGTH_SHORT).show();
        }

    }

    private void init() {
        tvAge = findViewById(R.id.tvAge);
        sbAge = findViewById(R.id.sbAge);
        rbIsFasting = findViewById(R.id.rbtOui);
        rbIsNotFasting = findViewById(R.id.rbtNon);
        etValue = findViewById(R.id.etValue);
        btnConsulter = findViewById(R.id.btnConsulter);

    }
    public void Calculer(View v) {
        int age;
        float value;
        boolean verifAge = false;
        boolean verifValue = false;
        String valueString = etValue.getText().toString();
        if (sbAge.getProgress() != 0) {
            verifAge = true;
        } else {
            Toast.makeText(MainActivity.this, "Veuillez vérifier votre âge", Toast.LENGTH_SHORT).show();
        }

        if (!etValue.getText().toString().isEmpty()) {
            verifValue = true;
        } else {
            Toast.makeText(MainActivity.this, "Veuillez vérifier votre valeur", Toast.LENGTH_LONG).show();
        }

        if (verifAge && verifValue) {
            age = sbAge.getProgress();
            value = Float.valueOf(etValue.getText().toString());
        }
    }
}
