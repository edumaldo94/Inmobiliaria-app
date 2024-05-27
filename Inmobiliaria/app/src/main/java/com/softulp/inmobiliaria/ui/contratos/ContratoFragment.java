package com.softulp.inmobiliaria.ui.contratos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softulp.inmobiliaria.R;
import com.softulp.inmobiliaria.databinding.FragmentContratoBinding;

public class ContratoFragment extends Fragment {

    private FragmentContratoBinding binding;
    private ContratoViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm =
                new ViewModelProvider(this).get(ContratoViewModel.class);

        binding = FragmentContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.cargarInmuebles();

        vm.getmListInmueblesAlqu().observe(getViewLifecycleOwner(), inmuebles -> {
            RecyclerView rv=getActivity().findViewById(R.id.rvContrato);
            GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(), 1,GridLayoutManager.VERTICAL,false);
            rv.setLayoutManager(gridLayoutManager);
            ContratoAdapter adapter=new ContratoAdapter(getActivity(),inmuebles,getLayoutInflater());
            rv.setAdapter(adapter);

        });

        return root;
    }



}