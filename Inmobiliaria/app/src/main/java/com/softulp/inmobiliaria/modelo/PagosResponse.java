package com.softulp.inmobiliaria.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PagosResponse {
    @SerializedName("pagos")
    private ArrayList<Pago> pagos;

    public ArrayList<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(ArrayList<Pago> pagos) {
        this.pagos = pagos;
    }
}
