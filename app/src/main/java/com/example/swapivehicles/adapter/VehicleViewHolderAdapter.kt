package com.example.swapivehicles.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swapivehicles.databinding.ItemVehicleBinding
import com.example.swapivehicles.model.Vehicle


class VehicleAdapter(private var vehicleList: ArrayList<Vehicle>) : RecyclerView.Adapter<VehicleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class VehicleViewHolder(vehicleItem: ItemVehicleBinding) :
    RecyclerView.ViewHolder(vehicleItem.root) {

    val itemVehicleBinding: ItemVehicleBinding = vehicleItem
}