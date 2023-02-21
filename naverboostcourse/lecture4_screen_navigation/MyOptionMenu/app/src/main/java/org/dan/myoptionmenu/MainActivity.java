package org.dan.myoptionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar abar = getSupportActionBar();
        //abar.hide(); //액션바 숨기기
    }

    //액션바 Inflation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

   //메뉴 아이템이 클릭됬을때 사용되는 메서드 (콜백)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();

        switch (curId){
            case R.id.menu_refresh:
                Toast.makeText(getApplicationContext(), "Clicked Refresh Menu"
                ,Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_search:
                Toast.makeText(getApplicationContext(), "Clicked Search Menu"
                        ,Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_settings:
                Toast.makeText(getApplicationContext(), "Clicked Setting Menu"
                        ,Toast.LENGTH_LONG).show();
                break;
            default: break;
        }

        return super.onOptionsItemSelected(item);
    }
}