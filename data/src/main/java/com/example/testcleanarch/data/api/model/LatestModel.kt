package com.example.testcleanarch.data.api.model

import com.google.gson.annotations.SerializedName

data class Latest (

    @SerializedName("latest" ) var latest : ArrayList<LatestItem> = arrayListOf()
)

data class LatestItem (

    @SerializedName("category"  ) var category : String? = null,
    @SerializedName("name"      ) var name     : String? = null,
    @SerializedName("price"     ) var price    : Int?    = null,
    @SerializedName("image_url" ) var imageUrl : String? = null

)
