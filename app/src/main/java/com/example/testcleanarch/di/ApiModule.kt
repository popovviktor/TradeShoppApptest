package com.example.testcleanarch.di

import com.example.testcleanarch.data.api.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object ApiModule {
    private const val BASE_URL = "https://run.mocky.io/"
    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
    }
    @Provides
    @Singleton
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    @Provides
    @Singleton
    fun providesService(retrofit: Retrofit)=
        retrofit.create(Service::class.java)
}