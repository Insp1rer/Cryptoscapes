package com.example.cryptoscapes.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptoscapes.R
import com.example.cryptoscapes.databinding.TopCurrencyLayoutBinding
import com.example.cryptoscapes.models.CryptoCurrency

class MarketAdapter(var context: Context, val list: List<CryptoCurrency>) :
    RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {
    inner class MarketViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = TopCurrencyLayoutBinding.bind(view)

        fun bind(cryptoCurrency: CryptoCurrency){

            binding.topCurrencyNameTextView.text = cryptoCurrency.name

            Glide.with(binding.topCurrencyImageView.context).load("https://s2.coinmarketcap.com/static/img/coins/64x64/" + cryptoCurrency.id + ".png")
                .thumbnail(Glide.with(context).load(R.drawable.spinner))
                .into(binding.topCurrencyImageView)

            if (cryptoCurrency.quotes[0].percentChange24h > 0) {
                binding.topCurrencyChangeTextView.setTextColor(context.resources.getColor(R.color.green))
                binding.topCurrencyChangeTextView.text =
                    "+ ${String.format("%.02f", cryptoCurrency.quotes[0].percentChange24h)} %"
            } else {
                binding.topCurrencyChangeTextView.setTextColor(context.resources.getColor(R.color.red))
                binding.topCurrencyChangeTextView.text =
                    "${String.format("%.02f", cryptoCurrency.quotes[0].percentChange24h)} %"
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.top_currency_layout, parent, false)
        return MarketViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}