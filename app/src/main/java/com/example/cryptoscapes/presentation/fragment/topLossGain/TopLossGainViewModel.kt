package com.example.cryptoscapes.presentation.fragment.topLossGain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoscapes.data.models.MarketModel
import com.example.cryptoscapes.domain.MarketModelUseCase
import kotlinx.coroutines.launch

class TopLossGainViewModel: ViewModel() {

    private val _marketModelLiveData = MutableLiveData<MarketModel>()
    val marketModelLivedata: LiveData<MarketModel> = _marketModelLiveData

    fun getMarketData(){
        viewModelScope.launch {
            val marketModel = MarketModelUseCase.getMarketData()
            _marketModelLiveData.postValue(marketModel!!)
        }
    }
}