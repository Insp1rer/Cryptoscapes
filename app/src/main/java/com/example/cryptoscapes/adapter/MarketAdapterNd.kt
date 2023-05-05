package com.example.cryptoscapes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptoscapes.R
import com.example.cryptoscapes.databinding.CurrencyItemLayoutBinding
import com.example.cryptoscapes.models.CryptoCurrency

class MarketAdapterNd(var context : Context, var list: List<CryptoCurrency>) : RecyclerView.Adapter<MarketAdapterNd.MarketNdViewHolder>(){

    inner class MarketNdViewHolder(view: View) : RecyclerView.ViewHolder(view){
    var binding = CurrencyItemLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketNdViewHolder {
        return MarketNdViewHolder(LayoutInflater.from(context).inflate(R.layout.currency_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: MarketNdViewHolder, position: Int) {
        val item = list[position]
        holder.binding.currencyNameTextView.text = item.name
        holder.binding.currencySymbolTextView.text = item.symbol

        Glide.with(context).load("https://s2.coinmarketcap.com/static/img/coins/64x64/" + item.id + ".png")
            .thumbnail(Glide.with(context).load(R.drawable.spinner))
            .into(holder.binding.currencyImageView)

        Glide.with(context).load("https://s3.coinmarketcap.com/generated/sparklines/web/7d/usd/" + item.id + ".png")
            .thumbnail(Glide.with(context).load(R.drawable.spinner))
            .into(holder.binding.currencyChartImageView)

        holder.binding.currencyPriceTextView.text =
        "${String.format("$%.02f", item.quotes[0].price)} "

        if(item.quotes!![0].percentChange24h > 0){
            holder.binding.currencyChangeTextView.setTextColor(context.resources.getColor(R.color.green))
            holder.binding.currencyChangeTextView.text = "+ ${String.format("%.02f", item.quotes[0].percentChange24h)} %"
        }else{
            holder.binding.currencyChangeTextView.setTextColor(context.resources.getColor(R.color.red))
            holder.binding.currencyChangeTextView.text = "${String.format("%.02f", item.quotes[0].percentChange24h)} %"
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }



}