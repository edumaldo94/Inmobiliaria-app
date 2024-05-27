package com.softulp.inmobiliaria.ui.inmuebles;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.softulp.inmobiliaria.R;
import com.softulp.inmobiliaria.databinding.FragmentInmuebleAgregarBinding;
import com.softulp.inmobiliaria.modelo.Inmueble;

public class InmuebleAgregarFragment extends Fragment {

    private InmuebleAgregarViewModel vm;
    private FragmentInmuebleAgregarBinding binding;
    private ActivityResultLauncher<Intent> imagePickerLauncher;

    private Uri imgUri;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInmuebleAgregarBinding.inflate(getLayoutInflater());
        vm = new ViewModelProvider(this).get(InmuebleAgregarViewModel.class);

       vm.getAdapterTipo().observe(getViewLifecycleOwner(), new Observer<ArrayAdapter<CharSequence>>() {
           @Override
           public void onChanged(ArrayAdapter<CharSequence> charSequenceArrayAdapter) {
               binding.spinnerTipo.setAdapter(charSequenceArrayAdapter);
           }
       });
       vm.getAdapterUso().observe(getViewLifecycleOwner(), new Observer<ArrayAdapter<CharSequence>>() {
           @Override
           public void onChanged(ArrayAdapter<CharSequence> charSequenceArrayAdapter) {
               binding.spinnerUso.setAdapter(charSequenceArrayAdapter);
           }
       });

        vm.cargarSpiners(getContext().getApplicationContext());

         imgUri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.drawable.fondo_casa);
        binding.imgNuevoInmueble.setImageResource(R.drawable.imagen_default);

        binding.btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        vm.cargarImg(result);
                    }
                }
        );

      vm.getmImgUri().observe(getViewLifecycleOwner(), new Observer<Uri>() {
          @Override
          public void onChanged(Uri uri) {
             imgUri = uri;
              binding.imgNuevoInmueble.setImageURI(uri);
          }
      });
        binding.btnCrearInm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedTipo = binding.spinnerTipo.getSelectedItem().toString();
                String selectedUso = binding.spinnerUso.getSelectedItem().toString();
                int selectedTipoPosition = binding.spinnerTipo.getSelectedItemPosition();
                int selectedUsoPosition = binding.spinnerUso.getSelectedItemPosition();
                if (selectedTipoPosition == 0 || selectedUsoPosition == 0) {
                    Toast.makeText(getContext(), "Por favor seleccione un tipo y uso válidos.", Toast.LENGTH_SHORT).show();
                    return; // No continuar si no se han seleccionado opciones válidas
                }

                Inmueble inmueble = new Inmueble(0,"-3.472917266432e15","1.5076240373317632e16",
                        "-34.72917189931966, 150.76240804480017",
                        binding.etDireccionCrear.getText().toString(),
                        Integer.parseInt(binding.etAmbientesCrear.getText().toString()),
                        selectedTipo,
                        selectedUso,
                        Double.parseDouble(binding.etPrecioCrear.getText().toString()),
                        false,1,"");

                vm.crearInmueble(imgUri, inmueble);
            }
        });

vm.getmInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
    @Override
    public void onChanged(Inmueble inmueble) {
        Navigation.findNavController(requireView()).navigate(R.id.nav_inmuebleFragment);
    }
});


        return binding.getRoot();

    }


    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        imagePickerLauncher.launch(intent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }
}

