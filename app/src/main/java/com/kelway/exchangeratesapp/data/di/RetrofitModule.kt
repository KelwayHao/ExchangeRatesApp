package com.kelway.exchangeratesapp.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kelway.exchangeratesapp.data.retrofit.ExchangeApiService
import com.kelway.exchangeratesapp.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    fun provideConverter(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideExchangeService(retrofit: Retrofit): ExchangeApiService {
        return retrofit.create(ExchangeApiService::class.java)
    }
}