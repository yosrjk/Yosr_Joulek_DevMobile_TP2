package com.example.yosr_joulek_lsi3_mesure_de_glycemie.view;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yosr_joulek_lsi3_mesure_de_glycemie.R;


public class ConsultActivity extends AppCompatActivity {
    private TextView tvReponse;
    private Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        init();

        Intent intent = getIntent();
        String response = intent.getStringExtra("reponse");
        tvReponse.setText(response);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (response != null) {
                    setResult(RESULT_OK, intent);
                } else {
                    setResult(RESULT_CANCELED, intent);
                }
                finish();
            }
        });
    }

    private void init() {
        tvReponse = findViewById(R.id.tvReponse);
        btnReturn = findViewById(R.id.btnReturn);
    }
}