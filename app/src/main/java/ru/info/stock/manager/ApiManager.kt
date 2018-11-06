package ru.info.stock.manager

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.info.stock.model.StockServiceResponse
import java.util.concurrent.TimeUnit

class ApiManager {

    companion object {
        private const val BASE_URL = "http://phisix-api3.appspot.com/"
    }

    private val apiService: StockService by lazy {
        Retrofit.Builder()
            .client(OkHttpClient.Builder().readTimeout(15, TimeUnit.SECONDS).build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(StockService::class.java)
    }

    fun getStocks(clbk: Callback<StockServiceResponse>) = apiService.get().enqueue(clbk)

    interface StockService {
        @GET("stocks.json")
        fun get(): Call<StockServiceResponse>
    }
}