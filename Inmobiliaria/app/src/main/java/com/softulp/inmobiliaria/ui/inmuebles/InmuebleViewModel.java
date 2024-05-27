package com.softulp.inmobiliaria.ui.inmuebles;

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

public class InmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> mListInmuebles;
    private Context context;

    public InmuebleViewModel(@NonNull Application application) {
        super(application);
        mListInmuebles = new MutableLiveData<>();
        context = application;
       cargarInmuebles();

    }

    public LiveData<List<Inmueble>> getmListInmuebles() {
        if (mListInmuebles== null){
            mListInmuebles=new MutableLiveData<>();
        }

        return mListInmuebles;
    }

    public void cargarInmuebles() {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInferface();
        Call<InmuebleResponse> llamada = apiService.obtenerPropiedades(token);

        llamada.enqueue(new Callback<InmuebleResponse>() {
            @Override
            public void onResponse(Call<InmuebleResponse> call, Response<InmuebleResponse> response) {
                if (response.isSuccessful()) {
                    InmuebleResponse inmuebleResponse = response.body();
                    if (inmuebleResponse != null) {
                        mListInmuebles.postValue(inmuebleResponse.getInmuebles());
                        Log.d("salidaI333",response.body().getInmuebles()+" " );
                    } else {
                        Log.d("salidaI8", "Response body is null");
                    }
                } else {
                    Log.d("salidaI8", "ELSE " + response.raw());
                }
            }

            @Override
            public void onFailure(Call<InmuebleResponse> call, Throwable t) {
                Log.d("salidaI9", "ERROR " + t.getMessage());
            }
        });
    }

}