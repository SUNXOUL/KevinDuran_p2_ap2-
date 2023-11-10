package com.sagrd.kevinduran_p2_ap2.data.remote

import com.sagrd.kevinduran_p2_ap2.data.remote.dto.GastoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GastosApi {

    @GET("gastos")
    suspend fun getGastos():List<GastoDto>

    @GET("gastos/{gastoId}")
    suspend fun getGastoById(@Path("gastoId")gastoId : Int): Response<GastoDto>

    @POST("gastos")
    suspend fun postGasto(@Body gasto: GastoDto): Response<GastoDto>

    @PUT("gastos/{gastoId}")
    suspend fun putGastos(@Body gasto: GastoDto, @Path("gastoId")gastoId : Int): Response<GastoDto>

    @DELETE("gastos/{id}")
    suspend fun deleteGasto(@Path("id") id: Int): Response<GastoDto>
}