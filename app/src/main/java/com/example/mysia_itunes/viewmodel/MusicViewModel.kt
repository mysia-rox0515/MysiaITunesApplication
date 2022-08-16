package com.example.mysia_itunes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysia_itunes.model.ClassicMusic
import com.example.mysia_itunes.model.MusicDetails
import com.example.mysia_itunes.model.PopMusic
import com.example.mysia_itunes.model.RockMusic
import com.example.mysia_itunes.model.remote.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicViewModel : ViewModel()
{
    /**
     * Rock Message
     */
    var _rock = MutableLiveData<List<MusicDetails>>()
    val rockMusic: LiveData<List<MusicDetails>>
        get() = _rock
    /**
     * Classic Message
     */

    var _classic = MutableLiveData<List<MusicDetails>>()
    val classicMusic: LiveData<List<MusicDetails>>
        get() = _classic
    /**
     * Pop Message
     */

    var _pop = MutableLiveData<List<MusicDetails>>()
    val popMusic: LiveData<List<MusicDetails>>
        get() = _pop

    /**
     * Error Message
     */
    private val _errorMessages = MutableLiveData("")
    val errorMessages: LiveData<String>
        get() = _errorMessages

    fun allMusic() {

        val rockcall = API.musicApi.getRockList()
        rockcall.enqueue(
            object : Callback<RockMusic> {
                override fun onResponse(call: Call<RockMusic>, response: Response<RockMusic>)
                {
                    if (response.isSuccessful) {
                        val body = response.body()
                        val resultMusic = body?.results
                        _rock.value = resultMusic

                    } else {
                        _errorMessages.value = response.message()
                    }
                }

                override fun onFailure(call: Call<RockMusic>, t: Throwable) {
                    t.printStackTrace()
                    _errorMessages.value = t.message ?: "Unknown error"
                    print(errorMessages)
                }

            }
        )

        val popCall = API.musicApi.getPopList()
        popCall.enqueue(
            object : Callback<PopMusic> {
                override fun onResponse(call: Call<PopMusic>, response: Response<PopMusic>)
                {
                    if (response.isSuccessful) {
                        var body = response.body()
                        val resultMusic = body?.results
                        _pop.value = resultMusic

                    } else {
                        _errorMessages.value = response.message()
                    }
                }
                override fun onFailure(call: Call<PopMusic>, t: Throwable) {
                    t.printStackTrace()
                    _errorMessages.value = t.message ?: "Unknown error"
                    print(errorMessages)
                }

            }
        )



        val classifCall = API.musicApi.getClassList()
        classifCall.enqueue(
            object : Callback<ClassicMusic> {
                override fun onResponse(call: Call<ClassicMusic>, response: Response<ClassicMusic>) {
                    if (response.isSuccessful) {
                        var body = response.body()
                        val resultMusic = body?.results
                        _classic.value = resultMusic



                    } else {
                        _errorMessages.value = response.message()
                    }
                }

                override fun onFailure(call: Call<ClassicMusic>, t: Throwable) {
                    t.printStackTrace()
                    _errorMessages.value = t.message ?: "Unknown error"
                    print(errorMessages)
                }

            }
        )
    }


}