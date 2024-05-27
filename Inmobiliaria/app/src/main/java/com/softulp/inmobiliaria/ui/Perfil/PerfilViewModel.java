package com.softulp.inmobiliaria.ui.Perfil;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softulp.inmobiliaria.LoginActivity;
import com.softulp.inmobiliaria.MainActivity;
import com.softulp.inmobiliaria.modelo.Propietario;
import com.softulp.inmobiliaria.request.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private MutableLiveData<Propietario> mpropietario;
    private MutableLiveData<String> editar;
    private MutableLiveData<Boolean> activar;
    private Context context;
    public PerfilViewModel(@NonNull Application application) {
        super(application);
        mpropietario = new MutableLiveData<>();
        context=getApplication();// Inicialización aquí
    }

    public LiveData<Propietario> getMpropietario() {
        if (mpropietario == null) {
            mpropietario = new MutableLiveData<>();
        }
        return mpropietario;
    }
    public LiveData<String> getMeditar() {
        if (editar == null) {
            editar = new MutableLiveData<>();
        }
        return editar;
    }
    public LiveData<Boolean> getMactivar() {
        if (activar == null) {
            activar = new MutableLiveData<>();
        }
        return activar;
    }
    public void editarPerfil(Propietario p, String btntexto){
        if(btntexto.equals("Editar")){
            activar.setValue(true);
            editar.setValue("Guardar");

        }else {

            String token = ApiService.leerToken(context);
            ApiService.init(getApplication());
            //  ApiService.MisEndPoints api = Apicliente.getEndPoints();
            ApiService.ApiInterface apiService = ApiService.getApiInferface();
            // String token = Apicliente.getToken();

            Log.d("carajo2", token);
            Log.d("carajo2", p.toString());
            Call<Propietario> call = apiService.Editar(token, p);
            call.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if (response.isSuccessful()) {
                        Log.d("carajo1", token);
                        mpropietario.postValue(response.body());
                        Log.d("salidaE",response.body().getApellido());
                        Toast.makeText(getApplication(), "Usuario Actualizado Correctamente", Toast.LENGTH_SHORT).show();
editar.setValue("Editar");
activar.setValue(false);
                    } else {
                        Log.d("carajo", "Error: " + response.message());
                        Toast.makeText(getApplication(), "No se pudo actualizar el usauurio", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable throwable) {
                    Log.d("carajo", "response");
                    Toast.makeText(getApplication(), "Error en la solicitud", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
    public void llenarPerfil() {
        ApiService.init(getApplication());
        ApiService.ApiInterface apiService = ApiService.getApiInferface();
        String token= ApiService.leerToken(context);
        Log.d("salidasas", token);
        Call<Propietario> call = apiService.obtenerusuarioB(token);
        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {
                    mpropietario.postValue(response.body());
                    Log.d("salidaC",response.body().getId_Propietario()+"");

                } else {
                    Toast.makeText(getApplication(), "Usuario No encontrado", Toast.LENGTH_SHORT).show();
                    Log.d("salidaB", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable throwable) {
                Toast.makeText(getApplication(), "Error en la solicitud", Toast.LENGTH_SHORT).show();

            }
        });
    }
}