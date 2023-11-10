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
    var fecha : String?=null,
    @Json(name = "idSuplidor")
    var idSuplidor : Int?=null,
    @Json(name = "ncf")
    var ncf : String?=null,
    @Json(name = "concepto")
    var concepto : String?=null,
    @Json(name = "descuento")
    var descuento : Int?=null,
    @Json(name = "itbis")
    var itbis : Int?=null,
    @Json(name = "monto")
    var monto : Int?=null,
)