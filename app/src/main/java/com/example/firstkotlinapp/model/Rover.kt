package com.example.firstkotlinapp.model

import com.google.gson.annotations.SerializedName

class Rover(
    @field:SerializedName("id") private val id: Int ,

    //    @SerializedName("name")
    //    private String name;
    //
    //    @SerializedName("landing_date")
    //    private String landing_date;
    //
    //    @SerializedName("launch_date")
    //    private String launch_date;
    //
    //    @SerializedName("status")
    //    private String status;
    @field:SerializedName("img_src") private val img_src: String
){
    fun createRoverModel(): RoverModel {
        return RoverModel(id, img_src)
    }
}
