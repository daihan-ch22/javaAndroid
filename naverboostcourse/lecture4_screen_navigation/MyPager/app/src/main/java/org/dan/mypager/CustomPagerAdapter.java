package org.dan.mypager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

class CustomPagerAdapter extends FragmentStateAdapter {

    public CustomPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        Fragment fragment = new Fragment1();
        Bundle args = new Bundle();

        args.putInt();


        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
