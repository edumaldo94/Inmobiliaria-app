package com.softulp.inmobiliaria;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softulp.inmobiliaria.modelo.Propietario;
import com.softulp.inmobiliaria.request.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Propietario> mPropietario;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mPropietario = new MutableLiveData<>();
        context = application;
    }

    public LiveData<Propietario> getmPropietario() {
        return mPropietario;
    }

    public void cargarUser() {
        String token = ApiService.leerToken(context);
        Log.d("salida", "TOKEN: " + token);
        ApiService.ApiInterface apiInterface = ApiService.getApiInferface();
        Call<Propietario> llamada = apiInterface.obtenerPropietario(token);
        llamada.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {
                    Log.d("salida ", response.body().toString());
                    mPropietario.setValue(response.body());
                    Log.d("salida ", "AVATAR DESDE VM: " + mPropietario.getValue().getAvatar() + "");
                } else {

                    Log.d("salida respuesta ", response.raw().message());
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("salida falla ", t.getMessage());
            }
        });
    }


}
