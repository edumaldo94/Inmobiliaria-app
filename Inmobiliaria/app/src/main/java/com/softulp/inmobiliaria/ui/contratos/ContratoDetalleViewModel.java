package com.softulp.inmobiliaria.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softulp.inmobiliaria.modelo.Contrato;
import com.softulp.inmobiliaria.modelo.Inmueble;
import com.softulp.inmobiliaria.request.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> mContrato;
    Context context;

    public ContratoDetalleViewModel(@NonNull Application application) {
        super(application);
        mContrato = new MutableLiveData<>();
        context=application;


    }
    public LiveData<Contrato> getmContrato(){
        return mContrato;
    }
    public void getContrato(Bundle bundle){
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmuebleAlqu");
        int id=inmueble.getId_Inmuebles();
        String token = ApiService.leerToken(context);

        ApiService.ApiInterface apiService = ApiService.getApiInferface();
        Call<Contrato> llamada = apiService.obtenerContrato(token,id);

        llamada.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if (response.isSuccessful()) {
                    Log.d("salidaC4", "SALIDA  " + response.body().toString());
                    mContrato.setValue(response.body());
                } else {
                    Log.d("salidaC5", "ELSE " + response.raw().message());
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Log.d("salidaC6", "ERROR " + t.getMessage());
            }
        });
    }
}