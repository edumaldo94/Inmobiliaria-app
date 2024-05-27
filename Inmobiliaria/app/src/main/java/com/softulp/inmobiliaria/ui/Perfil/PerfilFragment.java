package com.softulp.inmobiliaria.ui.Perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.softulp.inmobiliaria.R;
import com.softulp.inmobiliaria.databinding.FragmentPerfilBinding;
import com.softulp.inmobiliaria.modelo.Propietario;
import com.softulp.inmobiliaria.request.ApiService;

public class PerfilFragment extends Fragment {
    private FragmentPerfilBinding binding;
    private PerfilViewModel pvm;
    private Button btnCambiarClave;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        pvm = new ViewModelProvider(this).get(PerfilViewModel.class);

        pvm.getMpropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
Log.d("salidaA",propietario.getId_Propietario()+"");
                    binding.etCodigo.setText(propietario.getId_Propietario()+"");
                    binding.editNombre.setText(propietario.getNombre());
                    binding.editApellido.setText(propietario.getApellido());
                    binding.editDni.setText(propietario.getDni());
                //   binding.editClave.setText(propietario.getClave());
                    binding.editCorreo.setText(propietario.getEmail());
                    binding.editTelefono.setText(propietario.getTelefono());

            }
        });
pvm.getMactivar().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
    @Override
    public void onChanged(Boolean aBoolean) {
      //  binding.etCodigo.setEnabled(aBoolean);
        binding.editNombre.setEnabled(aBoolean);
        binding.editApellido.setEnabled(aBoolean);
        binding.editDni.setEnabled(aBoolean);
        binding.editTelefono.setEnabled(aBoolean);

    }
});
        pvm.getMeditar().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.buttonGuardar.setText(s);
            }
        });
        binding.buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Propietario p=new Propietario(binding.etCodigo.getId(),binding.editNombre.getText().toString(),binding.editApellido.getText().toString(),
                        binding.editDni.getText().toString(), binding.editCorreo.getText().toString()
                        ,binding.editTelefono.getText().toString());
//public Propietario(String nombre, String apellido, String dni, String email, String clave, String telefono)

                pvm.editarPerfil(p,binding.buttonGuardar.getText().toString());


            }
        });

        pvm.llenarPerfil();
        btnCambiarClave = binding.btnCambiarClave;
        btnCambiarClave.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(root);
            navController.navigate(R.id.action_nav_perfilFragment_to_claveFragment);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}