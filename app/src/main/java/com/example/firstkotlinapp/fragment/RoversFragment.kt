package com.example.firstkotlinapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlinapp.R
import com.example.firstkotlinapp.adapters.RoverRecyclerAdapter
import com.example.firstkotlinapp.model.RoverModel
import com.example.firstkotlinapp.viewmodels.RoversViewModel
import kotlinx.coroutines.*

class RoversFragment : Fragment() {
    private val roversViewModel: RoversViewModel by viewModels()
    lateinit var roverRecyclerAdapter: RoverRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("RoversFragment_TAG", "OnCreate")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("RoversFragment_TAG", "OnCreateView")

        val view = inflater.inflate(R.layout.fragment_rovers, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context, 1)

        roversViewModel.viewModelScope.launch {
            roversViewModel.loadRoverPhotos()
        }

        roversViewModel.roverModels.observe(viewLifecycleOwner, Observer {
            Log.d("TAGTAG", "Changed")
            roverRecyclerAdapter =
                RoverRecyclerAdapter(requireContext(), roversViewModel.roverModels.value!!)
            recyclerView.adapter = roverRecyclerAdapter
        })






        return view
    }
}