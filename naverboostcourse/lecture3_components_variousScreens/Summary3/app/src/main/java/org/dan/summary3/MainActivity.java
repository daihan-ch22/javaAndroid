package org.dan.summary3;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    RatingBar ratingBar;


    //launcher object
    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult() , result ->
            {
                if(result.getResultCode() == RESULT_OK){
                    Intent receivedDataFromComment = result.getData();
                    String content = receivedDataFromComment.getStringExtra("content");


                    TextView textView = (TextView) findViewById(R.id.contentFromCommentWrite);
                    textView.setText(content);

                    Toast.makeText(getApplicationContext(), "receivedData : " + content, Toast.LENGTH_LONG).show();
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCommentWriteActivity();
            }
        });
    }

    private void showCommentWriteActivity(){
        float rating = ratingBar.getRating();


        Intent intent = new Intent(getApplicationContext(), CommentWriteActivity.class);
        intent.putExtra("rating", rating);
        resultLauncher.launch(intent);
    }
}