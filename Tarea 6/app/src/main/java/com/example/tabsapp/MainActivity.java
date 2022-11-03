package com.example.tabsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager2 pager;
    TabLayout tabLayout;
    SectionPageAdapter sectPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabsLayout);
        pager = findViewById(R.id.viewPage);

        FragmentManager fm = getSupportFragmentManager();
        sectPager = new SectionPageAdapter(fm, getLifecycle());
        pager.setAdapter(sectPager);

        tabLayout.addTab(tabLayout.newTab().setText("Inicio"));
        tabLayout.addTab(tabLayout.newTab().setText("Registro"));
        tabLayout.addTab(tabLayout.newTab().setText("Contacto"));
        tabLayout.addTab(tabLayout.newTab().setText("Favoritas"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());

                String nombreTab = "";

                switch (tab.getPosition()){
                    case 0:
                        nombreTab = "Inicio";
                        break;
                    case 1:
                        nombreTab = "Registro";
                        break;
                    case 2:
                        nombreTab = "Contacto";
                        break;
                    case 3:
                        nombreTab = "Favoritas";
                        break;
                }

                Toast.makeText(getApplicationContext(), "Has cambiado al tab " + nombreTab , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position){
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int nro = item.getItemId();

        switch(nro){
            case R.id.configuracion:
                Intent ventana = new Intent(this, ActivitySettings.class);
                startActivity(ventana);
                break;
            case R.id.salir:
                finish();
                System.exit(0);
                break;
            case R.id.cerrarsesion:
                Toast.makeText(getApplicationContext(), "Has cerrado sesion", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
}