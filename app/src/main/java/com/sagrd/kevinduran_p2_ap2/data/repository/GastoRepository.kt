package com.sagrd.kevinduran_p2_ap2.data.repository

import android.os.Build
import androidx.annotation.RequiresExtension
import com.sagrd.clientesremoto.util.Resource
import com.sagrd.kevinduran_p2_ap2.data.remote.dto.GastoDto
import com.sagrd.kevinduran_p2_ap2.data.remote.GastosApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GastoRepository @Inject constructor(
    private val api: GastosApi
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getGastos (): Flow<Resource<List<GastoDto>?>> = flow {
        try {
            emit(Resource.Loading())

            val documentos = api.getGastos()

            emit(Resource.Success(documentos))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {

            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun postGastos(gastoDto: GastoDto) : GastoDto?{
        val response = api.postGasto(gastoDto)
        if (response.isSuccessful) {
            response.body()
        }
        return gastoDto
    }
    suspend fun getGastoById(gastoId : Int) : GastoDto?{
        val response = api.getGastoById(gastoId)
        if (response.isSuccessful) {
            response.body()
        }
        return response.body()
    }
    suspend fun putGastos(gastoDto: GastoDto,gastosId : Int) : GastoDto?{
        val response = api.putGastos(gasto = gastoDto, gastoId = gastosId)
        if (response.isSuccessful) {
            response.body()
        }
        return gastoDto
    }

    suspend fun deleteGastos(id: Int) : GastoDto? {
        return api.deleteGasto(id).body()
    }


}