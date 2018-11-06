package ru.info.stock.model

data class Stock(val name: String, val price: Price, val percent_change: Double, val volume: Int, val symbol: String)
