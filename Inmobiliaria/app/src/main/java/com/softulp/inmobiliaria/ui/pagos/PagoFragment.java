package com.softulp.inmobiliaria.ui.pagos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softulp.inmobiliaria.databinding.FragmentPagoBinding;
import com.softulp.inmobiliaria.modelo.Pago;

import java.util.ArrayList;

public class PagoFragment extends Fragment {
    private PagoViewModel vm;
    private FragmentPagoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPagoBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(PagoViewModel.class);

        if (getArguments() != null) {
            vm.getPagos(getArguments());
        } else {
            Log.d("PagoFragment", "No arguments received");
        }


vm.getmPagos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Pago>>() {
    @Override
    public void onChanged(ArrayList<Pago> pagos) {
        RecyclerView rv = binding.rvPagos;
        GridLayoutManager gm = new GridLayoutManager(getContext(), 1, RecyclerView.VERTICAL, false);
        rv.setLayoutManager(gm);
        rv.setAdapter(new PagoAdapter(getContext(), pagos, getLayoutInflater()));
    }
});
        return binding.getRoot();
    }
}
