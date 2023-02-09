package org.dan.mydialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage();
            }
        });

        textView = (TextView) findViewById(R.id.textView);
    }

    private void showMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Title");
        builder.setMessage("Would like to close?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() { //대화상자 예 아니오 버튼
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Snackbar.make(textView, "YES button is pressed", Snackbar.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Snackbar.make(textView, "NO button is pressed", Snackbar.LENGTH_LONG).show();
            }
        });

        builder.create().show();
    }
}