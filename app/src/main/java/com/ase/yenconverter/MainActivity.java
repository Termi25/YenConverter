package com.ase.yenconverter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText etYen;
    EditText etFinalValue;
    Spinner spinner2;
    Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etYen=findViewById(R.id.etYen);
        etFinalValue=findViewById(R.id.etFinalValue);
        spinner2=findViewById(R.id.spValoare2);
        spinner1=findViewById(R.id.spValoare);
        spinner1.setSelection(0);
        spinner2.setSelection(1);

        etYen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                schimbValutar();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                schimbValutar();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                schimbValutar();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void schimbValutar(){
        try{
            DecimalFormat numberFormat = new DecimalFormat("#.00");
            double convertedValue=Double.parseDouble(String.valueOf(etYen.getText()));
            double exchangeRate=1.0;
            switch(spinner1.getSelectedItemPosition()){
                case 0:{
                    switch (spinner2.getSelectedItemPosition()){
                        case 1:{
                            exchangeRate=31.5;
                            break;
                        }
                        case 2:{
                            exchangeRate=155.0;
                            break;
                        }
                        default:break;
                    }
                    break;
                }
                case 1:{
                    switch (spinner2.getSelectedItemPosition()){
                        case 0: {
                            exchangeRate=1/31.5;
                            break;
                        }
                        case 2:{
                            exchangeRate=1/0.2;
                            break;
                        }
                        default:break;
                    }
                    break;
                }
                case 2:{
                    switch (spinner2.getSelectedItemPosition()){
                        case 0: {
                            exchangeRate= 1 /155.0;
                            break;
                        }
                        case 1:{
                            exchangeRate=0.2;
                            break;
                        }
                        default:break;
                    }
                    break;
                }
            }
            convertedValue/=exchangeRate;
            etFinalValue.setText(String.valueOf(numberFormat.format(convertedValue)));
        }catch (Exception e){
            etFinalValue.setText("");
        }
    }
}