package org.dan.bitmapbutton;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class BitMapButton extends AppCompatButton {
    public BitMapButton(@NonNull Context context) {
        super(context);
        init(context);
    }

    public BitMapButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        setBackgroundResource(R.drawable.bitmapsample);
        //자바 코드에서 사이즈는 픽셀단위
        //따라서 dimens.xml에 dp단위를 적용해 사용
        float textSize = getResources().getDimension(R.dimen.text_size);
        setTextSize(textSize);
        setTextColor(Color.WHITE);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction(); //눌렀을때인지, 뗏을뗀지, 눌러서 움직일땐지 구분해준다.

        switch(action){
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(R.drawable.clicked);
                break;

            case MotionEvent.ACTION_UP:
                setBackgroundResource(R.drawable.normal);
                break;
        }

        invalidate(); //다시 그려달라고 요청 (버튼의 그래픽을 다시 그림)
        return true;
    }
}
