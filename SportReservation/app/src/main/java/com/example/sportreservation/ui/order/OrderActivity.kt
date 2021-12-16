package com.example.sportreservation.ui.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportreservation.R
import com.example.sportreservation.data.source.local.entity.HistoryEntity
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.databinding.ActivityOrderBinding
import com.example.sportreservation.ui.order.input.OrderInputActivity.Companion.ORDER_STATUS
import com.example.sportreservation.utils.OrderStatus
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderActivity : AppCompatActivity(),
    OrderAdapter.OnItemClickCallback {

    private var _orderBinding: ActivityOrderBinding? = null
    private val orderBinding get() = _orderBinding

    private lateinit var auth: FirebaseUser
    private lateinit var dbRef: DatabaseReference

    private val orderViewModel: OrderViewModel by viewModel()
    private var orderAdapter = OrderAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _orderBinding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(orderBinding?.root)


        auth = FirebaseAuth.getInstance().currentUser!!

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
        Snackbar.make(orderBinding?.root!!, "Apakah reservasi sudah selesai?", Snackbar.LENGTH_LONG)
            .setAction("Yes") {view ->
                orderViewModel.insertHistory(
                    HistoryEntity(0, orderEntity.name, OrderStatus.SELESAI, orderEntity.date)
                )
                dbRef = FirebaseDatabase.getInstance().getReference(SPORT_PLACE)
                dbRef.child(orderEntity.name).child(auth.uid).removeValue()
                orderViewModel.deleteOrder(orderEntity)

                val history = HashMap<String, String>()
                history[SPORT_NAME] = orderEntity.name
                history[ORDER_STATUS] = getString(R.string.txt_done)
                history[DATE] = orderEntity.date

                dbRef = FirebaseDatabase.getInstance().reference
                dbRef.child(SPORT_HISTORY).child(auth.uid).setValue(history)

            }.show()

    }

    override fun orderCancel(orderEntity: OrderEntity) {
        Snackbar.make(orderBinding?.root!!, "Apakah Anda yakin ingin membatalkan reservasi?", Snackbar.LENGTH_LONG)
            .setAction("Yes") {view ->
                orderViewModel.insertHistory(
                    HistoryEntity(0, orderEntity.name, OrderStatus.BATALKAN, orderEntity.date)
                )
                dbRef = FirebaseDatabase.getInstance().getReference(SPORT_PLACE)
                dbRef.child(orderEntity.name).child(auth.uid).removeValue()

                val history = HashMap<String, String>()
                history[SPORT_NAME] = orderEntity.name
                history[ORDER_STATUS] = getString(R.string.txt_dibatalkan)
                history[DATE] = orderEntity.date

                dbRef = FirebaseDatabase.getInstance().reference
                dbRef.child(SPORT_HISTORY).child(auth.uid).setValue(history)

                orderViewModel.deleteOrder(orderEntity)
            }.show()

    }

    companion object {
        const val SPORT_PLACE = "Sport Place"
        const val SPORT_HISTORY = "Order History"
        const val SPORT_NAME = "name"
        const val ORDER_STATUS = "orderStatus"
        const val DATE = "date"
    }
}