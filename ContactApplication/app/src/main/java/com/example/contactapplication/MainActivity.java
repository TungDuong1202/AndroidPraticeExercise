package com.example.contactapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.contactapplication.adapter.FragmentAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigation;
    private DrawerLayout drawerLayout;
    private NavigationView naviStart;
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ánh xạ Toolbar và DrawerLayout
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);

        // Tạo nút mở menu
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        bottomNavigation = findViewById(R.id.bottom_navigation);
        FragmentAdapter newAdapter = new FragmentAdapter(this);
        viewPager2 = findViewById(R.id.view_pager);
        viewPager2.setAdapter(newAdapter);

        // Xử lý sự kiện khi 1 mục trong bottom navigation được chọn
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.btn_units) {
                    viewPager2.setCurrentItem(0);
                } else if (id == R.id.btn_staff) {
                    viewPager2.setCurrentItem(1);
                } else if (id == R.id.btn_student) {
                    viewPager2.setCurrentItem(2);
                }
                return true;
            }
        });

        // Navigation Drawer
        naviStart = findViewById(R.id.navigation_view);
        naviStart.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.btn_add_staff) {
                    Intent intent = new Intent(MainActivity.this, AddStaffActivity.class);
                    startActivity(intent);
                } else if (id == R.id.btn_add_unit) {
                    Intent intent = new Intent(MainActivity.this, AddUnitActivity.class);
                    startActivity(intent);
                } else if (id == R.id.btn_log_out) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                } else if (id == R.id.btn_add_student) {
                    Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                    startActivity(intent);
                }

                // Đóng Navigation Drawer sau khi chọn
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }


}
