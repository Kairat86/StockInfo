package ru.info.stock.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Handler
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.info.stock.manager.ApiManager

class StockViewModel : ViewModel(), Runnable {

    companion object {
        private val TAG = StockViewModel::class.java.simpleName
    }

    private lateinit var stockLiveData: MutableLiveData<List<Stock>>
    private val apiManager = ApiManager()
    private val handler = Handler()
    fun getStockLiveData(): LiveData<List<Stock>> {
        if (!::stockLiveData.isInitialized) {
            stockLiveData = MutableLiveData()
            loadStocks()

        }
        return stockLiveData
    }

    private fun loadStocks() {
        run()
    }

    override fun run() {
        apiManager.getStocks(object : Callback<StockServiceResponse> {
            override fun onResponse(call: Call<StockServiceResponse>, response: Response<StockServiceResponse>) {
                Log.i(TAG, "Update")
                stockLiveData.value = response.body()?.stock
                handler.postDelayed(this@StockViewModel, 15000)
            }

            override fun onFailure(call: Call<StockServiceResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun updateManually() {
        handler.removeCallbacks(this)
        run()
    }
}