package com.softulp.inmobiliaria.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softulp.inmobiliaria.modelo.Inmueble;
import com.softulp.inmobiliaria.modelo.Inquilino;
import com.softulp.inmobiliaria.modelo.Propietario;
import com.softulp.inmobiliaria.request.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> mInquilino;
    private Context context;

    public InquilinoDetalleViewModel(@NonNull Application application) {
        super(application);
        mInquilino = new MutableLiveData<>();
        context=application;

    }


    public LiveData<Inquilino> getmInquilino() {
        if (mInquilino == null) {
            mInquilino = new MutableLiveData<>();
        }
        return mInquilino;
    }
    public void getInquilino(Bundle bundle){
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmuebleAlquInqui");
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInferface();
        Log.d("salidaQ55", "Inmueble ID: " + inmueble.getId_Inmuebles());
        Log.d("salidaQ66", "tok " + token);
        int id=inmueble.getId_Inmuebles();
        Call<Inquilino> llamada = apiService.obtenerInquiloAct(token,id);
        llamada.enqueue(new Callback<Inquilino>() {
            @Override
            public void onResponse(Call<Inquilino> call, Response<Inquilino> response) {
                if (response.isSuccessful()) {
                    mInquilino.postValue(response.body());
                    Log.d("salidaQ1",response.body().toString());
                } else {
                    Log.d("salidaQ2", "ELSE " + response.raw().message());
                }
            }
            @Override
            public void onFailure(Call<Inquilino> call, Throwable t) {
                Log.d("salidaQ3", "ERROR " + t.getMessage());
            }
        });


    }
}