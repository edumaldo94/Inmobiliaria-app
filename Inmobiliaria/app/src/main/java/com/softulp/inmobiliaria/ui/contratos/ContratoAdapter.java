package com.softulp.inmobiliaria.ui.contratos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softulp.inmobiliaria.R;
import com.softulp.inmobiliaria.modelo.Inmueble;
import com.softulp.inmobiliaria.request.ApiService;

import java.util.List;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ViewHolder> {
    private Context context;
    private List<Inmueble> inmuebles;
    private LayoutInflater li;

public  ContratoAdapter(Context context, List<Inmueble> inmuebles, LayoutInflater li) {
        this.context = context;
        this.inmuebles = inmuebles;
        this.li = li;
        }


@NonNull
@Override
public ContratoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = li.inflate(R.layout.item_inquilino, parent, false);
        return new ContratoAdapter.ViewHolder(root);
        }

@Override
public void onBindViewHolder(@NonNull ContratoAdapter.ViewHolder holder, int position) {

        Glide.with(context)
        .load(ApiService.URL_BASE+inmuebles.get(position).getFoto())
        .into(holder.imagen);
        holder.direccion.setText(inmuebles.get(position).getDireccion());
        }


@Override
public int getItemCount() {
        return inmuebles.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder{
    ImageView imagen;
    TextView direccion;
    Button boton;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        imagen = itemView.findViewById(R.id.imgFotoInmueble);
        boton = itemView.findViewById(R.id.btnVerInquilinoDI);
        direccion = itemView.findViewById(R.id.tvCalleInmuebleAlqu);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmuebleAlqu", inmuebles.get(getAdapterPosition()));
                Navigation.findNavController(v).navigate(R.id.contratoDetalleFragment, bundle);
            }
        });
    }
}
}