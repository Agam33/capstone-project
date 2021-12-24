package com.example.sportreservation.ui.order

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportreservation.R
import com.example.sportreservation.data.source.local.entity.HistoryEntity
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.data.source.remote.response.OrderResponse
import com.example.sportreservation.databinding.ActivityOrderBinding
import com.example.sportreservation.utils.DB_BOOKING
import com.example.sportreservation.utils.OrderStatus
import com.example.sportreservation.utils.Status
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
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

        orderViewModel.getOrderList().observe(this, {
            when(it.status) {
                Status.SUCCESS -> {
                    if(it.data != null) {
                        showOrderList(it.data)
                    }
                }
                Status.LOADING -> {}
                Status.ERROR -> {}
            }
        })
    }

    private fun showOrderList(orderList: PagedList<OrderEntity>) {
        if (orderList.isEmpty()) isEmptyList(true) else isEmptyList(false)

        orderAdapter.submitList(orderList)
        orderAdapter.setOnItemClickCallback(this)
        _orderBinding?.rvOrder?.apply {
            layoutManager = LinearLayoutManager(this@OrderActivity)
            setHasFixedSize(true)
            adapter = orderAdapter
        }
    }

    private fun isEmptyList(state: Boolean) {
        if (state) {
            orderBinding?.rvOrder?.visibility = View.GONE
            orderBinding?.emptyBox?.frameLayoutFavorite?.visibility = View.VISIBLE
        } else {
            orderBinding?.rvOrder?.visibility = View.VISIBLE
            orderBinding?.emptyBox?.frameLayoutFavorite?.visibility = View.GONE
        }
    }

    override fun orderDone(orderEntity: OrderEntity) {
        Snackbar.make(orderBinding?.root!!, "Is the reservation complete?", Snackbar.LENGTH_LONG)
            .setAction("Yes") { view ->
                orderViewModel.insertHistory(
                    HistoryEntity(0, orderEntity.name, OrderStatus.SELESAI, orderEntity.date)
                )

                dbRef = FirebaseDatabase.getInstance().getReference(SPORT_PLACE)
                dbRef.child(orderEntity.name).child(auth.uid).removeValue()

                dbRef = FirebaseDatabase.getInstance().getReference(DB_BOOKING)
                dbRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for(data in snapshot.children) {
                            val item = data.getValue(OrderResponse::class.java)
                            if(item?.userId == auth.uid) {
                                data.ref.removeValue()
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {}

                })

                orderViewModel.deleteOrder(orderEntity)

                val history = HashMap<String, String>()
                history[SPORT_NAME] = orderEntity.name
                history[ORDER_STATUS] = getString(R.string.finish)
                history[DATE] = orderEntity.date

                dbRef = FirebaseDatabase.getInstance().reference
                dbRef.child(SPORT_HISTORY).child(auth.uid).setValue(history)

            }.show()

    }

    override fun orderCancel(orderEntity: OrderEntity) {
        Snackbar.make(
            orderBinding?.root!!,
            "Are you sure you want to cancel your reservation?",
            Snackbar.LENGTH_LONG
        )
            .setAction("Yes") { view ->
                orderViewModel.insertHistory(
                    HistoryEntity(0, orderEntity.name, OrderStatus.BATALKAN, orderEntity.date)
                )
                dbRef = FirebaseDatabase.getInstance().getReference(SPORT_PLACE)
                dbRef.child(orderEntity.name).child(auth.uid).removeValue()

                val history = HashMap<String, String>()
                history[SPORT_NAME] = orderEntity.name
                history[ORDER_STATUS] = getString(R.string.cancel)
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