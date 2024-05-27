package com.softulp.inmobiliaria.ui.pagos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softulp.inmobiliaria.modelo.Contrato;
import com.softulp.inmobiliaria.modelo.Pago;
import com.softulp.inmobiliaria.modelo.PagosResponse;
import com.softulp.inmobiliaria.request.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagoViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Pago>> mPagos;
    private Context context;

    public PagoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        mPagos = new MutableLiveData<>();
    }

    public LiveData<ArrayList<Pago>> getmPagos() {
        return mPagos;
    }

    public void getPagos(Bundle bundle) {
        Contrato contrato = (Contrato) bundle.getSerializable("contrato");
        String token = ApiService.leerToken(context);
        int id = contrato.getId_Contrato();
        Log.d("salidaPP6", contrato.getId_Contrato() + "");

        ApiService.ApiInterface apiService = ApiService.getApiInferface();
        Call<ArrayList<Pago>> llamada = apiService.obtenerPagos(token, id);

        llamada.enqueue(new Callback<ArrayList<Pago>>() {
            @Override
            public void onResponse(Call<ArrayList<Pago>> call, Response<ArrayList<Pago>> response) {
                if (response.isSuccessful()) {
                    Log.d("salidaPP1", "SALIDA  " + response.body().toString());
                    mPagos.postValue(response.body());
                } else {
                    Log.d("salidaPP2", "ELSE " + response.raw().message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pago>> call, Throwable t) {
                Log.d("salidaPP3", "ERROR " + t.getMessage());
            }
        });
    }
}