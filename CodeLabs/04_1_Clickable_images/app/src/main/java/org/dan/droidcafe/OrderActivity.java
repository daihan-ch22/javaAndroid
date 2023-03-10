package org.dan.droidcafe;

import static org.dan.droidcafe.FirstFragment.EXTRA_MESSAGE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.sql.Time;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText time;
    RadioButton sameDay;
    RadioButton nextDay;
    RadioButton pickup;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        String msg = intent.getStringExtra(EXTRA_MESSAGE);
        System.out.println(msg);
        TextView tv = findViewById(R.id.textView);
        tv.setText(msg);

        clock();
        buttonsEvent();

        //Creating Spinner
        spinner = findViewById(R.id.label_spinner);
        if(spinner != null){
            spinner.setOnItemSelectedListener(this);
        }

        // Create ArrayAdapter using the string array and default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears.


        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner.
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }

    }

    private void buttonsEvent(){

        sameDay = findViewById(R.id.sameday);
        nextDay = findViewById(R.id.nextday);
        pickup = findViewById(R.id.pickup);
        nextDay.setChecked(true);

        sameDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sameday Delivery", Snackbar.LENGTH_LONG).show();
            }
        });
        nextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Nextday Delivery", Snackbar.LENGTH_LONG).show();
            }
        });
        pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Pickup", Snackbar.LENGTH_LONG).show();
            }
        });
    }
    private void clock(){
        time = findViewById(R.id.time);
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time.setText(DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE));
                    }
                });
            }
        }).start();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spinnerLabel = adapterView.getItemAtPosition(i).toString();
        Snackbar.make(view, spinnerLabel, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}