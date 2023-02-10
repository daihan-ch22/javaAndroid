package org.dan.listviewprac;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

/*
<<INFLATION>>
* Connect to listView.xml
*
* */
public class ListViewConnector extends LinearLayout {


    TextView textView;
    TextView textView2;

    public ListViewConnector(Context context) {
        super(context);
        init(context);
    }

    public ListViewConnector(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    //Inflation 작업
    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listview, this, true);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
    }

    public void setName(String name){
        textView.setText(name);
    }
    public void setPhone(String phone){
        textView2.setText(phone);
    }
}
