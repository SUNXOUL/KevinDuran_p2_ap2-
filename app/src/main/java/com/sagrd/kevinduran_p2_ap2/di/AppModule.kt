package com.sagrd.kevinduran_p2_ap2.di

import com.sagrd.kevinduran_p2_ap2.data.remote.GastosApi
import com.sagrd.kevinduran_p2_ap2.data.remote.SuplidorApi
import com.sagrd.kevinduran_p2_ap2.data.repository.GastoRepository
import com.sagrd.kevinduran_p2_ap2.data.repository.SuplidorRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn( SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }


    @Provides
    @Singleton
    fun provideGastosRepository(gastosApi: GastosApi): GastoRepository {
        return GastoRepository(gastosApi)
    }
    @Provides
    @Singleton
    fun provideSuplidoresRepository(suplidorApi: SuplidorApi): SuplidorRepository {
        return SuplidorRepository(suplidorApi)
    }


    @Provides
    @Singleton
    fun provideGastosApi(moshi: Moshi, ): GastosApi {
        return Retrofit.Builder()
            .baseUrl("https://sag-api.azurewebsites.net/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(GastosApi::class.java)
    }

}