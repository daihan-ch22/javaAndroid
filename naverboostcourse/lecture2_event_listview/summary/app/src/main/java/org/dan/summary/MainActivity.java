package org.dan.summary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Like Button variable
    Button likeButton;
    TextView likeCountView;
    ListView listView;
    boolean thumbsUpState = false;
    int likeCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        likeButton = (Button) findViewById(R.id.likeButton);
        likeCountView = (TextView) findViewById(R.id.likeCountView);
        listView = (ListView) findViewById(R.id.listView);

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(thumbsUpState){ //false
                    decreaseLikeCount();
                } else {
                    increaseLikeCount();
                }
                thumbsUpState = !thumbsUpState;
            }
        });

        CommentItemViewAdapter adapter = new CommentItemViewAdapter();
        listView.setAdapter(adapter);
    }

    private void increaseLikeCount(){
        likeCount += 1;
        likeButton.setBackgroundResource(R.drawable.ic_thumb_up_selected);
        likeCountView.setText(String.valueOf(likeCount));
    }

    private void decreaseLikeCount() {
        likeCount -= 1;
        likeCountView.setText(String.valueOf(likeCount));
        likeButton.setBackgroundResource(R.drawable.thumbs_up_selector);
    }
}