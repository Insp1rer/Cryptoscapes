package com.example.cryptoscapes.domain

import com.example.cryptoscapes.data.models.MarketModel
import com.example.cryptoscapes.data.repository.MarketRepository

object MarketModelUseCase {
    suspend fun getMarketData(): MarketModel? {
        return MarketRepository.getMarketData()
    }
}