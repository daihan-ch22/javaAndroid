package org.dan.numgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView textView2;
    EditText answerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        answerTextView = (EditText) findViewById(R.id.answerTextView);
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomFun(textView,textView2);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAns(textView,textView2);
            }
        });
    }


    private void checkAns(TextView textView, TextView textView2){
        int answer = Integer.parseInt(answerTextView.getText().toString());
        int first = Integer.parseInt(textView.getText().toString());
        int second = Integer.parseInt(textView2.getText().toString());

        if(answer == (first + second)){
            Snackbar.make(this.answerTextView, "CORRECT!", Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(this.answerTextView, "WRONG!", Snackbar.LENGTH_LONG).show();
        }
    }


    private void randomFun(TextView textView, TextView textView2){

        Random rand = new Random();
        int maxBound = 1000;

        int firstNum = rand.nextInt(maxBound);
        int secondNum = rand.nextInt(maxBound);

        textView.setText(String.valueOf(firstNum));
        textView2.setText(String.valueOf(secondNum));
    }
}