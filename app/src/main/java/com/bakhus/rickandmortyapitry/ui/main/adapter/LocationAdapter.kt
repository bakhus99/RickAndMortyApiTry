package com.bakhus.rickandmortyapitry.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bakhus.rickandmortyapitry.databinding.LocationItemBinding
import com.bakhus.rickandmortyapitry.models.LocationData

class LocationAdapter :
    PagingDataAdapter<LocationData, LocationAdapter.LocationsViewHolder>(LocationsDiff) {

    object LocationsDiff : DiffUtil.ItemCallback<LocationData>() {
        override fun areItemsTheSame(oldItem: LocationData, newItem: LocationData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: LocationData, newItem: LocationData): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    //    private var locationsList = emptyList<LocationData>()
//
    class LocationsViewHolder(private val binding: LocationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(locationData: LocationData) {
            binding.tvPlanetName.text = locationData.name
            binding.tvPlanetDimension.text = locationData.dimension
            binding.tvPlanetType.text = locationData.type
        }

    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {

        val binding =
            LocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationsViewHolder(binding)
    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
//        val binding =
//            LocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return LocationsViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
//        holder.bind(locationsList[position])
//    }
//
//    override fun getItemCount() = locationsList.size
//
//    fun addLocations(locationData: List<LocationData>) {
//        locationsList = locationData
//        notifyDataSetChanged()
//    }

}