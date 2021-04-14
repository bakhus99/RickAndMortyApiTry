package com.bakhus.rickandmortyapitry.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bakhus.rickandmortyapitry.databinding.LocationItemBinding
import com.bakhus.rickandmortyapitry.models.LocationData
import com.bakhus.rickandmortyapitry.ui.main.view.LocationsFragmentDirections

class LocationAdapter : RecyclerView.Adapter<LocationAdapter.LocationsViewHolder>() {

    private var locationsList = emptyList<LocationData>()

    class LocationsViewHolder(private val binding: LocationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(locationData: LocationData) {
            binding.tvPlanetName.text = locationData.name
            binding.tvPlanetDimension.text = locationData.dimension
            binding.tvPlanetType.text = locationData.type
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        val binding =
            LocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        holder.bind(locationsList[position])
        holder.itemView.setOnClickListener {
            val action =
                LocationsFragmentDirections.actionLocationsFragmentToLocationDetailFragment(
                    locationsList[position]
                )
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = locationsList.size

    fun addLocations(locationData: List<LocationData>) {
        locationsList = locationData
        notifyDataSetChanged()
    }

}