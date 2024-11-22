package com.bcit.myminiapp.data

import com.google.gson.annotations.SerializedName

data class Art(
    @SerializedName("data")
    val pieces: List<ArtPiece>
)

data class ArtPiece(
    val id: String,
    val title: String,
    @SerializedName("image_id")
    val image: String?
)