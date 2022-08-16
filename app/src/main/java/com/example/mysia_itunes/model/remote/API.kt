package com.example.mysia_itunes.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {


    val musicApi: Service  by lazy {
        retrofit()
    }

    private fun retrofit(): Service
    {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Service::class.java)
    }


}