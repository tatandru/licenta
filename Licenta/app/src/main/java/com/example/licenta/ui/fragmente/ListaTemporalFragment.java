package com.example.licenta.ui.fragmente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.licenta.R;
import com.example.licenta.adapter.CuvinteRecyclerView;

import java.util.ArrayList;

public class ListaTemporalFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<String> intrebareRomanaArray;
    private ArrayList<String> intrebareEnglezaArray;
    private ArrayList<String> raspunsuriRomanaArray;
    private ArrayList<String> raspunsuriEnglezaArray;
    private CuvinteRecyclerView adapter;
    private RecyclerView.LayoutManager layoutManager;
    private NavController navController;
    private Bundle bundleQRo;
    private Bundle bundleQEn;
    private Bundle bundleAEn;
    private Bundle bundleARo;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.optiune_layout, container, false);
        recyclerView = view.findViewById(R.id.rv_optiuni);
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
        try {
            bundleQEn = getArguments().getBundle("bundleIntrebariTempEn");
            bundleQRo = getArguments().getBundle("bundleIntrebariTempRo");
            bundleAEn = getArguments().getBundle("bundleRaspunsuriTempEn");
            bundleARo = getArguments().getBundle("bundleRaspunsuriTempRo");

            intrebareRomanaArray = bundleQRo.getStringArrayList("intrebariTempRo");
            intrebareEnglezaArray = bundleQEn.getStringArrayList("intrebariTempEn");
            raspunsuriRomanaArray = bundleARo.getStringArrayList("raspunsuriTempRo");
            raspunsuriEnglezaArray = bundleAEn.getStringArrayList("raspunsuriTempEn");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setupList();
        return view;
    }

    private void setupList() {
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter = new CuvinteRecyclerView(intrebareRomanaArray, getContext());
        adapter.setCategoryClickListener(new CuvinteRecyclerView.ItemClickListener() {
            @Override
            public void onClick(String cuvant, int pos) {

                Bundle bundle = new Bundle();
                bundle.putBundle("bundleIntrebariTempEn", bundleQEn);
                bundle.putBundle("bundleIntrebariTempRo", bundleQRo);
                bundle.putBundle("bundleRaspunsuriTempEn", bundleAEn);
                bundle.putBundle("bundleRaspunsuriTempRo", bundleARo);
                bundle.putInt("pozitie", pos);
                navController.navigate(R.id.action_listaTemporalFragment_to_temporalFragment, bundle);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}