package com.willowtreeapps.mintweather.di

import com.willowtreeapps.mintweather.api.DarkSkyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DarkSkyModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): DarkSkyApi {
        return Retrofit.Builder().addConverterFactory(
            MoshiConverterFactory.create()
        ).baseUrl(DarkSkyApi.BASE_URL)
            .client(okHttpClient)
            .build().create(DarkSkyApi::class.java)
    }
}