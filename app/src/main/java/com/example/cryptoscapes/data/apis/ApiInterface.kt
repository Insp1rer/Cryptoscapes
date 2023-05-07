package com.example.cryptoscapes.data.apis

import com.example.cryptoscapes.data.models.MarketModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("data-api/v3/cryptocurrency/listing?start=1&limit=250")
    suspend fun getMarketData() : Response<MarketModel>
}