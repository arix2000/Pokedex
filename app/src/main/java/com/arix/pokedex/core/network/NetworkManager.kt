package com.arix.pokedex.core.network

import com.arix.pokedex.core.Constants.Network.API_KEY
import com.arix.pokedex.core.Constants.Network.API_KEY_HEADER
import com.arix.pokedex.core.Constants.Network.POKE_API_BASE_URL
import com.arix.pokedex.core.Constants.Network.POKE_LIST_API_BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManager {
    fun providePokeApiRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(POKE_API_BASE_URL)
            .client(getPokeApiOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(getGson()))
            .build()
    }

    fun providePokeApi(retrofit: Retrofit): PokeApiService {
        return retrofit.create(PokeApiService::class.java)
    }

    fun providePokeListsApiRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(POKE_LIST_API_BASE_URL)
            .client(getPokeListsOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(getGson()))
            .build()
    }

    fun providePokeListsApi(retrofit: Retrofit): PokeListsApiService {
        return retrofit.create(PokeListsApiService::class.java)
    }


    private fun getPokeApiOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    private fun getPokeListsOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val headerInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader(API_KEY_HEADER, API_KEY)
                .build()
            chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptor).addInterceptor(headerInterceptor)
            .build()
    }

    private fun getGson() = GsonBuilder().create()
}