package com.example.firstkotlinapp.model

class RoverModel(_id: Int, _img_src: String) {
    var id = _id
    var img_src = _img_src

    constructor(
        _id: Int, _img_src: String, _name: String,
        _loading_date: String, _launch_date: String, _status: String
    ) : this(_id, _img_src) {
        id = _id
        img_src = _img_src
    }
}

