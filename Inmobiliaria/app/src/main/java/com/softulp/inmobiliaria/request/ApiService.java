package com.softulp.inmobiliaria.request;


import android.content.Context;
import android.content.SharedPreferences;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softulp.inmobiliaria.modelo.CambiarClaveModel;
import com.softulp.inmobiliaria.modelo.Contrato;
import com.softulp.inmobiliaria.modelo.Inmueble;
import com.softulp.inmobiliaria.modelo.InmuebleResponse;
import com.softulp.inmobiliaria.modelo.Inquilino;
import com.softulp.inmobiliaria.modelo.Pago;
import com.softulp.inmobiliaria.modelo.PagosResponse;
import com.softulp.inmobiliaria.modelo.Propietario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;

public class ApiService {

    public static final String URL_BASE = "http://192.168.0.101:5000/";
    private static SharedPreferences sharedPreferences;
    private static ApiInterface apiInterface;

    public static ApiInterface getApiInferface(){

        Gson gson= new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(LocalDateTime.class, new Contrato.LocalDateTimeDeserializer())
                .registerTypeAdapter(LocalDateTime.class, new Pago.LocalDateTimeDeserializer())
                .create();


        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiInterface=retrofit.create(ApiInterface.class);

        return apiInterface;


    }
    public static void init(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        }
    }
    public static void guardarToken(Context context, String token){
        SharedPreferences sp=context.getSharedPreferences("token.xml",0);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("token",token);
        editor.commit();

    }
    public static String leerToken(Context context){
        SharedPreferences sp=context.getSharedPreferences("token.xml",0);
        return sp.getString("token","");
    }
    public interface ApiInterface {


        ///////////////////////////////P R O P I E T A R I O///////////////////////////////////////////////

        @FormUrlEncoded
        @POST("api/Propietario/login")
        Call<String> login(@Field("Usuario") String usuario, @Field("Clave") String clave);


        @GET("api/Propietario/obtenerusuarioB")
        Call<Propietario> obtenerusuarioB(@Header("Authorization") String token);
        @PUT("api/Propietario/editar")
        Call<Propietario> Editar(@Header("Authorization") String token, @Body Propietario propietario);
        @PUT("api/Propietario/editClave")
        Call<Void> clave(@Header("Authorization") String token, @Body CambiarClaveModel model);
        @GET("api/Propietario/user")
        Call<Propietario> obtenerPropietario(@Header("Authorization") String token);

        @FormUrlEncoded
        @POST("api/Propietario/email")
        Call<Propietario> inciarRecupero(@Field("correo") String correo);


        ///////////////////////////////I N M U E B L E///////////////////////////////////////////////

        @GET("api/Inmueble/qweqwe")
        Call<InmuebleResponse> obtenerPropiedades(@Header("Authorization") String token);

        @GET("api/Inmueble/{id}")
        Call<Inmueble> obtenerInmueble(@Header("Authorization") String token,@Path("id") int id);

        @PUT("api/Inmueble/inmuEstado/{id}")
        Call<Inmueble> inmuEstado(@Header("Authorization") String token,@Path("id") int id);

        @POST("api/Inmueble/crear")
        Call<Inmueble> crearInmueble(@Header("Authorization") String token,@Body Inmueble inmueble);

        @GET("api/Inmueble/alquiladas")
        Call<InmuebleResponse> obtenerInmueblesAlq(@Header("Authorization") String token);

        @GET("api/Inquilino/inquiloActual/{id}")
        Call<Inquilino> obtenerInquiloAct(@Header("Authorization") String token, @Path("id") int id);
        ///////////////////////////////C O N T R A T O///////////////////////////////////////////////

        @GET("api/Contrato/{id}")
        Call<Contrato> obtenerContrato(@Header("Authorization") String token,@Path("id") int id);

        ///////////////////////////////P A G O S///////////////////////////////////////////////


        @GET("api/Pago/cadapago/{contratoId}")
        Call<ArrayList<Pago>> obtenerPagos(@Header("Authorization") String token, @Path("contratoId") int contratoId);





    }
}