package com.example.cryptoscapes.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.cryptoscapes.R
import com.example.cryptoscapes.databinding.FragmentDetailsBinding
import com.example.cryptoscapes.models.CryptoCurrency


class DetailsFragment : Fragment() {

    lateinit var binding : FragmentDetailsBinding

    private val item : DetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater)

        val data : CryptoCurrency = item.data!!
        setUpDetails(data)
        loadChart(data)

        return binding.root
    }

        private fun loadChart(data: CryptoCurrency) {
            binding.detaillChartWebView.settings.javaScriptEnabled = true
            binding.detaillChartWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)

            binding.detaillChartWebView.loadUrl(
                "https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol=" + item.data?.symbol
            )
        }

    private fun setUpDetails(data: CryptoCurrency) {
    binding.detailSymbolTextView.text = data.symbol

        Glide.with(requireContext()).load("https://s3.coinmarketcap.com/generated/sparklines/web/7d/usd/" + data.id + ".png")
            .thumbnail(Glide.with(requireContext()).load(R.drawable.spinner))
            .into(binding.detailImageView)

        binding.detailPriceTextView.text =
            "${String.format("$%.4f", data.quotes[0].price)} "

        if(data.quotes!![0].percentChange24h > 0){
            binding.detailChangeTextView.setTextColor(requireContext().resources.getColor(R.color.green))
            binding.detailChangeImageView.setImageResource(R.drawable.ic_caret_up)
            binding.detailChangeTextView.text = "+ ${String.format("%.02f", data.quotes[0].percentChange24h)} %"
        }else{
            binding.detailChangeTextView.setTextColor(requireContext().resources.getColor(R.color.red))
            binding.detailChangeImageView.setImageResource(R.drawable.ic_caret_down)
            binding.detailChangeTextView.text = "${String.format("%.02f", data.quotes[0].percentChange24h)} %"
        }
    }


}