package com.example.licenta;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.licenta.ui.pronume.PronumeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    final Bundle bundleEn = new Bundle();
    final Bundle bundleRo = new Bundle();
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView drawerMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_menu);
        initUI();
        db=FirebaseFirestore.getInstance();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PronumeFragment()).commit();
            drawerMenu.setCheckedItem(R.id.pronume);
        }
        setMenuListener();
    }
    private void initUI(){
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerMenu = findViewById(R.id.nv_drawer_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setMenuListener() {
        getPronume();
        drawerMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.adjective:
                        //etSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomepageFragment()).addToBackStack(null).commit();
                        Toast.makeText(MainActivity.this, "Adjective", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.substantive:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalendarFragment()).addToBackStack(null).commit();
                        Toast.makeText(MainActivity.this, "Substantive", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.obiecte:
                        Toast.makeText(MainActivity.this, "Obiecte", Toast.LENGTH_SHORT).show();
                        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavoritesFragment()).addToBackStack(null).commit();
                        break;
                    case R.id.pronume:
                        try {
                            PronumeFragment pronumeFragment = new PronumeFragment();
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            Bundle bundle = new Bundle();
                            bundle.putBundle("bundleRo", bundleRo);
                            bundle.putBundle("bundleEn", bundleEn);
                            pronumeFragment.setArguments(bundle);
                            transaction.replace(R.id.fragment_container, pronumeFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        drawerLayout.closeDrawers();
                        return true;
                }
                drawerLayout.closeDrawers();

                return true;
            }
        });
    }

    public void getPronume() {
        db.collection("cuvinte").document("pronumeEngleza").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot pronumeRomana = task.getResult();
                List<String> pronume = (List<String>) pronumeRomana.get("pronume");
                ArrayList<String> pronumeRo = new ArrayList<>(pronume.size());
                pronumeRo.addAll(pronume);
                Log.d("myTag", pronume.toString());
                bundleEn.putStringArrayList("pronumeEngleza", pronumeRo);

            }
        });
        db.collection("cuvinte").document("pronumeRomana").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot pronumeRomana = task.getResult();
                List<String> pronume = (List<String>) pronumeRomana.get("pronume");
                ArrayList<String> pronumeRo = new ArrayList<>(pronume.size());
                pronumeRo.addAll(pronume);
                Log.d("myTag", pronume.toString());
                bundleRo.putStringArrayList("pronumeRomana", pronumeRo);

            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            getFragmentManager().popBackStackImmediate();
            super.onBackPressed();
        }
    }

}
