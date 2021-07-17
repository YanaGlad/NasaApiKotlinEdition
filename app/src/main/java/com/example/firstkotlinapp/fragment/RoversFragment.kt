package com.example.firstkotlinapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlinapp.R
import com.example.firstkotlinapp.adapters.RoverRecyclerAdapter
import com.example.firstkotlinapp.model.Rover
import com.example.firstkotlinapp.model.RoverModel
import com.example.firstkotlinapp.model.Rovers
import com.example.kotlintraining.api.Api
import com.example.kotlintraining.api.Instance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RoversFragment : Fragment() {
    private val roverModels = ArrayList<RoverModel>()
    fun createListOfRoverModels(rovers: ArrayList<Rover?>) {
        for (r in rovers) {
            Log.d("RoverCreatesModel", "ADDING: " + r?.createRoverModel()!!.img_src)
            r.let { roverModels.add(it.createRoverModel()) }
        }
    }

    fun loadRovers() {
        val service = Instance.getInstance().create(Api::class.java)
        service.getMarsPhotos()!!.enqueue(roverCallback)
    }

    private val roverCallback: Callback<Rovers?> = object : Callback<Rovers?> {
        override fun onResponse(call: Call<Rovers?>, response: Response<Rovers?>) {
            if (response.isSuccessful) {
                if (response.body() != null) {
                    createListOfRoverModels(response.body()!!.rovers)
                }
            }
            Log.d("Response callback", "RoverModels size is... " + roverModels.size)
        }

        override fun onFailure(call: Call<Rovers?>, t: Throwable) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadRovers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rovers, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        Log.d("ON CREATE VIEW", "Size of rover models: " + roverModels.size)
        for (rover in roverModels) {
            Log.d("RoverCreatesModel", "Src: " + rover.img_src)
        }
        val roverRecyclerAdapter = RoverRecyclerAdapter(requireContext(), roverModels)
        recyclerView.adapter = roverRecyclerAdapter
        return view
    }
}