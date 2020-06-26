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

public class ListaPronumeFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<String> pronumeRomanaArray;
    private ArrayList<String> pronumeEnglezaArray;
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
            bundleRo = getArguments().getBundle("bundlePronumeRo");
            bundleEn = getArguments().getBundle("bundlePronumeEn");

            pronumeRomanaArray = bundleRo.getStringArrayList("pronumeRomana");
            pronumeEnglezaArray = bundleEn.getStringArrayList("pronumeEngleza");
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
        adapter = new CuvinteRecyclerView(pronumeRomanaArray, getContext());
        adapter.setCategoryClickListener(new CuvinteRecyclerView.ItemClickListener() {
            @Override
            public void onClick(String cuvant, int pos) {

                Bundle bundle = new Bundle();
                bundle.putBundle("bundlePronumeEn", bundleEn);
                bundle.putBundle("bundlePronumeRo", bundleRo);
                bundle.putInt("pozitie", pos);
                navController.navigate(R.id.action_listaPronumeFragment_to_pronumeFragment, bundle);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
