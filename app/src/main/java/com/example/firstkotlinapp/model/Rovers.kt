package com.example.firstkotlinapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class Rovers (
    @field:SerializedName("photos")
    val rovers: ArrayList<Rover?>

)

