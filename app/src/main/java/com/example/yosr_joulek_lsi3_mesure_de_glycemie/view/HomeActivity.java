package com.example.yosr_joulek_lsi3_mesure_de_glycemie.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yosr_joulek_lsi3_mesure_de_glycemie.R;
import com.example.yosr_joulek_lsi3_mesure_de_glycemie.controller.LoginController;

public class HomeActivity extends AppCompatActivity {
    private Button btnConsultation;
    private EditText eUserName;
    private EditText etPassword;
    private LoginController loginController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        btnConsultation=(Button) findViewById(R.id.btnConsultation);
        btnConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void init(){
        loginController=loginController.getInstance(HomeActivity.this);
        btnConsultation=(Button) findViewById(R.id.btnConsultation);
        etPassword=(EditText) findViewById(R.id.etPassword);
        eUserName=(EditText) findViewById(R.id.etUserName);
    }
}