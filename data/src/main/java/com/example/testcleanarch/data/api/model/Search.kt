package com.example.testcleanarch.data.api.model

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("words" ) var words : ArrayList<String> = arrayListOf()

)