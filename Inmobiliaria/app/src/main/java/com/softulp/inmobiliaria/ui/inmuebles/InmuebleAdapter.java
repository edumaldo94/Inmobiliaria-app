package com.softulp.inmobiliaria.ui.inmuebles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolder>{
    private Context context;
    private List<Inmueble> inmuebles;
    private LayoutInflater li;

    public InmuebleAdapter( List<Inmueble> inmuebles,Context context, LayoutInflater li) {
        this.context = context;
        this.inmuebles = inmuebles;
        this.li = li;
    }

    @NonNull
    @Override
    public InmuebleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = li.inflate(R.layout.item_inmueble, parent, false);
        return new ViewHolder(root);
    }


    @Override
    public void onBindViewHolder(@NonNull InmuebleAdapter.ViewHolder holder, int position) {
        holder.calle.setText(inmuebles.get(position).getDireccion());
        holder.precio.setText("$"+inmuebles.get(position).getPrecio());
        Glide.with(context)
                .load(ApiService.URL_BASE+inmuebles.get(position).getFoto())
                .placeholder(R.drawable.imagen_default)
                .into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;
        TextView calle;
        TextView precio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imgFotoInmueble);
            calle = itemView.findViewById(R.id.tvCalleInmuebleII);
            precio = itemView.findViewById(R.id.tvPrecioII);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Inmueble inmueble = inmuebles.get(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("inmuebleSelec", inmueble);
                    Navigation.findNavController(v).navigate(R.id.inmuebleDetalleFragment, bundle);
                }
            });

        }
    }
}
