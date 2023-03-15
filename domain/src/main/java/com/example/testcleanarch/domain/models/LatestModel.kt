package com.example.testcleanarch.domain.models



data class Latest (

    var latest : ArrayList<LatestItem> = arrayListOf()
)

data class LatestItem (

    var category : String? = null,
    var name     : String? = null,
    var price    : Int?    = null,
    var imageUrl : String? = null

)
