package com.bakhus.rickandmortyapitry.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakhus.rickandmortyapitry.R
import com.bakhus.rickandmortyapitry.api.ApiHelper
import com.bakhus.rickandmortyapitry.api.RetrofitBuilder
import com.bakhus.rickandmortyapitry.databinding.LocationsFragmentBinding
import com.bakhus.rickandmortyapitry.ui.base.LocationsViewModelFactory
import com.bakhus.rickandmortyapitry.ui.main.adapter.LocationAdapter
import com.bakhus.rickandmortyapitry.ui.main.viewmodel.LocationsViewModel
import com.bakhus.rickandmortyapitry.utils.Status
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LocationsFragment : Fragment() {

    private val binding: LocationsFragmentBinding by viewBinding()
    private lateinit var viewModel: LocationsViewModel
    private lateinit var adapter: LocationAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.locations_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        binding.rvLocations.scheduleLayoutAnimation()

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
                        resources.data?.let {
                            //  retriveList(data.results)
                            lifecycleScope.launch {
                                viewModel.flow.collectLatest { pagingData ->
                                    adapter.submitData(pagingData)
                                }
                            }
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

//    private fun retriveList(locationData: List<LocationData>) {
//        adapter.apply {
//            addLocations(locationData)
//            notifyDataSetChanged()
//            binding.rvLocations.scheduleLayoutAnimation()
//        }

}

