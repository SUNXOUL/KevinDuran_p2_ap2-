package com.sagrd.kevinduran_p2_ap2.data.remote.dto

import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GastoDto (
    @PrimaryKey
    @Json(name = "idGasto")
    var idGasto : Int?=null,
    @Json(name = "fecha")
    var fecha : String,
    @Json(name = "idSuplidor")
    var idSuplidor : Int,
    @Json(name = "ncf")
    var ncf : String,
    @Json(name = "concepto")
    var concepto : String,
    @Json(name = "descuento")
    var descuento : Int,
    @Json(name = "itbis")
    var itbis : Int,
    @Json(name = "monto")
    var monto : Int
)