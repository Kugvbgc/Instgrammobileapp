package com.khair.instgrammobileapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawerLayout);
        materialToolbar=findViewById(R.id.Toolbar);
        navigationView=findViewById(R.id.NavigationView);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);

       view=navigationView.getHeaderView(0);


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
                MainActivity.this,drawerLayout,materialToolbar,R.string.CloseDrawer,R.string.openDrawer);
        drawerLayout.addDrawerListener(toggle);

        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.frameLayout,new Fast_Fragment());
        transaction.commit();




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //drawerLayout.closeDrawer(GravityCompat.START);

                if (item.getItemId()==R.id.Item1){
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId()==R.id.Item2) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId()==R.id.Item3) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId()==R.id.Item4) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }


                return true;
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId()==R.id.home){
                    FragmentManager manager=getSupportFragmentManager();
                    FragmentTransaction transaction=manager.beginTransaction();
                    transaction.add(R.id.frameLayout,new Fast_Fragment());
                    transaction.commit();

                } else if (item.getItemId()==R.id.search) {
                    FragmentManager manager=getSupportFragmentManager();
                    FragmentTransaction transaction=manager.beginTransaction();
                    transaction.add(R.id.frameLayout,new Fragment_search());
                    transaction.commit();


                } else if (item.getItemId()==R.id.profile) {
                    FragmentManager manager=getSupportFragmentManager();
                    FragmentTransaction transaction=manager.beginTransaction();
                    transaction.add(R.id.frameLayout,new Fragment_2nd());
                    transaction.commit();
                }



                return true;
            }
        });



    }

}