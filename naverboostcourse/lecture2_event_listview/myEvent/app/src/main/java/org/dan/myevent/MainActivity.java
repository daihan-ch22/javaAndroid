package org.dan.myevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        View view = findViewById(R.id.view);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int action = motionEvent.getAction();

                float curlX = motionEvent.getX();
                float curlY = motionEvent.getY();


                if (action == MotionEvent.ACTION_DOWN) {
                    println("pressed by finger : " + curlX + ", " + curlY);
                } else if (action == MotionEvent.ACTION_MOVE) {
                    println("finger moved : " + curlX + ", " + curlY);
                } else if (action == MotionEvent.ACTION_UP) {
                    println("finger removed : " + curlX + ", " + curlY);
                }
                return true;
            }
        });

        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(@NonNull MotionEvent motionEvent) {
                println("called onDown() ");
                return true;
            }

            @Override
            public void onShowPress(@NonNull MotionEvent motionEvent) {
                println("called onShowPress() ");
            }

            @Override
            public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
                println("called onSingleTapUp() ");
                return true;
            }

            @Override
            public boolean onScroll(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
                println("called onScroll() " + v + "," + v1);
                return true;
            }

            @Override
            public void onLongPress(@NonNull MotionEvent motionEvent) {
                println("called onLongPress() ");

            }

            @Override
            public boolean onFling(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
                println("called onFling() " + v + ", " + v1);
                return true;
            }
        });

        View view2 = findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                detector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }

    /**
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(this, "Pressed System Back Button.", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    public void println(String data) {
        textView.append(data + "\n");
    }


}