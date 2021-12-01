package com.example.sportreservation.ui.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.databinding.ActivityOrderBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderActivity : AppCompatActivity() {

    private var _orderBinding: ActivityOrderBinding? = null
    private val orderBinding get() = _orderBinding

    private val orderViewModel: OrderViewModel by viewModel()
    private var orderAdapter = OrderAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _orderBinding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(orderBinding?.root)

        setSupportActionBar(orderBinding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        orderViewModel.getOrderList().observe(this, Observer(this::showOrderList))
    }

    private fun showOrderList(orderList: PagedList<OrderEntity>) {
        orderAdapter.submitList(orderList)

        _orderBinding?.rvOrder?.apply {
            layoutManager = LinearLayoutManager(this@OrderActivity)
            setHasFixedSize(true)
            adapter = orderAdapter
        }
    }

}