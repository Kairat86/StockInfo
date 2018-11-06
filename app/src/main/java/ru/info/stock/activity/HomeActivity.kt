package ru.info.stock.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_main.*
import ru.info.stock.R
import ru.info.stock.adapter.StockAdapter
import ru.info.stock.model.Stock
import ru.info.stock.model.StockViewModel

class HomeActivity : AppCompatActivity() {
    companion object {
        private val TAG = HomeActivity::class.java.simpleName
    }

    private lateinit var viewModel: StockViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvStocks.adapter = StockAdapter()
        viewModel = ViewModelProviders.of(this).get(StockViewModel::class.java)
        viewModel.getStockLiveData().observe(this, Observer<List<Stock>> {
            prgrBar.visibility = GONE
            Log.i(TAG, "received update")
            it.let((rvStocks.adapter as StockAdapter)::submitList)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun refresh(item: MenuItem) {
        prgrBar.visibility = VISIBLE
        viewModel.updateManually()
    }
}
