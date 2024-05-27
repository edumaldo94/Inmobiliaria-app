package com.softulp.inmobiliaria.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class InmuebleResponse implements Serializable {
    @SerializedName("$values")
    private List<Inmueble> inmuebles;

    public List<Inmueble> getInmuebles() {
        return inmuebles;
    }

    public void setInmuebles(List<Inmueble> inmuebles) {
        this.inmuebles = inmuebles;
    }
}
