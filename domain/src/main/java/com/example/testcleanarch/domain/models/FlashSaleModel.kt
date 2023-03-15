package com.example.testcleanarch.domain.models



data class FlashSale (

    var flashSale : ArrayList<FlashSaleItem> = arrayListOf()
)
data class FlashSaleItem (

    var category : String? = null,
    var name     : String? = null,
    var price    : Double? = null,
    var discount : Int?    = null,
    var imageUrl : String? = null

)

