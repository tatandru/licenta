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

public class ListaInterumaneFragment extends Fragment {
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
            bundleQEn = getArguments().getBundle("bundleIntrebariUmaneEn");
            bundleQRo = getArguments().getBundle("bundleIntrebariUmaneRo");
            bundleAEn = getArguments().getBundle("bundleRaspunsuriUmaneEn");
            bundleARo = getArguments().getBundle("bundleRaspunsuriUmaneRo");
            intrebareRomanaArray = bundleQRo.getStringArrayList("intrebariUmaneRo");
            intrebareEnglezaArray = bundleQEn.getStringArrayList("intrebariUmaneEn");
            raspunsuriRomanaArray = bundleARo.getStringArrayList("raspunsuriUmaneRo");
            raspunsuriEnglezaArray = bundleAEn.getStringArrayList("raspunsuriUmaneEn");
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
                bundle.putBundle("bundleIntrebariUmaneEn", bundleQEn);
                bundle.putBundle("bundleIntrebariUmaneRo", bundleQRo);
                bundle.putBundle("bundleRaspunsuriUmaneEn", bundleAEn);
                bundle.putBundle("bundleRaspunsuriUmaneRo", bundleARo);
                bundle.putInt("pozitie", pos);
                navController.navigate(R.id.action_listaInterumaneFragment_to_interumaneFragment, bundle);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
