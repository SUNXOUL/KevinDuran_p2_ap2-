package com.sagrd.kevinduran_p2_ap2.data.repository

import android.os.Build
import androidx.annotation.RequiresExtension
import com.sagrd.clientesremoto.util.Resource
import com.sagrd.kevinduran_p2_ap2.data.remote.GastosApi
import com.sagrd.kevinduran_p2_ap2.data.remote.SuplidorApi
import com.sagrd.kevinduran_p2_ap2.data.remote.dto.GastoDto
import com.sagrd.kevinduran_p2_ap2.data.remote.dto.SuplidorDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class SuplidorRepository @Inject constructor(
    private val api: SuplidorApi
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getSuplidores(): Flow<Resource<List<SuplidorDto>?>> = flow {
        try {
            emit(Resource.Loading())

            val suplidores = api.getSuplidores()

            emit(Resource.Success(suplidores))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {

            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}