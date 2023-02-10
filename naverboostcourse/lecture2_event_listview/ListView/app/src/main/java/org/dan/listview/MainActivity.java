package org.dan.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SingerAdapter adapter;
    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);


        adapter = new SingerAdapter();

        String name = "냉장고";
        String mobile = "010-1111-2222";
        for(int i=0; i<3; i++){
            adapter.items.add(new SingerItems(name, mobile));
            name = "냉장고" + i;
            mobile = "010-1111-222" + i;
        }
        listView.setAdapter(adapter);

        //리스트 클릭시 스낵바 생성
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SingerItems items = (SingerItems) adapter.getItem(i);

                //Toast.makeText(getApplicationContext(),  "Select : " + items.getName(), Toast.LENGTH_LONG).show();
                Snackbar.make(listView, "select: " + items.getName(), Snackbar.LENGTH_LONG).show();

            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                String mobile = editText2.getText().toString();

                adapter.items.add(new SingerItems(name, mobile));
                adapter.notifyDataSetChanged();
            }
        });
    }


    //Adapter will control DATAs
    private class SingerAdapter extends BaseAdapter {

        List<SingerItems> items = new ArrayList<>();

        @Override
        public int getCount() { //ListView에 아이템이 몇개가 있는지??
            return items.size();
        }


        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        //Data관리하는 어댑터에게 View도 만들어달라고 하는 메서드
        //부분화면으로 객체를 만들어서 거기에 데이터를 넣어 뽑기
        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {

            //리소스 재사용----------------------------
            SingerItemView view = null;
            if(convertView == null) {
                view = new SingerItemView(getApplicationContext());
            } else {
                view = (SingerItemView) convertView;
            }

            //안드로이드에서 모든 View는 Context객체를 받게 되어있다.
            SingerItemView singerItemView = new SingerItemView(getApplicationContext());

            SingerItems item = items.get(i);
            singerItemView.setName(item.getName());
            singerItemView.setMobile(item.getMobile());

            return singerItemView;
        }
    }
}
