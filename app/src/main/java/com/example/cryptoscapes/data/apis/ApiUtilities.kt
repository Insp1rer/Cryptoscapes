package com.example.cryptoscapes.data.apis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities {

    val marketDataApi = getRetrofit().create(ApiInterface::class.java)

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.coinmarketcap.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}