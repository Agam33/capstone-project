package com.example.sportreservation.ui.main.rent.referee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportreservation.data.source.remote.response.RefereeResponse
import com.example.sportreservation.databinding.ItemRefereeBinding
import com.example.sportreservation.ui.main.rent.booking.BookingFragment
import com.example.sportreservation.utils.loadImage

class RefereeAdapter : RecyclerView.Adapter<RefereeAdapter.RefereeViewHolder>() {

    private val listReferees = ArrayList<RefereeResponse>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setReferees(referees: List<RefereeResponse>?) {
        if (referees == null) return
        this.listReferees.clear()
        this.listReferees.addAll(referees)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RefereeAdapter.RefereeViewHolder {
        val binding = ItemRefereeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RefereeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RefereeAdapter.RefereeViewHolder, position: Int) {
        val referee = listReferees[position]
        holder.bind(referee)
    }

    override fun getItemCount(): Int = listReferees.size

    inner class RefereeViewHolder(private val binding: ItemRefereeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(referee: RefereeResponse) {
            with(binding) {
                tvName.text = referee.name
                tvPrice.text = referee.price
                imgReferee.loadImage(referee.imgUrl)
                btnRent.setOnClickListener {
                    onItemClickCallback.openBookingDialog()
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun openBookingDialog()
    }
}