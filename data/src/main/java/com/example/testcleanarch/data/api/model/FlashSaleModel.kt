package com.example.testcleanarch.data.api.model

import com.google.gson.annotations.SerializedName

data class FlashSale (

    @SerializedName("flash_sale" ) var flashSale : ArrayList<FlashSaleItem> = arrayListOf()
)
data class FlashSaleItem (

    @SerializedName("category"  ) var category : String? = null,
    @SerializedName("name"      ) var name     : String? = null,
    @SerializedName("price"     ) var price    : Double? = null,
    @SerializedName("discount"  ) var discount : Int?    = null,
    @SerializedName("image_url" ) var imageUrl : String? = null

)

