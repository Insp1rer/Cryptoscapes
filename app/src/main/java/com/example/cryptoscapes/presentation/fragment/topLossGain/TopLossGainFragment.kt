package com.example.cryptoscapes.presentation.fragment.topLossGain

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoscapes.models.CryptoCurrency
import com.example.cryptoscapes.databinding.FragmentTopLossGainBinding
import com.example.cryptoscapes.presentation.adapter.MarketAdapterNd
import java.util.Collections

class TopLossGainFragment : Fragment() {

    lateinit var binding: FragmentTopLossGainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTopLossGainBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("SHUBH", "TopLossGainFragment")

        val topLossGainViewModel = ViewModelProvider(this).get(TopLossGainViewModel::class.java)
        topLossGainViewModel.marketModelLivedata.observe(viewLifecycleOwner) {
            if (it != null) {
                val position = requireArguments().getInt("position")
                val dataItem = it.data.cryptoCurrencyList

                Collections.sort(dataItem) { o1, o2 ->
                    (o2.quotes[0].percentChange24h.toInt())
                        .compareTo(o1.quotes[0].percentChange24h.toInt())
                }

                binding.spinKitView.visibility = View.GONE

                Log.d("SHUBH", "${binding.spinKitView.visibility}")

                val list = ArrayList<CryptoCurrency>()

                if (position == 0) {
                    list.clear()
                    for (i in 0..9) {
                        list.add(dataItem[i])
                    }

                    binding.topGainLoseRecyclerView.adapter =
                        MarketAdapterNd(requireContext(), list)
                }
                else {
                    list.clear()
                    for (i in 0..9) {
                        list.add(dataItem[dataItem.size - 1 - i])
                    }

                    binding.topGainLoseRecyclerView.adapter =
                        MarketAdapterNd(requireContext(), list)
                }

            }
        }
        topLossGainViewModel.getMarketData()
    }



}