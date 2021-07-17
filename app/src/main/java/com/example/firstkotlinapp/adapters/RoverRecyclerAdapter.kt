package com.example.firstkotlinapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlinapp.R
import com.example.firstkotlinapp.model.RoverModel
import java.util.*

class RoverRecyclerAdapter(context: Context, roverModels: ArrayList<RoverModel>) :
    RecyclerView.Adapter<RoverViewHolder>() {
    var context: Context
    var roverModels: List<RoverModel>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoverViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.rover_item, parent, false)
        return RoverViewHolder(context, view)
    }

    override fun onBindViewHolder(holder: RoverViewHolder, position: Int) {
        val roverModel: RoverModel = roverModels[position]
        Log.d("AdapterBinding", roverModel.img_src)
        holder.loadImage(roverModel.img_src)
    }

    override fun getItemCount(): Int {
        return roverModels.size
    }

    init {
        Log.d("RoverAdapter", "ADAPTER")
        this.context = context
        this.roverModels = roverModels
    }
}