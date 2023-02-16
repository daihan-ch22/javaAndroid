package org.dan.myservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();

                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("command", "show");
                intent.putExtra("name", name);

                startService(intent);
            }
        });

        Intent passedIntent = getIntent();
        processCommand(passedIntent);
    }

    //MainActivity가 이미 만들어져있는 상태에서는 onCreate()가 아니라 onNewIntent()가 호출된다.
    // 서비스에서 onCreate()가 한번만 호출되고 그 이후에는 onStartCommand()가 호출되는것과 같다.
    @Override
    protected void onNewIntent(Intent passedIntent) {
        processCommand(passedIntent);
        super.onNewIntent(passedIntent);
    }

    private void processCommand(Intent intent) {
        if (intent != null && intent.getStringExtra("command") != null && intent.getStringExtra("name")!=null) {
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");

            Toast.makeText(this, "Received Data From Service via Intent: "
                    + command + ", " + name, Toast.LENGTH_LONG).show();

        }
    }
}