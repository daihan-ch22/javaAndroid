package org.dan.mypager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 pager;
    private CustomSliderPagerAdapter pagerAdapter;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        pagerAdapter = new CustomSliderPagerAdapter(this);
        pager.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabLayout, pager,
                (tab, position) -> tab.setText("Tab " + (position+1))).attach();

    }

}