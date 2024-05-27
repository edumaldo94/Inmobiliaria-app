package com.softulp.inmobiliaria.ui.contratos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.softulp.inmobiliaria.R;
import com.softulp.inmobiliaria.databinding.FragmentContratoDetalleBinding;

import java.time.format.DateTimeFormatter;

public class ContratoDetalleFragment extends Fragment {

    private ContratoDetalleViewModel vm;
    private FragmentContratoDetalleBinding binding;

    public static ContratoDetalleFragment newInstance() {
        return new ContratoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentContratoDetalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = new ViewModelProvider(this).get(ContratoDetalleViewModel.class);
        vm.getContrato(getArguments());
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        vm.getmContrato().observe(getViewLifecycleOwner(), contrato -> {
            String name=contrato.getInquilino().getApellido()+" "+contrato.getInquilino().getNombre();
            binding.tvCodigoCD.setText(contrato.getId_Contrato() + "");
            binding.tvFechaInicioCD.setText(contrato.getFecha_Inicio().format(formatoFecha));
            binding.tvFechaFinCD.setText(contrato.getFecha_Fin().format(formatoFecha));
            binding.tvMontoCD.setText("$"+String.valueOf(contrato.getMonto()));
            binding.tvInquilinoCD.setText(String.valueOf(name));
            binding.tvInmuebleCD.setText(String.valueOf(contrato.getInmueble().getDireccion()));
        });
      binding.btnPagos.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("contrato", vm.getmContrato().getValue());
            Navigation.findNavController(v).navigate(R.id.pagoFragment, bundle);
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = new ViewModelProvider(this).get(ContratoDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}