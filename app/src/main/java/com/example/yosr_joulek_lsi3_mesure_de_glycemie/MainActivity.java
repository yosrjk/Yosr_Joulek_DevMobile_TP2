package com.example.yosr_joulek_lsi3_mesure_de_glycemie;

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

public class MainActivity extends AppCompatActivity {
    private TextView tvAge;
    private SeekBar sbAge;
    private RadioButton  rbIsFasting,rbIsNotFasting ;
    private EditText etValue;
    private Button btnConsulter ;
    private TextView tvResult ;


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
    }
    private void init(){
        // cette methode permet l'initialisation des attributs en relation avec les ids de l'acitivity_main
        //on peut la faire dans la methode oncreate()
        tvAge=(TextView)findViewById(R.id.tvAge);// from the activity_main , findviewbyid return view
        sbAge=(SeekBar)findViewById(R.id.sbAge);
        rbIsFasting=(RadioButton) findViewById(R.id.rbtOui);
        rbIsNotFasting=(RadioButton) findViewById(R.id.rbtNon);
        etValue=(EditText) findViewById(R.id.etValue);
        btnConsulter=(Button) findViewById(R.id.btnConsulter);
        tvResult=(TextView) findViewById(R.id.tvResult);
    }
    //methode Calculer
       public void  Calculer (View v) {
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
           if(verifAge && verifValue ){
               age=sbAge.getProgress();
               value=Float.valueOf(etValue.getText().toString());
               if(rbIsFasting.isChecked()){
                   if(age>=10){
                       if(value<5.0)
                           tvResult.setText("Le niveau de glycemie est tres bas ");
                        else if (value >=5.0 && value <=7.2)
                           tvResult.setText("Le niveau de glycemie est normal ");
                        else tvResult.setText("Le niveau de glycemie est elevé");
                   if(age >=6 && age <=12)
                       if(value<5.0) tvResult.setText("Le niveau de glycemie est tres bas ");
                       else if (value>=5.0 && value<=10.5) tvResult.setText("Le niveau de glycemie est normal ");
                         else tvResult.setText("Le niveau de glycemie est elevé");
                   else if (value <5.5)  tvResult.setText("Le niveau de glycemie est bas");
                    else if (value>=5.0 && value <=10.0) tvResult.setText("Le niveau de glycemie est normal");
                     else tvResult.setText("Le niveau de glycemie est elevé");
                   }
               } else if (value<10.5) tvResult.setText("Le niveau de glycemie est normal");
                  else  tvResult.setText("Le niveau de glycemie est élevé");

           }

    }



}
