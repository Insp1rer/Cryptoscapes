package com.example.cryptoscapes.apis

import com.example.cryptoscapes.models.MarketModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("data-api/v3/cryptocurrency/listing?start=1&limit=50")
    suspend fun getMarketData() : Response<MarketModel>
}