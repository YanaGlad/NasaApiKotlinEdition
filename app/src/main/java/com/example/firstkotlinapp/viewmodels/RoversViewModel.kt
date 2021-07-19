package com.example.firstkotlinapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstkotlinapp.model.Rover
import com.example.firstkotlinapp.model.RoverModel
import com.example.kotlintraining.api.Api
import com.example.kotlintraining.api.Instance
import kotlinx.coroutines.joinAll

class RoversViewModel : ViewModel() {

    var roverModels: MutableLiveData<ArrayList<RoverModel>> =
        MutableLiveData<ArrayList<RoverModel>>(arrayListOf<>())
    var list: ArrayList<RoverModel> = arrayListOf()

    suspend fun loadRoverPhotos() {
        Log.d("AAAAABBBB", "Is mlivedata null? " + (roverModels.value == null))
        val response = Instance.getInstance().create(Api::class.java).getRoverPhotos()

        if (response.isSuccessful) {
            if (response.body() != null) {
                for (r in response.body()!!.rovers) {
                    Log.d(
                        "RoverCreatesModel",
                        "ADDING: ${r?.createRoverModel()!!.id}" + r.createRoverModel().img_src
                    )
                    list.add(r.createRoverModel())
                }
            }
        }

        roverModels.value = list

        for (r in roverModels.value!!)
            Log.d("CHTO", "" + r.id + " " + r.img_src)
    }
}




