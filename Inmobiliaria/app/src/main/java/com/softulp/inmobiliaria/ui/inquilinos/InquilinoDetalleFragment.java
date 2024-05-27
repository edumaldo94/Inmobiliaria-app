package com.softulp.inmobiliaria.ui.inquilinos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.softulp.inmobiliaria.databinding.FragmentInquilinoDetalleBinding;
import com.softulp.inmobiliaria.modelo.Inquilino;

public class InquilinoDetalleFragment extends Fragment {

    private InquilinoDetalleViewModel vm;
    private FragmentInquilinoDetalleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentInquilinoDetalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);
        vm.getInquilino(getArguments());


vm.getmInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
    @Override
    public void onChanged(Inquilino inquilino) {
        Log.d("salidaInflate",inquilino.getNombre());
        binding.tvCodigoFI.setText(inquilino.getId_Inquilino() + "");
        binding.tvApellidoFI.setText(String.valueOf(inquilino.getApellido()));
        binding.tvNombreFI.setText(String.valueOf(inquilino.getNombre()));
        binding.tvDniFI.setText(String.valueOf(inquilino.getDni()));
        binding.tvEmailFI.setText(String.valueOf(inquilino.getEmail()));
        binding.tvTelFI.setText(String.valueOf(inquilino.getTelefono() + ""));

    }
});
        return root;
    }

}