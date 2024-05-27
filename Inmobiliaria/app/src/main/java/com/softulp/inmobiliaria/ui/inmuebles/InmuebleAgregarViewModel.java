package com.softulp.inmobiliaria.ui.inmuebles;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.softulp.inmobiliaria.R;
import com.softulp.inmobiliaria.modelo.Inmueble;
import com.softulp.inmobiliaria.request.ApiService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleAgregarViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayAdapter<CharSequence>> mAdapterTipo;
    private MutableLiveData<ArrayAdapter<CharSequence>> mAdapterUso;
    private MutableLiveData<Inmueble> mInmueble;
    private MutableLiveData<Uri> mImgUri;


    public InmuebleAgregarViewModel(@NonNull Application application) {
        super(application);
        mAdapterTipo = new MutableLiveData<>();
        mAdapterUso = new MutableLiveData<>();
        mImgUri=new MutableLiveData<>();
        mInmueble=new MutableLiveData<>();
   //   cargarSpiners(getApplication());
    }
    public LiveData<Uri> getmImgUri() {
        return mImgUri;
    }

    public LiveData<Inmueble> getmInmueble() {
        return mInmueble;
    }
    public LiveData<ArrayAdapter<CharSequence>> getAdapterTipo() {
        return mAdapterTipo;
    }

    public LiveData<ArrayAdapter<CharSequence>> getAdapterUso() {
        return mAdapterUso;
    }

    public void cargarImg(ActivityResult result){
        if(result.getData()!=null){
            mImgUri.setValue(result.getData().getData());
            Log.d("salidaI11",mImgUri.getValue().toString());
        }

    }


    public void cargarSpiners(Context context) {

        ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter.createFromResource(context,
                R.array.tipo_inmueble_array, android.R.layout.simple_spinner_item);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAdapterTipo.setValue(adapterTipo);

        ArrayAdapter<CharSequence> adapterUso = ArrayAdapter.createFromResource(context,
                R.array.uso_inmueble_array, android.R.layout.simple_spinner_item);
        adapterUso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAdapterUso.setValue(adapterUso);
    }

    public String convertirImgBase64(Uri uri) {
        try {
            ContentResolver contentResolver = getApplication().getContentResolver();
            InputStream inputStream = contentResolver.openInputStream(uri);

            if (inputStream != null) {
                // Decodifica la imagen en un objeto Bitmap
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2; // Puedes ajustar este valor para comprimir más o menos
                Bitmap originalBitmap = BitmapFactory.decodeStream(inputStream, null, options);

                // Comprime la imagen (ajusta la calidad según tus necesidades)
                int quality = 50; // Rango de 0 (más comprimido) a 100 (menos comprimido)
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                originalBitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);

                // Convierte la imagen comprimida a Base64
                byte[] imageBytes = byteArrayOutputStream.toByteArray();
                return Base64.encodeToString(imageBytes, Base64.DEFAULT).replace("\n", ""); // Eliminar caracteres de nueva línea
            } else {
                Log.d("salidaI12", "Error al cargar la imagen");
                return null;
            }
        } catch (IOException e) {
            // Handle error: Ocurrió un error al leer la imagen.
            e.printStackTrace();
            return null;
        }
    }

    public void crearInmueble(Uri uri, Inmueble i) {
        String token = ApiService.leerToken(getApplication());
        ApiService.ApiInterface apiService = ApiService.getApiInferface();

        Inmueble inmueble = i;
        Log.d("salidaPt", i.toString());
       // inmueble.setLatitud("");
      //  inmueble.setLongitud("");
        inmueble.setFoto(convertirImgBase64(uri));
        Log.d("salidaPt", token);
        Call<Inmueble> llamada = apiService.crearInmueble(token, inmueble);

        llamada.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()) {
                    mInmueble.postValue(response.body());
                    Log.d("salidaI13", response.body().toString());
                } else {
                    Log.d("salidaI14", "ELSE " + response.raw());

                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Log.d("salidaI15", "ERROR " + t.getMessage());

            }
        });
    }



}