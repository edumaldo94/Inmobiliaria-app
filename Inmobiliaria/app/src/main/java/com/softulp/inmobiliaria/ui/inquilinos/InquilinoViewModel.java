package com.softulp.inmobiliaria.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softulp.inmobiliaria.modelo.Inmueble;
import com.softulp.inmobiliaria.modelo.InmuebleResponse;
import com.softulp.inmobiliaria.request.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel {
    private MutableLiveData<List<Inmueble>> mListInmueblesAlqu;
    Context context;

    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        mListInmueblesAlqu = new MutableLiveData<>();
        context = application;
        cargarInmuebles();
    }

    public LiveData<List<Inmueble>> getmListInmueblesAlqu() {
        return mListInmueblesAlqu;
    }

    public void cargarInmuebles() {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInferface();
        Call<InmuebleResponse> llamada = apiService.obtenerInmueblesAlq(token);

        llamada.enqueue(new Callback<InmuebleResponse>() {
            @Override
            public void onResponse(Call<InmuebleResponse> call, Response<InmuebleResponse> response) {
                if (response.isSuccessful()) {
                    InmuebleResponse inmuebleResponse = response.body();
                    mListInmueblesAlqu.postValue(inmuebleResponse.getInmuebles());
                } else {
                    Log.d("salidaQ21", "ELSE " + response.raw().message());
                }
            }

            @Override
            public void onFailure(Call<InmuebleResponse> call, Throwable t) {
                Log.d("salidaQ22", "ERROR " + t.getMessage());
            }
        });
    }
}