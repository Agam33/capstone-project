package com.example.sportreservation.ui.main.rent.equipment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportreservation.data.source.remote.response.EquipmentResponse
import com.example.sportreservation.databinding.ItemEquipmentBinding
import com.example.sportreservation.utils.loadImage

class EquipmentAdapter : RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder>() {

    private var listEquipments = ArrayList<EquipmentResponse>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setEquipments(equipments: List<EquipmentResponse>?) {
        if (equipments == null) return
        this.listEquipments.clear()
        this.listEquipments.addAll(equipments)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder {
        val binding =
            ItemEquipmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EquipmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EquipmentViewHolder, position: Int) {
        val equipment = listEquipments[position]
        holder.bind(equipment)
    }

    override fun getItemCount(): Int = listEquipments.size

    inner class EquipmentViewHolder(private val binding: ItemEquipmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(equipment: EquipmentResponse) {
            with(binding) {
                tvName.text = equipment.name
                tvPrice.text = equipment.price
                imgEquipment.loadImage(equipment.imgUrl)
                btnRent.setOnClickListener {
                    onItemClickCallback.openBookingDialog(equipment)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun openBookingDialog(equipment: EquipmentResponse)
    }
}