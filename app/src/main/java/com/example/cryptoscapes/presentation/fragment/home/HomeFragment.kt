package com.example.cryptoscapes.presentation.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.cryptoscapes.databinding.FragmentHomeBinding
import com.example.cryptoscapes.presentation.adapter.MarketAdapter
import com.example.cryptoscapes.presentation.adapter.TopLossGainPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTabLayout()

        val homeFragmentViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        homeFragmentViewModel.marketModelLiveData.observe(viewLifecycleOwner){

            binding.topCurrencyRecyclerView.adapter =
                MarketAdapter(requireContext(), it.data.cryptoCurrencyList)

            Log.d("SHUBH", "getTopCurrencyList: ${it.data.cryptoCurrencyList}")
        }
        homeFragmentViewModel.getData()

    }

    private fun setTabLayout(){
        val adapter = TopLossGainPagerAdapter(this)
        binding.contentViewPager.adapter = adapter

        binding.contentViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(position == 0){
                    binding.topGainIndicator.visibility = VISIBLE
                    binding.topGainIndicator.visibility = GONE
                }
                else{
                    binding.topGainIndicator.visibility = GONE
                    binding.topGainIndicator.visibility = VISIBLE
                }
            }
        })

        TabLayoutMediator(binding.tabLayout, binding.contentViewPager) { tab, position ->
            var title = if (position == 0) {
                "Найбільше зросли"
            }
            else {
                "Найбільше просіли"
            }
            tab.text = title
        }.attach()

    }

}