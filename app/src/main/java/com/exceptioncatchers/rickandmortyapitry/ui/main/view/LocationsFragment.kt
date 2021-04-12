package com.exceptioncatchers.rickandmortyapitry.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.exceptioncatchers.rickandmortyapitry.R
import com.exceptioncatchers.rickandmortyapitry.api.ApiHelper
import com.exceptioncatchers.rickandmortyapitry.api.RetrofitBuilder
import com.exceptioncatchers.rickandmortyapitry.databinding.LocationsFragmentBinding
import com.exceptioncatchers.rickandmortyapitry.models.LocationData
import com.exceptioncatchers.rickandmortyapitry.ui.base.LocationsViewModelFactory
import com.exceptioncatchers.rickandmortyapitry.ui.base.ViewModelFactory
import com.exceptioncatchers.rickandmortyapitry.ui.main.adapter.LocationAdapter
import com.exceptioncatchers.rickandmortyapitry.ui.main.viewmodel.LocationsViewModel
import com.exceptioncatchers.rickandmortyapitry.utils.Status

class LocationsFragment : Fragment() {

    private val binding: LocationsFragmentBinding by viewBinding()
    private lateinit var viewModel: LocationsViewModel
    private lateinit var adapter: LocationAdapter


    companion object {
        fun newInstance() = LocationsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.locations_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
        setupViewModel()
        setupObservers()
    }

    private fun setupViewModel() {
        val viewModelFactory = LocationsViewModelFactory(ApiHelper(RetrofitBuilder.api))
        viewModel = ViewModelProvider(this, viewModelFactory).get(LocationsViewModel::class.java)

    }

    private fun setupUI() {
        adapter = LocationAdapter()
        binding.rvLocations.addItemDecoration(
            DividerItemDecoration(
                binding.rvLocations.context,
                (binding.rvLocations.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.rvLocations.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getLocations(1).observe(viewLifecycleOwner) {
            it?.let { resources ->
                when (resources.status) {
                    Status.SUCCESS -> {
                        binding.rvLocations.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resources.data?.let { data ->
                            retriveList(data.results)
                        }
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.rvLocations.visibility = View.INVISIBLE
                    }
                    Status.ERROR -> {
                        binding.rvLocations.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun retriveList(locationData: List<LocationData>) {
        adapter.apply {
            addLocations(locationData)
            notifyDataSetChanged()
        }

    }

}