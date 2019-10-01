package com.example.testandroid.Service;

import com.example.testandroid.Pojos.Amiibo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicioRetrofit {


    @GET("api/amiibo")
    Call<Amiibo> getPersonajes();

}
