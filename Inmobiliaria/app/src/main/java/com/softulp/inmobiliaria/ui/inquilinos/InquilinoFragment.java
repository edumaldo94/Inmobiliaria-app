package com.softulp.inmobiliaria.ui.inquilinos;

import androidx.lifecycle.Observer;
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
import com.softulp.inmobiliaria.databinding.FragmentInquilinoBinding;
import com.softulp.inmobiliaria.modelo.Inmueble;

import java.util.List;

public class InquilinoFragment extends Fragment {

    private FragmentInquilinoBinding binding;
    private InquilinoViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm =
                new ViewModelProvider(this).get(InquilinoViewModel.class);

        binding = FragmentInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.cargarInmuebles();


vm.getmListInmueblesAlqu().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
    @Override
    public void onChanged(List<Inmueble> inmuebles) {
        RecyclerView rv=getActivity().findViewById(R.id.rvInmueblesAlq);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(), 1,GridLayoutManager.VERTICAL,false);
        rv.setLayoutManager(gridLayoutManager);
        InquilinoAdapter adapter=new InquilinoAdapter(getActivity(),inmuebles,getLayoutInflater());
        rv.setAdapter(adapter);

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