package org.dan.scorekeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mScoreText1;
    private TextView mScoreText2;
    private Integer mScore1;
    private Integer mScore2;
    private ImageButton minusButton1;
    private ImageButton minusButton2;
    private ImageButton plusButton1;
    private ImageButton plusButton2;
    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        buttonEvent();

        if(savedInstanceState != null){
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2);

            mScoreText1.setText(String.valueOf(mScore1));
            mScoreText2.setText(String.valueOf(mScore2));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(STATE_SCORE_1, mScore1);
        outState.putInt(STATE_SCORE_2, mScore2);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.night_mode) {
            int nightMode = AppCompatDelegate.getDefaultNightMode();

            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
        }
        return true;
    }

    private void init(){
        mScoreText1 = findViewById(R.id.score_1);
        mScoreText2 = findViewById(R.id.score_2);
        plusButton1 = findViewById(R.id.increaseTeam1);
        plusButton2 = findViewById(R.id.increaseTeam2);
        minusButton1 = findViewById(R.id.decreaseTeam1);
        minusButton2 = findViewById(R.id.decreaseTeam2);
        mScore1 = mScore2 = 0;
    }

    private void decreaseScore(View view){
        Integer viewId = view.getId();
        switch (viewId){
            case R.id.decreaseTeam1 :
                mScore1 --;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.decreaseTeam2 :
                mScore2 --;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
            default: break;
        }
    }
    private void increaseScore(View view){
        Integer viewId = view.getId();
        switch (viewId){
            case R.id.increaseTeam1:
                mScore1 ++;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.increaseTeam2:
                mScore2 ++;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
            default: break;
        }
    }

    private void buttonEvent(){
        plusButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseScore(view);
            }
        });
        plusButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseScore(view);
            }
        });
        minusButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseScore(view);
            }
        });
        minusButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseScore(view);
            }
        });
    }
}