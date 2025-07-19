package com.example.vcampusexpenses.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.vcampusexpenses.ExchangeFragment;
import com.example.vcampusexpenses.AnalystFragment;
import com.example.vcampusexpenses.HomeFragment;
import com.example.vcampusexpenses.CategoryFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager,@NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new HomeFragment();
        } else if (position == 1) {
            return new AnalystFragment();
        } else if (position == 2) {
            return new ExchangeFragment();
        } else if (position == 3) {
            return new CategoryFragment();
        } else {
            return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

}
