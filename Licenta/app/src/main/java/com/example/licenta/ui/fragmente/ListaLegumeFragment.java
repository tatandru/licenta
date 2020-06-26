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

public class ListaLegumeFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<String> legumeRomanaArray;
    private ArrayList<String> legumeEnglezaArray;
    private CuvinteRecyclerView adapter;
    private RecyclerView.LayoutManager layoutManager;
    private NavController navController;
    private Bundle bundleRo;
    private Bundle bundleEn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.optiune_layout, container, false);
        recyclerView = view.findViewById(R.id.rv_optiuni);
        navController = Navigation.findNavController(getActivity(), R.id.fragment_container);
        try {
             bundleRo = getArguments().getBundle("bundleLegumeRo");
             bundleEn = getArguments().getBundle("bundleLegumeEn");
            legumeRomanaArray = bundleRo.getStringArrayList("legumeRomana");
            legumeEnglezaArray = bundleEn.getStringArrayList("legumeEngleza");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setupList();
        return view;
    }

    private void setupList() {
        layoutManager = new LinearLayoutManager(getContext());  // use a linear layout manager vertical
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter = new CuvinteRecyclerView(legumeRomanaArray, getContext());
        adapter.setCategoryClickListener(new CuvinteRecyclerView.ItemClickListener() {
            @Override
            public void onClick(String cuvant,int pos) {

                Bundle bundle = new Bundle();
                bundle.putBundle("bundleLegumeRo", bundleRo);
                bundle.putBundle("bundleLegumeEn", bundleEn);
                bundle.putInt("pozitie",pos);
                navController.navigate(R.id.action_listaLegumeFragment_to_legumeFragment,bundle);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
