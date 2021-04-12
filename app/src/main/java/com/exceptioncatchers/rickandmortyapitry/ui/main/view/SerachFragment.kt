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
import com.exceptioncatchers.rickandmortyapitry.databinding.SerachFragmentBinding
import com.exceptioncatchers.rickandmortyapitry.models.Character
import com.exceptioncatchers.rickandmortyapitry.ui.base.SearchViewModelFactory
import com.exceptioncatchers.rickandmortyapitry.ui.main.adapter.SearchAdapter
import com.exceptioncatchers.rickandmortyapitry.ui.main.viewmodel.SerachViewModel
import com.exceptioncatchers.rickandmortyapitry.utils.Status

class SerachFragment : Fragment() {

    private lateinit var adapter: SearchAdapter
    private val binding: SerachFragmentBinding by viewBinding()
    private var nameSearch: String = ""

    companion object {
        fun newInstance() = SerachFragment()
    }

    private lateinit var viewModel: SerachViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.serach_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUi()
        getSearchView()
        setupViewModel()
        // setupObserves()
    }

    private fun setupViewModel() {
        val viewModelfactory = SearchViewModelFactory(ApiHelper(RetrofitBuilder.api))
        viewModel = ViewModelProvider(this, viewModelfactory).get(SerachViewModel::class.java)
    }

    private fun setupUi() {
        binding.rvSearchCharacter.layoutManager = LinearLayoutManager(requireContext())
        adapter = SearchAdapter()
        binding.rvSearchCharacter.addItemDecoration(
            DividerItemDecoration(
                binding.rvSearchCharacter.context,
                (binding.rvSearchCharacter.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.rvSearchCharacter.adapter = adapter
    }

//    private fun setupObs() {
//        viewModel.listCharacter.observe(viewLifecycleOwner) {
//            adapter.addCharacter(it)
//        }
//    }

    private fun searchWord(nameS: String) {
        viewModel.getCharacterByName(nameS).observe(viewLifecycleOwner) {
            it?.data?.let {
                retriveList(it.results)
            }
        }
    }

    private fun getSearchView() {
        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchWord(query)
                }
                return true

//                viewModel.getCharacterByName(query.toString())
//                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    searchWord(query)
                }
                return true
            }

        })
    }

    private fun setupObserves() {
        viewModel.getCharacterByName("Rick").observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.rvSearchCharacter.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { data ->
                            retriveList(data.results)
                        }
                    }
                    Status.ERROR -> {
                        binding.rvSearchCharacter.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        binding.rvSearchCharacter.visibility = View.INVISIBLE
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }

            }
        }
    }

    private fun retriveList(character: List<Character>) {
        adapter.apply {
            addCharacter(character)
            notifyDataSetChanged()
        }
    }

}