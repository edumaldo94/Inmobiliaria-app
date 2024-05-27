package com.softulp.inmobiliaria.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softulp.inmobiliaria.R;
import com.softulp.inmobiliaria.databinding.FragmentInmuebleBinding;
import com.softulp.inmobiliaria.modelo.Inmueble;

import java.util.List;

public class InmuebleFragment extends Fragment {

    private FragmentInmuebleBinding binding;
    private InmuebleViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm =
                new ViewModelProvider(this).get(InmuebleViewModel.class);

        binding = FragmentInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getmListInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                RecyclerView rv=getActivity().findViewById(R.id.rvInmuebles);
                GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(), 1,GridLayoutManager.VERTICAL,false);
                rv.setLayoutManager(gridLayoutManager);
                InmuebleAdapter adapter=new InmuebleAdapter(inmuebles,getActivity(),getLayoutInflater());
                rv.setAdapter(adapter);
            }
        });

        binding.btnAgregarInm.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Navigation.findNavController(v).navigate(R.id.inmuebleAgregarFragment);
    }
});


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}