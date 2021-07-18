package com.example.firstkotlinapp.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.firstkotlinapp.R
import com.example.kotlintraining.api.Api
import com.example.kotlintraining.api.Instance
import com.example.firstkotlinapp.viewmodels.ApodViewModel
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch

class MainFragment() : Fragment() {
    private val viewModel: ApodViewModel by viewModels()

    private fun loadGifWithGlide(url: String) {
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL) // .placeholder(R.drawable.waiting_background)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any,
                    target: Target<Drawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .into(imageView!!)
    }

    var toMarsBtn: MaterialButton? = null
    var explanation: TextView? = null
    var imageView: ImageView? = null
    var url = "https://api.nasa.gov/planetary/apod?api_key=3OROfSQR8chlp7L1fqhm9aDF3JUgFXzLwaLFThij"

//    private val apodCallback: Callback<Apod?> = object : Callback<Apod?> {
//        override fun onResponse(call: Call<Apod?>, response: Response<Apod?>) {
//            if (response.isSuccessful()) {
//                if (response.body() != null) {
//                    Log.d(
//                        "ResponseAPOD",
//                        response.body()!!.createApodModel().copyright.toString() + "\n" +
//                                response.body()!!.createApodModel().explanation
//                    )
//                    response.body()!!.createApodModel().url?.let { loadGifWithGlide(it) }
//                    explanation?.setText(response.body()!!.createApodModel().explanation)
//                }
//            }
//        }
//
//        override fun onFailure(call: Call<Apod?>, t: Throwable) {}
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("OnCreateMainFragme", "doing")
        val api: Api = Instance.getInstance().create(Api::class.java)


        viewModel.viewModelScope.launch {
            viewModel.loadApod()
            explanation?.setText(viewModel.apodModel?.explanation)
            viewModel.apodModel?.url?.let { loadGifWithGlide(it) }
        }

      //  api.getAstronomyImageOfTheDay()?.enqueue(apodCallback)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)
        imageView = view.findViewById(R.id.apod_imgView_main)
        explanation = view.findViewById(R.id.subtitle2)
        toMarsBtn = view.findViewById(R.id.SecAct)

        return view
    }
}