package com.example.licenta;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.licenta.ui.fragmente.ttestFragmentt;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
    private NavController navController;
    final Bundle bundleIntrebariLocRo = new Bundle();
    final Bundle bundleIntrebariLocEn = new Bundle();
    final Bundle bundleRaspunsuriLocEn = new Bundle();
    final Bundle bundleRaspunsuriLocRo = new Bundle();
    final Bundle bundleIntrebariTempRo = new Bundle();
    final Bundle bundleIntrebariTempEn = new Bundle();
    final Bundle bundleRaspunsuriTempEn = new Bundle();
    final Bundle bundleRaspunsuriTempRo = new Bundle();
    final Bundle bundleIntrebariUmaneRo = new Bundle();
    final Bundle bundleIntrebariUmaneEn = new Bundle();
    final Bundle bundleRaspunsuriUmaneEn = new Bundle();
    final Bundle bundleRaspunsuriUmaneRo = new Bundle();
    final Bundle bundleIntrebariNecunoscutRo = new Bundle();
    final Bundle bundleIntrebariNecunoscutEn = new Bundle();
    final Bundle bundleRaspunsuriNecunoscutEn = new Bundle();
    final Bundle bundleRaspunsuriNecunoscutRo = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_menu);
        initUI();
        db = FirebaseFirestore.getInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ttestFragmentt()).commit();
        getDB();

    }

    private void initUI() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerMenu = findViewById(R.id.nv_drawer_menu);
        navController = Navigation.findNavController(this, R.id.fragment_container);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(drawerMenu, navController);
        drawerMenu.setNavigationItemSelectedListener(this);


    }

    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, drawerLayout);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.adjective:
                //Toast.makeText(MainActivity.this, "Adjective", Toast.LENGTH_SHORT).show();
                try {

                    Bundle bundle = new Bundle();
                    bundle.putBundle("bundleAdjectiveRo", bundleAdjectiveRo);
                    bundle.putBundle("bundleAdjectiveEn", bundleAdjectiveEn);
                    navController.navigate(R.id.action_ttestFragmentt_to_listaAdjectiveFragment,bundle);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.animale:
                //Toast.makeText(MainActivity.this, "Animale", Toast.LENGTH_SHORT).show();
                try {

                    Bundle bundle = new Bundle();
                    bundle.putBundle("bundleAnimaleRo", bundleAnimaleRo);
                    bundle.putBundle("bundleAnimaleEn", bundleAnimaleEn);
                    navController.navigate(R.id.action_ttestFragmentt_to_listaAnimaleFragment,bundle);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.obiecte:
                //Toast.makeText(MainActivity.this, "Obiecte", Toast.LENGTH_SHORT).show();
                try {
                    Bundle bundle = new Bundle();
                    bundle.putBundle("bundleObiecteRo", bundleObiecteRo);
                    bundle.putBundle("bundleObiecteEn", bundleObiecteEn);
                    navController.navigate(R.id.action_ttestFragmentt_to_listaObiecteFragment,bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.pronume:
                try {
                    Bundle bundle = new Bundle();
                    bundle.putBundle("bundlePronumeEn", bundlePronumeEn);
                    bundle.putBundle("bundlePronumeRo", bundlePronumeRo);
                    navController.navigate(R.id.action_ttestFragmentt_to_listaPronumeFragment,bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.fructe:
                //Toast.makeText(MainActivity.this, "Fructe", Toast.LENGTH_SHORT).show();
                try {
                    Bundle bundle = new Bundle();
                    bundle.putBundle("bundleFructeRo", bundleFructeRo);
                    bundle.putBundle("bundleFructeEn", bundleFructeEn);
                    navController.navigate(R.id.action_ttestFragmentt_to_listaFructeFragment,bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.zileSaptamana:
                //Toast.makeText(MainActivity.this, "zileSaptamana", Toast.LENGTH_SHORT).show();
                try {
                    Bundle bundle = new Bundle();
                    bundle.putBundle("bundleZileRo", bundleZileRo);
                    bundle.putBundle("bundleZileEn", bundleZileEn);
                    navController.navigate(R.id.action_ttestFragmentt_to_listaZileSaptamanaFragment,bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.legume:
                //Toast.makeText(MainActivity.this, "Legume", Toast.LENGTH_SHORT).show();
                try {
                    Bundle bundle = new Bundle();
                    bundle.putBundle("bundleLegumeRo", bundleLegumeRo);
                    bundle.putBundle("bundleLegumeEn", bundleLegumeEn);
                    navController.navigate(R.id.action_ttestFragmentt_to_listaLegumeFragment,bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.locatie:
                try {
                    Bundle bundle = new Bundle();
                    bundle.putBundle("bundleIntrebariLocEn", bundleIntrebariLocEn);
                    bundle.putBundle("bundleIntrebariLocRo", bundleIntrebariLocRo);
                    bundle.putBundle("bundleRaspunsuriLocEn", bundleRaspunsuriLocEn);
                    bundle.putBundle("bundleRaspunsuriLocRo", bundleRaspunsuriLocRo);
                    navController.navigate(R.id.action_ttestFragmentt_to_listaLocatieFragment,bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.temporal:
                try {
                    Bundle bundle = new Bundle();
                    bundle.putBundle("bundleIntrebariTempEn", bundleIntrebariTempEn);
                    bundle.putBundle("bundleIntrebariTempRo", bundleIntrebariTempRo);
                    bundle.putBundle("bundleRaspunsuriTempEn", bundleRaspunsuriTempEn);
                    bundle.putBundle("bundleRaspunsuriTempRo", bundleRaspunsuriTempRo);
                    navController.navigate(R.id.action_ttestFragmentt_to_listaTemporalFragment,bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.interumane:
                try {
                    Bundle bundle = new Bundle();
                    bundle.putBundle("bundleIntrebariUmaneEn", bundleIntrebariUmaneEn);
                    bundle.putBundle("bundleIntrebariUmaneRo", bundleIntrebariUmaneRo);
                    bundle.putBundle("bundleRaspunsuriUmaneEn", bundleRaspunsuriUmaneEn);
                    bundle.putBundle("bundleRaspunsuriUmaneRo", bundleRaspunsuriUmaneRo);
                    navController.navigate(R.id.action_ttestFragmentt_to_listaInterumaneFragment,bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.necunoscut:
                try {
                    Bundle bundle = new Bundle();
                    bundle.putBundle("bundleIntrebariNecunoscutEn", bundleIntrebariNecunoscutEn);
                    bundle.putBundle("bundleIntrebariNecunoscutRo", bundleIntrebariNecunoscutRo);
                    bundle.putBundle("bundleRaspunsuriNecunoscutEn", bundleRaspunsuriNecunoscutEn);
                    bundle.putBundle("bundleRaspunsuriNecunoscutRo", bundleRaspunsuriNecunoscutRo);
                    navController.navigate(R.id.action_ttestFragmentt_to_listaNecunoscutFragment,bundle);
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


    public void getDB() {
        getPronume();
        getAdjective();
        getAnimale();
        getFructe();
        getLegume();
        getObiecte();
        getZileSaptamana();
        getQAInterumane();
        getQALocatie();
        getQATemporale();
        getQANecunoscut();
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
                List<String> raspunsuri = (List<String>) raspunsLocatieEngleza.get("raspunsuri");
                ArrayList<String> raspunsuriLocatieEn = new ArrayList<>(raspunsuri.size());
                raspunsuriLocatieEn.addAll(raspunsuri);
                Log.d("myTag", raspunsuri.toString());
                bundleRaspunsuriLocEn.putStringArrayList("raspunsuriLocatieEn", raspunsuriLocatieEn);

            }
        });
        db.collection("cuvinte").document("raspunsuriLocatieRomana").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot raspunsLocatieRomana = task.getResult();
                List<String> raspunsuri = (List<String>) raspunsLocatieRomana.get("raspunsuri");
                ArrayList<String> raspunsuriLocatieRo = new ArrayList<>(raspunsuri.size());
                raspunsuriLocatieRo.addAll(raspunsuri);
                Log.d("myTag", raspunsuri.toString());
                bundleRaspunsuriLocRo.putStringArrayList("raspunsuriLocatieRo", raspunsuriLocatieRo);

            }
        });
    }

    public void getQATemporale() {
        db.collection("cuvinte").document("intrebariTemporaleRomana").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot intrebariTemporaleRomana = task.getResult();
                List<String> intrebari = (List<String>) intrebariTemporaleRomana.get("intrebari");
                ArrayList<String> intrebariTempRo = new ArrayList<>(intrebari.size());
                intrebariTempRo.addAll(intrebari);
                Log.d("myTag", intrebari.toString());
                bundleIntrebariTempRo.putStringArrayList("intrebariTempRo", intrebariTempRo);

            }
        });
        db.collection("cuvinte").document("intrebariTemporaleEngleza").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot intrebariTemporaleEngleza = task.getResult();
                List<String> intrebari = (List<String>) intrebariTemporaleEngleza.get("intrebari");
                ArrayList<String> intrebariTempEn = new ArrayList<>(intrebari.size());
                intrebariTempEn.addAll(intrebari);
                Log.d("myTag", intrebari.toString());
                bundleIntrebariTempEn.putStringArrayList("intrebariTempEn", intrebariTempEn);

            }
        });
        db.collection("cuvinte").document("raspunsuriTemporaleEngleza").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot raspunsuriTemporaleEngleza = task.getResult();
                List<String> raspunsuri = (List<String>) raspunsuriTemporaleEngleza.get("raspunsuri");
                ArrayList<String> raspunsuriTempEn = new ArrayList<>(raspunsuri.size());
                raspunsuriTempEn.addAll(raspunsuri);
                Log.d("myTag", raspunsuri.toString());
                bundleRaspunsuriTempEn.putStringArrayList("raspunsuriTempEn", raspunsuriTempEn);

            }
        });
        db.collection("cuvinte").document("raspunsuriTemporaleRomana").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot raspunsuriTemporaleRomana = task.getResult();
                List<String> intrebari = (List<String>) raspunsuriTemporaleRomana.get("raspunsuri");
                ArrayList<String> raspunsuriTempRo = new ArrayList<>(intrebari.size());
                raspunsuriTempRo.addAll(intrebari);
                Log.d("myTag", intrebari.toString());
                bundleRaspunsuriTempRo.putStringArrayList("raspunsuriTempRo", raspunsuriTempRo);

            }
        });
    }

    public void getQAInterumane() {
        db.collection("cuvinte").document("intrebariInterumaneRomana").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot intrebariInterumaneRomana = task.getResult();
                List<String> intrebari = (List<String>) intrebariInterumaneRomana.get("intrebari");
                ArrayList<String> intrebariUmaneRo = new ArrayList<>(intrebari.size());
                intrebariUmaneRo.addAll(intrebari);
                Log.d("myTag", intrebari.toString());
                bundleIntrebariUmaneRo.putStringArrayList("intrebariUmaneRo", intrebariUmaneRo);

            }
        });
        db.collection("cuvinte").document("intrebariInterumaneEngleza").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot intrebariInterumaneEngleza = task.getResult();
                List<String> intrebari = (List<String>) intrebariInterumaneEngleza.get("intrebari");
                ArrayList<String> intrebariUmaneEn = new ArrayList<>(intrebari.size());
                intrebariUmaneEn.addAll(intrebari);
                Log.d("myTag", intrebari.toString());
                bundleIntrebariUmaneEn.putStringArrayList("intrebariUmaneEn", intrebariUmaneEn);

            }
        });
        db.collection("cuvinte").document("raspunsuriInterumaneEngleza").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot raspunsuriInterumaneEngleza = task.getResult();
                List<String> raspunsuri = (List<String>) raspunsuriInterumaneEngleza.get("raspunsuri");
                ArrayList<String> raspunsuriUmaneEn = new ArrayList<>(raspunsuri.size());
                raspunsuriUmaneEn.addAll(raspunsuri);
                Log.d("myTag", raspunsuri.toString());
                bundleRaspunsuriUmaneEn.putStringArrayList("raspunsuriUmaneEn", raspunsuriUmaneEn);

            }
        });
        db.collection("cuvinte").document("raspunsuriInterumaneRomana").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot raspunsuriInterumaneRomana = task.getResult();
                List<String> intrebari = (List<String>) raspunsuriInterumaneRomana.get("raspunsuri");
                ArrayList<String> raspunsuriUmaneRo = new ArrayList<>(intrebari.size());
                raspunsuriUmaneRo.addAll(intrebari);
                Log.d("myTag", intrebari.toString());
                bundleRaspunsuriUmaneRo.putStringArrayList("raspunsuriUmaneRo", raspunsuriUmaneRo);

            }
        });
    }

    public void getQANecunoscut() {
        db.collection("cuvinte").document("intrebariNecunoscutRomana").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot intrebariNecunoscutRomana = task.getResult();
                List<String> intrebari = (List<String>) intrebariNecunoscutRomana.get("intrebari");
                ArrayList<String> intrebariNecunoscutRo = new ArrayList<>(intrebari.size());
                intrebariNecunoscutRo.addAll(intrebari);
                Log.d("myTag", intrebari.toString());
                bundleIntrebariNecunoscutRo.putStringArrayList("intrebariNecunoscutRo", intrebariNecunoscutRo);

            }
        });
        db.collection("cuvinte").document("intrebariNecunoscutEngleza").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot intrebariNecunoscutEngleza = task.getResult();
                List<String> intrebari = (List<String>) intrebariNecunoscutEngleza.get("intrebari");
                ArrayList<String> intrebariNecunoscutEn = new ArrayList<>(intrebari.size());
                intrebariNecunoscutEn.addAll(intrebari);
                Log.d("myTag", intrebari.toString());
                bundleIntrebariNecunoscutEn.putStringArrayList("intrebariNecunoscutEn", intrebariNecunoscutEn);

            }
        });
        db.collection("cuvinte").document("raspunsuriNecunoscutEngleza").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot raspunsuriNecunoscutEngleza = task.getResult();
                List<String> raspunsuri = (List<String>) raspunsuriNecunoscutEngleza.get("raspunsuri");
                ArrayList<String> raspunsuriNecunoscutEn = new ArrayList<>(raspunsuri.size());
                raspunsuriNecunoscutEn.addAll(raspunsuri);
                Log.d("myTag", raspunsuri.toString());
                bundleRaspunsuriNecunoscutEn.putStringArrayList("raspunsuriNecunoscutEn", raspunsuriNecunoscutEn);

            }
        });
        db.collection("cuvinte").document("raspunsuriNecunoscutRomana").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot raspunsuriNecunoscutRomana = task.getResult();
                List<String> intrebari = (List<String>) raspunsuriNecunoscutRomana.get("raspunsuri");
                ArrayList<String> raspunsuriNecunoscutRo = new ArrayList<>(intrebari.size());
                raspunsuriNecunoscutRo.addAll(intrebari);
                Log.d("myTag", intrebari.toString());
                bundleRaspunsuriNecunoscutRo.putStringArrayList("raspunsuriNecunoscutRo", raspunsuriNecunoscutRo);

            }
        });
    }


}
