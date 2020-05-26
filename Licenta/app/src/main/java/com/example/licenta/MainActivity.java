package com.example.licenta;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import android.widget.Toast;

import com.example.licenta.ui.fragmente.AdjectiveFragment;
import com.example.licenta.ui.fragmente.AnimaleFragment;
import com.example.licenta.ui.fragmente.FructeFragment;
import com.example.licenta.ui.fragmente.LegumeFragment;
import com.example.licenta.ui.fragmente.ObiecteFragment;
import com.example.licenta.ui.fragmente.PronumeFragment;
import com.example.licenta.ui.fragmente.ZileSaptamanaFragment;
import com.example.licenta.ui.fragmente.ttestFragmentt;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    final Bundle bundlePronumeEn = new Bundle();
    final Bundle bundlePronumeRo = new Bundle();
    final Bundle bundleAdjectiveEn = new Bundle();
    final Bundle bundleAdjectiveRo = new Bundle();
    final Bundle bundleObiecteEn = new Bundle();
    final Bundle bundleObiecteRo = new Bundle();
    final Bundle bundleAnimaleEn = new Bundle();
    final Bundle bundleAnimaleRo = new Bundle();
    final Bundle bundleLegumeEn = new Bundle();
    final Bundle bundleLegumeRo = new Bundle();
    final Bundle bundleFructeEn = new Bundle();
    final Bundle bundleFructeRo = new Bundle();
    final Bundle bundleZileEn = new Bundle();
    final Bundle bundleZileRo = new Bundle();
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView drawerMenu;
    final Bundle bundleIntrebariLocRo = new Bundle();
    final Bundle bundleIntrebariLocEn = new Bundle();
    final Bundle bundleRaspunsuriLocEn = new Bundle();
    final Bundle bundleRaspunsuriLocRo = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_menu);
        initUI();
        db = FirebaseFirestore.getInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ttestFragmentt()).commit();
        getDB();
        setMenuListener();

    }

    private void initUI() {
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

        drawerMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.adjective:
                        //Toast.makeText(MainActivity.this, "Adjective", Toast.LENGTH_SHORT).show();
                        try {
                            AdjectiveFragment adjectiveFragment = new AdjectiveFragment();
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            Bundle bundle = new Bundle();
                            bundle.putBundle("bundleAdjectiveRo", bundleAdjectiveRo);
                            bundle.putBundle("bundleAdjectiveEn", bundleAdjectiveEn);
                            adjectiveFragment.setArguments(bundle);
                            transaction.replace(R.id.fragment_container, adjectiveFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.animale:
                        //Toast.makeText(MainActivity.this, "Animale", Toast.LENGTH_SHORT).show();
                        try {
                            AnimaleFragment animaleFragment = new AnimaleFragment();
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            Bundle bundle = new Bundle();
                            bundle.putBundle("bundleAnimaleRo", bundleAnimaleRo);
                            bundle.putBundle("bundleAnimaleEn", bundleAnimaleEn);
                            animaleFragment.setArguments(bundle);
                            transaction.replace(R.id.fragment_container, animaleFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.obiecte:
                        //Toast.makeText(MainActivity.this, "Obiecte", Toast.LENGTH_SHORT).show();
                        try {
                            ObiecteFragment obiecteFragment = new ObiecteFragment();
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            Bundle bundle = new Bundle();
                            bundle.putBundle("bundleObiecteRo", bundleObiecteRo);
                            bundle.putBundle("bundleObiecteEn", bundleObiecteEn);
                            obiecteFragment.setArguments(bundle);
                            transaction.replace(R.id.fragment_container, obiecteFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.pronume:
                        try {
                            PronumeFragment pronumeFragment = new PronumeFragment();
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            Bundle bundle = new Bundle();
                            bundle.putBundle("bundlePronumeEn", bundlePronumeEn);
                            bundle.putBundle("bundlePronumeRo", bundlePronumeRo);
                            pronumeFragment.setArguments(bundle);
                            transaction.replace(R.id.fragment_container, pronumeFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.fructe:
                        //Toast.makeText(MainActivity.this, "Fructe", Toast.LENGTH_SHORT).show();
                        try {
                            FructeFragment fructeFragment = new FructeFragment();
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            Bundle bundle = new Bundle();
                            bundle.putBundle("bundleFructeRo", bundleFructeRo);
                            bundle.putBundle("bundleFructeEn", bundleFructeEn);
                            fructeFragment.setArguments(bundle);
                            transaction.replace(R.id.fragment_container, fructeFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.zileSaptamana:
                        //Toast.makeText(MainActivity.this, "zileSaptamana", Toast.LENGTH_SHORT).show();
                        try {
                            ZileSaptamanaFragment zileSaptamanaFragment = new ZileSaptamanaFragment();
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            Bundle bundle = new Bundle();
                            bundle.putBundle("bundleZileRo", bundleZileRo);
                            bundle.putBundle("bundleZileEn", bundleZileEn);
                            zileSaptamanaFragment.setArguments(bundle);
                            transaction.replace(R.id.fragment_container, zileSaptamanaFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;
                    case R.id.legume:
                        //Toast.makeText(MainActivity.this, "Legume", Toast.LENGTH_SHORT).show();
                        try {
                            LegumeFragment legumeFragment = new LegumeFragment();
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            Bundle bundle = new Bundle();
                            bundle.putBundle("bundleLegumeRo", bundleLegumeRo);
                            bundle.putBundle("bundleLegumeEn", bundleLegumeEn);
                            legumeFragment.setArguments(bundle);
                            transaction.replace(R.id.fragment_container, legumeFragment);
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

    public void getDB() {
        getPronume();
        getAdjective();
        getAnimale();
        getFructe();
        getLegume();
        getObiecte();
        getZileSaptamana();
    }

    public void getPronume() {
        db.collection("cuvinte").document("pronumeEngleza").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot pronumeEngleza = task.getResult();
                List<String> pronume = (List<String>) pronumeEngleza.get("pronume");
                ArrayList<String> pronumeEn = new ArrayList<>(pronume.size());
                pronumeEn.addAll(pronume);
                Log.d("myTag", pronume.toString());
                bundlePronumeEn.putStringArrayList("pronumeEngleza", pronumeEn);

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
                bundlePronumeRo.putStringArrayList("pronumeRomana", pronumeRo);

            }
        });
    }

    public void getAdjective() {
        db.collection("cuvinte").document("adjectiveRomana").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot adjectiveRomana = task.getResult();
                List<String> adjective = (List<String>) adjectiveRomana.get("complimente");
                ArrayList<String> adjectiveRo = new ArrayList<>(adjective.size());
                adjectiveRo.addAll(adjective);
                Log.d("myTag", adjective.toString());
                bundleAdjectiveRo.putStringArrayList("adjectiveRomana", adjectiveRo);

            }
        });
        db.collection("cuvinte").document("adjectiveEngleza").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot adjectiveEngleza = task.getResult();
                List<String> adjective = (List<String>) adjectiveEngleza.get("complimente");
                ArrayList<String> adjectiveEn = new ArrayList<>(adjective.size());
                adjectiveEn.addAll(adjective);
                Log.d("myTag", adjective.toString());
                bundleAdjectiveEn.putStringArrayList("adjectiveEngleza", adjectiveEn);

            }
        });
    }

    public void getFructe() {
        db.collection("cuvinte").document("fructeEngleza").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot fructeEngleza = task.getResult();
                List<String> fructe = (List<String>) fructeEngleza.get("fructe");
                ArrayList<String> fructeEn = new ArrayList<>(fructe.size());
                fructeEn.addAll(fructe);
                Log.d("myTag", fructe.toString());
                bundleFructeEn.putStringArrayList("fructeEngleza", fructeEn);

            }
        });
        db.collection("cuvinte").document("fructeRomana").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot fructeRomana = task.getResult();
                List<String> fructe = (List<String>) fructeRomana.get("fructe");
                ArrayList<String> fructeRo = new ArrayList<>(fructe.size());
                fructeRo.addAll(fructe);
                Log.d("myTag", fructeRo.toString());
                bundleFructeRo.putStringArrayList("fructeRomana", fructeRo);

            }
        });
    }

    public void getObiecte() {
        db.collection("cuvinte").document("substantiveEngleza").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot obiecteEngleza = task.getResult();
                List<String> obiecte = (List<String>) obiecteEngleza.get("obiecte");
                ArrayList<String> obiecteEn = new ArrayList<>(obiecte.size());
                obiecteEn.addAll(obiecte);
                Log.d("myTag", obiecte.toString());
                bundleObiecteEn.putStringArrayList("obiecteEngleza", obiecteEn);

            }
        });
        db.collection("cuvinte").document("substantiveRomana").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot obiecteRomana = task.getResult();
                List<String> obiecte = (List<String>) obiecteRomana.get("obiecte");
                ArrayList<String> obiecteRo = new ArrayList<>(obiecte.size());
                obiecteRo.addAll(obiecte);
                Log.d("myTag", obiecte.toString());
                bundleObiecteRo.putStringArrayList("obiecteRomana", obiecteRo);

            }
        });
    }

    public void getLegume() {
        db.collection("cuvinte").document("legumeEngleza").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot legumeEngleza = task.getResult();
                List<String> legume = (List<String>) legumeEngleza.get("legume");
                ArrayList<String> legumeEn = new ArrayList<>(legume.size());
                legumeEn.addAll(legume);
                Log.d("myTag", legume.toString());
                bundleLegumeEn.putStringArrayList("legumeEngleza", legumeEn);

            }
        });
        db.collection("cuvinte").document("legumeRomana").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot legumeRomana = task.getResult();
                List<String> legume = (List<String>) legumeRomana.get("legume");
                ArrayList<String> legumeRo = new ArrayList<>(legume.size());
                legumeRo.addAll(legume);
                Log.d("myTag", legume.toString());
                bundleLegumeRo.putStringArrayList("legumeRomana", legumeRo);

            }
        });
    }

    public void getZileSaptamana() {
        db.collection("cuvinte").document("zileSaptamanaEngleza").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot zileSaptamanaEngleza = task.getResult();
                List<String> zile = (List<String>) zileSaptamanaEngleza.get("zile");
                ArrayList<String> zileEn = new ArrayList<>(zile.size());
                zileEn.addAll(zile);
                Log.d("myTag", zile.toString());
                bundleZileEn.putStringArrayList("zileSaptamanaEngleza", zileEn);

            }
        });
        db.collection("cuvinte").document("zileSaptamanaRomana").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot zileSaptamanaRomana = task.getResult();
                List<String> zile = (List<String>) zileSaptamanaRomana.get("zile");
                ArrayList<String> zileRo = new ArrayList<>(zile.size());
                zileRo.addAll(zile);
                Log.d("myTag", zile.toString());
                bundleZileRo.putStringArrayList("zileSaptamanaRomana", zileRo);

            }
        });
    }

    public void getAnimale() {
        db.collection("cuvinte").document("animaleEngleza").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot animaleEngleza = task.getResult();
                List<String> animale = (List<String>) animaleEngleza.get("animale");
                ArrayList<String> animaleEn = new ArrayList<>(animale.size());
                animaleEn.addAll(animale);
                Log.d("myTag", animale.toString());
                bundleAnimaleEn.putStringArrayList("animaleEngleza", animaleEn);

            }
        });
        db.collection("cuvinte").document("animaleRomana").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot animaleRomana = task.getResult();
                List<String> animale = (List<String>) animaleRomana.get("animale");
                ArrayList<String> animaleRo = new ArrayList<>(animale.size());
                animaleRo.addAll(animale);
                Log.d("myTag", animale.toString());
                bundleAnimaleRo.putStringArrayList("animaleRomana", animaleRo);

            }
        });
    }

    public void getQALocatie() {
        db.collection("cuvinte").document("intrebariLocatieRomana").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot intrebareLocatieRomana = task.getResult();
                List<String> intrebari = (List<String>) intrebareLocatieRomana.get("intrebari");
                ArrayList<String> intrebariLocatieRo = new ArrayList<>(intrebari.size());
                intrebariLocatieRo.addAll(intrebari);
                Log.d("myTag", intrebari.toString());
                bundleIntrebariLocRo.putStringArrayList("intrebariLocatieRo", intrebariLocatieRo);

            }
        });
        db.collection("cuvinte").document("intrebariLocatieEngleza").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot intrebareLocatieEngleza = task.getResult();
                List<String> intrebari = (List<String>) intrebareLocatieEngleza.get("intrebari");
                ArrayList<String> intrebariLocatieEn = new ArrayList<>(intrebari.size());
                intrebariLocatieEn.addAll(intrebari);
                Log.d("myTag", intrebari.toString());
                bundleIntrebariLocEn.putStringArrayList("intrebariLocatieEn", intrebariLocatieEn);

            }
        });
        db.collection("cuvinte").document("raspunsuriLocatieEngleza").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot raspunsLocatieEngleza = task.getResult();
                List<String> intrebari = (List<String>) raspunsLocatieEngleza.get("raspunsuri");
                ArrayList<String> raspunsuriLocatieEn = new ArrayList<>(intrebari.size());
                raspunsuriLocatieEn.addAll(intrebari);
                Log.d("myTag", intrebari.toString());
                bundleRaspunsuriLocEn.putStringArrayList("raspunsuriLocatieEn", raspunsuriLocatieEn);

            }
        });
        db.collection("cuvinte").document("raspunsuriLocatieRomana").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot raspunsLocatieRomana = task.getResult();
                List<String> intrebari = (List<String>) raspunsLocatieRomana.get("raspunsuri");
                ArrayList<String> raspunsuriLocatieRo = new ArrayList<>(intrebari.size());
                raspunsuriLocatieRo.addAll(intrebari);
                Log.d("myTag", intrebari.toString());
                bundleRaspunsuriLocRo.putStringArrayList("raspunsuriLocatieRo", raspunsuriLocatieRo);

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
