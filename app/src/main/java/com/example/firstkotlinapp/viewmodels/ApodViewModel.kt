package com.example.firstkotlinapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kotlintraining.api.Api
import com.example.kotlintraining.api.Instance
import com.example.kotlintraining.model.ApodModel

class ApodViewModel : ViewModel() {
    private val apodModels: MutableList<ApodModel> = ArrayList<ApodModel>()

    var apodModel: ApodModel? = null
        get() = field

    suspend fun loadApod() {
        val response = Instance.getInstance().create(Api::class.java).getAstronomyImageOfTheDay2()

        if (response.isSuccessful()) {
            if (response.body() != null) {
                Log.d(
                    "ResponseAPOD",
                    response.body()!!.createApodModel().copyright.toString() + "\n" +
                            response.body()!!.createApodModel().explanation
                )

                apodModel = response.body()!!.createApodModel()
                // response.body()!!.createApodModel().url?.let { loadGifWithGlide(it) }
                // explanation?.setText(response.body()!!.createApodModel().explanation)
            }
        }

    }
}
