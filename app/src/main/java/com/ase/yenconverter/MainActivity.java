package com.ase.yenconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        EditText etYen=findViewById(R.id.etYen);
        EditText etFinalValue=findViewById(R.id.etFinalValue);
        Spinner spinner=findViewById(R.id.spValoare);
        Button btnConvert=findViewById(R.id.button2);

        btnConvert.setOnClickListener(v -> {
            float convertedValue=Float.parseFloat(String.valueOf(etYen.getText()));
            if(spinner.getSelectedItemPosition()==0){
                convertedValue/=31.5;
                etFinalValue.setText(String.valueOf(convertedValue)+" RON");
            }else{
                convertedValue/=155;
                etFinalValue.setText(String.valueOf(convertedValue)+" EURO");
            }

        });
    }
}