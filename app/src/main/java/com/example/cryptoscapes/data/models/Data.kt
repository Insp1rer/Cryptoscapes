package com.example.cryptoscapes.data.models

import com.example.cryptoscapes.models.CryptoCurrency

data class Data(
    val cryptoCurrencyList: List<CryptoCurrency>,
    val totalCount: String
)