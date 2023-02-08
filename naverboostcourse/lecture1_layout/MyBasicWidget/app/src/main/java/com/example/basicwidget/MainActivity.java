package com.example.basicwidget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    RadioButton radioButton;
    RadioButton radioButton2;
    boolean isCheckedRB1;
    boolean isCheckedRB2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         radioButton = (RadioButton) findViewById(R.id.radioButton);
         radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
         isCheckedRB1 = radioButton.isChecked();
         isCheckedRB2 = radioButton2.isChecked();
    }

    public void doClick1(View view){

        System.out.println(isCheckedRB1);
        System.out.println(isCheckedRB2);


            if (isCheckedRB1) {
                Toast toast = Toast.makeText(getApplicationContext(), "1 Selected", Toast.LENGTH_LONG);
                toast.show();
            } else if (isCheckedRB2) {
                Toast toast = Toast.makeText(getApplicationContext(), "2 Selected", Toast.LENGTH_LONG);
                toast.show();
            }

        }


    }