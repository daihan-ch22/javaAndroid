    package org.dan.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

    public class SecondActivity extends AppCompatActivity {

        EditText mReply;
        Button replyBtn;

        public static final String EXTRA_REPLY = "org.dan.twoactivities.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        //MAIN -> SECOND
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView tv = findViewById(R.id.text_message_reply);
        tv.setText(message);

        replyBtn = findViewById(R.id.button_second);
        replyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToMain();
            }
        });
    }

    public void returnToMain() {
        //SECOND -> MAIN
        mReply = findViewById(R.id.editText_second);
        String reply = mReply.getText().toString();

        Intent returnIntent = new Intent();
        returnIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}