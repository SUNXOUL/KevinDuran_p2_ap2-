package com.sagrd.kevinduran_p2_ap2.data.remote.dto

import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SuplidorDto(
    @PrimaryKey
    @Json(name = "idSuplidor")
    var idSuplidor : Int?=null,
    @Json(name = "nombres")
    var nombres : String
)