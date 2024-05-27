package com.softulp.inmobiliaria.ui.pagos;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softulp.inmobiliaria.R;
import com.softulp.inmobiliaria.modelo.Contrato;
import com.softulp.inmobiliaria.modelo.Pago;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.ViewHolder> {
    private Context context;
    private List<Pago> pagos;
    private LayoutInflater li;

    public PagoAdapter(Context context, List<Pago> pagos, LayoutInflater li) {
        this.context = context;
        this.pagos = pagos != null ? pagos : new ArrayList<>();
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = li.inflate(R.layout.item_pago, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Pago pago = pagos.get(position);
        holder.codPago.setText(String.valueOf(pago.getPagoId()));
        holder.nroPago.setText(String.valueOf(pago.getNumeroPago()));
        holder.codContrato.setText(String.valueOf(pago.getContratoId()));
        holder.monto.setText(String.valueOf(pago.getImporte()));

        LocalDateTime fechaPago = pago.getFechaPago();
        if (fechaPago != null) {
            holder.fechaPago.setText(fechaPago.format(formatoFecha));
        } else {
            holder.fechaPago.setText("N/A"); // O cualquier texto predeterminado
        }
    }

    @Override
    public int getItemCount() {
        return pagos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView codPago;
        TextView nroPago;
        TextView monto;
        TextView codContrato;
        TextView fechaPago;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            codPago = itemView.findViewById(R.id.tvCodPagoItem);
            nroPago = itemView.findViewById(R.id.tvNroPagoItem);
            monto = itemView.findViewById(R.id.tvMontoItem);
            codContrato = itemView.findViewById(R.id.tvCodContratoItem);
            fechaPago = itemView.findViewById(R.id.tvFechaPagoItem);
        }
    }
}