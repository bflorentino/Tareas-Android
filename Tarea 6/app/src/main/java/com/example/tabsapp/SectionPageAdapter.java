package com.example.tabsapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SectionPageAdapter extends FragmentStateAdapter {
    public SectionPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                tabInicio tab1 = new tabInicio();
                return tab1;
            case 1:
                tab_registro tab2 = new tab_registro();
                return tab2;
            case 2:
                tab_contacto tab3 = new tab_contacto();
                return tab3;
            case 3:
                tab_canciones_favoritas tab4 = new tab_canciones_favoritas();
                return tab4;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}