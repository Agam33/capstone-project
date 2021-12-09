package com.example.sportreservation.ui.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportreservation.data.source.local.entity.HistoryEntity
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.databinding.ActivityOrderBinding
import com.example.sportreservation.utils.OrderStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderActivity : AppCompatActivity(), OrderAdapter.OnItemClickCallback {

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
        if(orderList.isEmpty()) isEmptyList(true) else isEmptyList(false)

        orderAdapter.submitList(orderList)
        orderAdapter.setOnItemClickCallback(this)
        _orderBinding?.rvOrder?.apply {
            layoutManager = LinearLayoutManager(this@OrderActivity)
            setHasFixedSize(true)
            adapter = orderAdapter
        }
    }

    private fun isEmptyList(state: Boolean) {
        if(state) {
            orderBinding?.rvOrder?.visibility = View.GONE
            orderBinding?.emptyBox?.frameLayoutFavorite?.visibility = View.VISIBLE
        } else {
            orderBinding?.rvOrder?.visibility = View.VISIBLE
            orderBinding?.emptyBox?.frameLayoutFavorite?.visibility = View.GONE
        }
    }

    override fun orderDone(orderEntity: OrderEntity) {
        orderViewModel.insertHistory(
            HistoryEntity(0, orderEntity.name, OrderStatus.SELESAI, orderEntity.date)
        )
        orderViewModel.deleteOrder(orderEntity)
    }

    override fun orderCancel(orderEntity: OrderEntity) {
        orderViewModel.insertHistory(
            HistoryEntity(0, orderEntity.name, OrderStatus.BATALKAN, orderEntity.date)
        )
        orderViewModel.deleteOrder(orderEntity)
    }
}