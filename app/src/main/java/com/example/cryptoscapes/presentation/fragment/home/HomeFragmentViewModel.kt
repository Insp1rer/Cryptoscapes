package com.example.cryptoscapes.presentation.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoscapes.data.models.MarketModel
import com.example.cryptoscapes.domain.MarketModelUseCase
import kotlinx.coroutines.launch

class HomeFragmentViewModel: ViewModel() {
    private val _marketModelLiveData = MutableLiveData<MarketModel>()
    val marketModelLiveData: LiveData<MarketModel> = _marketModelLiveData

    fun getData(){
        viewModelScope.launch {
            val marketModel = MarketModelUseCase.getMarketData()
            _marketModelLiveData.postValue(marketModel!!)
        }
    }
}