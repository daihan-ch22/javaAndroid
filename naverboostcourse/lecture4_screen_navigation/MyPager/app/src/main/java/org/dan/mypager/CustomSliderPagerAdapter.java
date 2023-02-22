package org.dan.mypager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

class CustomSliderPagerAdapter extends FragmentStateAdapter {

    public CustomSliderPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        if(position==0) return new Fragment1();
        else if(position==1) return new Fragment2();
        else return new Fragment3();
    }

    //https://developer.android.com/guide/navigation/navigation-swipe-view-2?hl=ko


    @Override
    public int getItemCount() {
        return 3;
    }
}
