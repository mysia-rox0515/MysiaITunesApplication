package com.example.mysia_itunes.model.remote

import com.example.mysia_itunes.model.ClassicMusic
import com.example.mysia_itunes.model.PopMusic
import com.example.mysia_itunes.model.RockMusic
import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET(ROCK_ARGUMENTS)
    fun getRockList(): Call<RockMusic>

    @GET(CLASSIC_ARGUMENTS)
    fun getClassList():Call<ClassicMusic>

    @GET(POP_ARGUMENTS)
    fun getPopList():Call<PopMusic>


}