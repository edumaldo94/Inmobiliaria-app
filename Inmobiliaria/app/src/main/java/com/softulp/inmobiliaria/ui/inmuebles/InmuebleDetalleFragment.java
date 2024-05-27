package com.softulp.inmobiliaria.ui.inmuebles;

import android.graphics.Bitmap;
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

import com.bumptech.glide.Glide;
import com.softulp.inmobiliaria.R;
import com.softulp.inmobiliaria.databinding.FragmentInmuebleDetalleBinding;
import com.softulp.inmobiliaria.modelo.Inmueble;
import com.softulp.inmobiliaria.request.ApiService;

public class InmuebleDetalleFragment extends Fragment {

    private InmuebleDetalleViewModel vm;

    private FragmentInmuebleDetalleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInmuebleDetalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);

        vm.getInmuebles(getArguments());

        vm.getMInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                binding.mySwitch.setChecked(inmueble.isDisponible());
                binding.tvAmbDetalle.setText(String.valueOf(inmueble.getAmbientes()));
                binding.tvDirDetalle.setText(inmueble.getDireccion());
                binding.tvCodigoDetalle.setText(inmueble.getId_Inmuebles() + "");
                binding.tvPrecioDetalle.setText("$" + inmueble.getPrecio());
                binding.tvTipoDetalle.setText(inmueble.getTipo());
                binding.tvUsoDetalle.setText(inmueble.getUso()+"");
                Log.d("salidaI10", ApiService.URL_BASE+inmueble.getFoto());
                String imageUrl =ApiService.URL_BASE+inmueble.getFoto();
                Glide.with(getContext().getApplicationContext())
                        .load(imageUrl)
                        .placeholder(R.drawable.fondo_casa2)
                        .into(binding.imgInmueble);
            }
        });
        vm.getmFotoInmueble().observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                binding.imgInmueble.setImageBitmap(bitmap);
            }
        });
        vm.getmLabelSwich().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.labelSwich.setText(s);
            }
        });

        binding.mySwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.setEstado(vm.getMInmueble().getValue().getId_Inmuebles());
            }
        });


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}