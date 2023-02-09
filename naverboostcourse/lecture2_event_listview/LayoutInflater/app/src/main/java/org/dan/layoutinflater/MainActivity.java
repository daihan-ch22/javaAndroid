package org.dan.layoutinflater;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);     //여기서 activity_main.xml(전체화면)을 만들어 메모리에 올림 (Inflation)

        container = (FrameLayout) findViewById(R.id.container);


        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.sub1_xml, container, true);

                Snackbar.make(view, "Inflation completed.", Snackbar.LENGTH_LONG).show();

                Button button2 = (Button) findViewById(R.id.button2);
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogCall();
                    }
                });
            }
        });
    }
    private void dialogCall() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        dialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.out.println("YesClicked");
                System.exit(1);
            }
        });
        dialogBuilder.setTitle("TITLE");
        dialogBuilder.setMessage("WOULD LIKE TO CLOSE??");
        dialogBuilder.create().show();
    }
}