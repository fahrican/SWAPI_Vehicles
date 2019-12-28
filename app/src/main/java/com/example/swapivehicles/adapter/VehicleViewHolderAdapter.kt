package com.example.swapivehicles.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.swapivehicles.R
import com.example.swapivehicles.databinding.ItemVehicleBinding
import com.example.swapivehicles.model.Vehicle


class VehicleAdapter(private var vehicleList: ArrayList<Vehicle>) :
    RecyclerView.Adapter<VehicleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val itemVehicleBinding: ItemVehicleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_vehicle,
            parent,
            false
        )
        return VehicleViewHolder(itemVehicleBinding)
    }

    override fun getItemCount(): Int = vehicleList.size

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.itemVehicleBinding.vehicle = vehicleList[position]
    }

    fun setUpVehicles(listOfVehicles: List<Vehicle>) {
        vehicleList.clear()
        vehicleList.addAll(listOfVehicles)
        notifyDataSetChanged()
    }
}

class VehicleViewHolder(val itemVehicleBinding: ItemVehicleBinding) :
    RecyclerView.ViewHolder(itemVehicleBinding.root)