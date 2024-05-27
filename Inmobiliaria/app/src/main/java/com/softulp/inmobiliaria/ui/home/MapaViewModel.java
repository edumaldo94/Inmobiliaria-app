package com.softulp.inmobiliaria.ui.home;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationRequest;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapaViewModel extends AndroidViewModel {

    private Context context;
    private static final LatLng imb = new LatLng(-33.150720, -66.306864);
    private MutableLiveData<MapaActual> mMapa;

    public MapaViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();

        mMapa = new MutableLiveData<>();
    }

    public LiveData<MapaActual> getmMapa() {
        return mMapa;
    }

    public void obtenerMapa() {
        MapaActual ma = new MapaActual();
        mMapa.setValue(ma);
    }

    public class MapaActual implements OnMapReadyCallback {

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            googleMap.addMarker(new MarkerOptions().position(imb).title("Inmobiliaria CR7"));
            CameraPosition camPos = new CameraPosition.Builder()
                    .target(imb)
                    .zoom(17)
                    .bearing(45)
                    .build();
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(camPos);
            googleMap.animateCamera(update);

        }

    }

}