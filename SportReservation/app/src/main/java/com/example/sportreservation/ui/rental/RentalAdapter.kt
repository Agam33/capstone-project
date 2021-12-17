package com.example.sportreservation.ui.rental

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportreservation.R
import com.example.sportreservation.databinding.ItemRentalBinding

class RentalAdapter: RecyclerView.Adapter<RentalAdapter.RentalViewHolder>() {

    private var listRental = ArrayList<RentalModel>()

    fun setRentalItem(listRental: ArrayList<RentalModel>) {
        this.listRental = listRental
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RentalViewHolder {
       return RentalViewHolder(
           ItemRentalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       )
    }

    override fun onBindViewHolder(holder: RentalViewHolder, position: Int) {
        val rentalModel = listRental[position]
        holder.bind(rentalModel)
    }

    override fun getItemCount(): Int = listRental.size

    inner class RentalViewHolder(private val binding: ItemRentalBinding): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(rentalModel: RentalModel) = with(binding) {
            tvDate.text = "${root.context.getString(R.string.txt_penyewaan_pada_tanggal)} ${rentalModel.date}"
            tvUsername.text = rentalModel.username
            tvEmail.text = rentalModel.email
            tvPhone.text = rentalModel.phone
            tvRentalType.text = rentalModel.rentType
            tvRentalName.text = rentalModel.rentName
            tvStartTime.text = rentalModel.startTime
            tvEndTime.text = rentalModel.endTime
        }
    }
}