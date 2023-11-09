package com.sagrd.kevinduran_p2_ap2.data.remote

import com.sagrd.kevinduran_p2_ap2.data.remote.dto.GastoDto
import com.sagrd.kevinduran_p2_ap2.data.remote.dto.SuplidorDto
import retrofit2.http.GET

interface SuplidorApi {
    @GET("SuplidoresGastos")
    suspend fun getSuplidores(): List<SuplidorDto>
}