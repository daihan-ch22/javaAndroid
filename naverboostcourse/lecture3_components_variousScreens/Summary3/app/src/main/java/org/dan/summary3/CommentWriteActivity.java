package org.dan.summary3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

//작성하기 화면
public class CommentWriteActivity extends AppCompatActivity {

    RatingBar ratingBar;
    EditText contentInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_write);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        contentInput = (EditText) findViewById(R.id.contentInput);

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToMainActivity();
            }
        });

        Intent intentFromMainAct = getIntent();
        processIntent(intentFromMainAct);
    }


    private void processIntent(Intent intent){
        if(intent != null){
            float rating = intent.getFloatExtra("rating", 0.0f);
            ratingBar.setRating(rating);

        }
    }


    private void returnToMainActivity(){
        Intent intent = new Intent();
        intent.putExtra("content", contentInput.getText().toString() );

        setResult(RESULT_OK, intent);

        finish();
    }




}