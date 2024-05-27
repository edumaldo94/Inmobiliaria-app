package com.softulp.inmobiliaria;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softulp.inmobiliaria.modelo.Propietario;
import com.softulp.inmobiliaria.request.ApiService;
import com.softulp.inmobiliaria.modelo.Propietario;

import com.softulp.inmobiliaria.modelo.Usuario;
import com.softulp.inmobiliaria.request.ApiClient;
import com.softulp.inmobiliaria.ui.Perfil.RecuperarActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


    public class LoginActivityViewModel extends AndroidViewModel implements SensorEventListener {
        private Context context;
        private MutableLiveData<String> mError;
        private MutableLiveData<Boolean> mPermisoLlamada;
        private SensorManager sensorManager;
        private Sensor accelerometer;
        private float acceleration, currentAcceleration, lastAcceleration;
        private static final float SHAKE_THRESHOLD = 9.0f;

        public LoginActivityViewModel(@NonNull Application application) {
            super(application);
            context = application.getApplicationContext();
            mPermisoLlamada = new MutableLiveData<>();
            mError = new MutableLiveData<>();
            sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            acceleration = 0.0f;
            currentAcceleration = SensorManager.GRAVITY_EARTH;
            lastAcceleration = SensorManager.GRAVITY_EARTH;
            iniciarDeteccionDeAgitacion();

        }

        public LiveData<String> getMerror() {

            return mError;
        }

        public void login(String mail, String password) {
            ApiService.ApiInterface apiService = ApiService.getApiInferface();
            if (!mail.contains("@")) {
                mError.setValue("Ingrese un Correo Válido");
            } else {
                if (password.isEmpty()) {
                    mError.setValue("Debe ingresar una contraseña");
                } else {

                    Call<String> llamada = apiService.login(mail, password);
                    llamada.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful()) {
                                ApiService.guardarToken(context, "Bearer " + response.body());
                                Intent intent = new Intent(context, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            } else {
                                mError.setValue("Correo y/o Contraseña Incorrecta");
                                Log.d("salida", response.raw().message());
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("salida", t.getMessage());
                        }
                    });
                }
            }
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            lastAcceleration = currentAcceleration;
            currentAcceleration = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = currentAcceleration - lastAcceleration;
            acceleration = acceleration * 0.9f + delta;

            if (acceleration > SHAKE_THRESHOLD && context.checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                LoginActivityViewModel.this.hacerLlamadaInmobiliaria();
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        private void hacerLlamadaInmobiliaria() {

            String numeroInmobiliaria = "tel:+2657324466";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse(numeroInmobiliaria));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try {
                context.startActivity(intent);
            } catch (SecurityException e) {
                Toast.makeText(context, "Necesita permisos de llamada para hacer esta accion", Toast.LENGTH_SHORT).show();
                ;
            }
        }

        public void iniciarDeteccionDeAgitacion() {
            Log.d("salida", "SENSOR ACTIVADO");
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        public void detenerDeteccionDeAgitacion() {
            Log.d("salida", "SENSOR DESACTIVADO");

            sensorManager.unregisterListener(this);
        }

        public void iniciarRecupero(){
            Intent intent=new Intent(context, RecuperarActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }



    }
