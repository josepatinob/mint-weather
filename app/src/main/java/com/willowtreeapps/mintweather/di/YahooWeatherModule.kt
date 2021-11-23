package com.willowtreeapps.mintweather.di

import com.willowtreeapps.mintweather.api.YahooWeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class YahooWeatherModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): YahooWeatherApi {
        return Retrofit.Builder().addConverterFactory(
            MoshiConverterFactory.create()
        ).baseUrl(YahooWeatherApi.BASE_URL).client(okHttpClient).build()
            .create(YahooWeatherApi::class.java)
    }
}