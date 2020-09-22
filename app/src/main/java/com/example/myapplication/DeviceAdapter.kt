package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_device.view.*

class DeviceAdapter : RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {

    private var deviceList = mutableListOf<String>()

    class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(device: String) {
            itemView.name.text = device
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder =
        DeviceViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_device, parent, false)
        )


    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.bind(deviceList[position])
    }

    override fun getItemCount(): Int = deviceList.size
}