package org.dan.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;


/*
*
* singer_items.xml과 인플레이션을 통해서 묶어놓았다.
*
* */
public class SingerItemView extends LinearLayout {


    TextView textView;
    TextView textView2;

    public SingerItemView(Context context) {
        super(context);
        init(context);
    }

    public SingerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    //singer_items xml파일을 Inflation해서 객체화
    //SystemService -> 단말 백그라운드에서 SystemService가 돌아가고 있다.
    private void init(Context context){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.singer_items, this, true);
        // .inflate(객체화 하고 싶은 레이아웃 ,  매칭시킬 자바 클래스, 붙일지 여부)
        /*
        public View inflate (XmlPullParser parser,
                ViewGroup root,
                boolean attachToRoot)
        */

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
    }

    public void setName(String name) {
        textView.setText(name);
    }

    public void setMobile(String mobile){
        textView2.setText(mobile);
    }
}
