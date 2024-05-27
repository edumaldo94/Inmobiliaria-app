package com.softulp.inmobiliaria.ui.Perfil;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.softulp.inmobiliaria.modelo.Propietario;
import com.softulp.inmobiliaria.request.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecuperarActivityViewModel extends AndroidViewModel {

    Context context;
    public RecuperarActivityViewModel(@NonNull Application application) {
        super(application);
        context=application;
    }
    public void  pedidoRecuperoClave(String correo){
        String token= ApiService.leerToken(context);
        Log.d("salidaR","TOKEN: "+token);
        ApiService.ApiInterface apiInterface= ApiService.getApiInferface();
        Call<Propietario> llamada=apiInterface.inciarRecupero(correo);
        llamada.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){

                    Log.d("salidaR ",response.body().toString());

                }else {

                    Log.d("salidaR respuesta ",response.raw().message());
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("salidaR falla ",t.getMessage());
            }
        });
    }
}
