package ru.info.stock.adapter

import android.support.v7.util.DiffUtil
import ru.info.stock.R
import ru.info.stock.model.Stock

class StockAdapter : DataBindingAdapter<Stock>(DiffCallback()) {
    class DiffCallback : DiffUtil.ItemCallback<Stock>() {

        override fun areItemsTheSame(p0: Stock, p1: Stock) = p0 == p1

        override fun areContentsTheSame(p0: Stock, p1: Stock) = areItemsTheSame(p0, p1)
    }

    override fun getItemViewType(position: Int) = R.layout.item_stock

} 