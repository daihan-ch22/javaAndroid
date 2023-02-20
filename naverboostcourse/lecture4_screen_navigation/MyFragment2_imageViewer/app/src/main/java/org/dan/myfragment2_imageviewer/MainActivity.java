package org.dan.myfragment2_imageviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ListFragment fragment1;
    viewerFragment fragment2;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        fragment1 = (ListFragment) manager.findFragmentById(R.id.listFragment);
        fragment2 = (viewerFragment) manager.findFragmentById(R.id.viewerFragment);
    }

    public void onImageChange(int index){

        fragment2.setImage(index);
    }
}