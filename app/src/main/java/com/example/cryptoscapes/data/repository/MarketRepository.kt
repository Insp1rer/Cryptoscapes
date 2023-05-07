package com.example.cryptoscapes.data.repository

import com.example.cryptoscapes.data.apis.ApiUtilities
import com.example.cryptoscapes.data.models.MarketModel

object MarketRepository {
    suspend fun getMarketData(): MarketModel? {
        val res = ApiUtilities.marketDataApi.getMarketData()
        return res.body()
    }
}