package com.example.contactapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.contactapplication.fragment.StaffsFragment;
import com.example.contactapplication.fragment.StudentsFragment;
import com.example.contactapplication.fragment.UnitsFragment;


public class FragmentAdapter extends FragmentStateAdapter {


    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    //Trả về 1 Fragment tương ứng với vị trí position
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new UnitsFragment();
            case 1:
                return new StaffsFragment();
            case 2:
                return new StudentsFragment();
            default:
                return new UnitsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
