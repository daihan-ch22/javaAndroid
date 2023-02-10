package org.dan.listviewprac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListViewAdapter adapter;

    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);

        adapter = new ListViewAdapter();

        adapter.personList.add(new PersonEntity("NAME1", "MOBILE1"));
        adapter.personList.add(new PersonEntity("NAME2", "MOBILE2"));

        listView.setAdapter(adapter);


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                String phone = editText2.getText().toString();

                adapter.personList.add(new PersonEntity(name, phone));
                adapter.notifyDataSetChanged();
            }
        });
    }

    //This will controll data
    class ListViewAdapter extends BaseAdapter {

        List<PersonEntity> personList = new ArrayList<>();

        @Override
        public int getCount() {
            return personList.size();
        }

        @Override
        public Object getItem(int i) {
            return personList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }


        //Adapter to create View
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ListViewConnector listView = null;

            if(view == null){
                listView = new ListViewConnector(getApplicationContext());
            } else {
                listView = (ListViewConnector) view;
            }

            ListViewConnector listViewConnector = new ListViewConnector(getApplicationContext());

            PersonEntity personInfo = personList.get(i);
            listViewConnector.setName(personInfo.getName());
            listViewConnector.setPhone(personInfo.getMobileNum());

            return listViewConnector;
        }
    }




}