package com.example.firstkotlinapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstkotlinapp.model.RoverModel
import com.example.kotlintraining.api.Api
import com.example.kotlintraining.api.Instance
import kotlinx.coroutines.joinAll

class RoversViewModel : ViewModel() {

    var roverModels: MutableLiveData<ArrayList<RoverModel>> = MutableLiveData<ArrayList<RoverModel>>()


    suspend fun loadRoverPhotos() {
        val response = Instance.getInstance().create(Api::class.java).getRoverPhotos()

        if (response.isSuccessful) {
            if (response.body() != null) {
                for (r in response.body()!!.rovers) {
                    Log.d("RoverCreatesModel", "ADDING: " + r?.createRoverModel()!!.img_src)
                    r.let { roverModels.value?.add(it.createRoverModel()) }
                }
            }
        }
        joinAll()
    }
}




