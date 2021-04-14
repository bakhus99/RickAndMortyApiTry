package com.bakhus.rickandmortyapitry.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakhus.rickandmortyapitry.R
import com.bakhus.rickandmortyapitry.api.ApiHelper
import com.bakhus.rickandmortyapitry.api.RetrofitBuilder
import com.bakhus.rickandmortyapitry.databinding.CharactersFragmentBinding
import com.bakhus.rickandmortyapitry.models.Character
import com.bakhus.rickandmortyapitry.ui.base.ViewModelFactory
import com.bakhus.rickandmortyapitry.ui.main.adapter.CharacterAdapter
import com.bakhus.rickandmortyapitry.ui.main.viewmodel.CharactersViewModel
import com.bakhus.rickandmortyapitry.utils.Status

class CharactersFragment : Fragment() {

    companion object {
        fun newInstance() = CharactersFragment()
    }

    private lateinit var viewModel: CharactersViewModel
    private lateinit var adapter: CharacterAdapter
    private val binding: CharactersFragmentBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.characters_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUi()
        setupViewModel()
        setupObserves()
        easterEgg()

    }


    private fun setupViewModel() {
        val viewModelfactory = ViewModelFactory(ApiHelper(RetrofitBuilder.api))
        viewModel = ViewModelProvider(this, viewModelfactory).get(CharactersViewModel::class.java)
    }

    private fun setupUi() {
        binding.rvCharacter.layoutManager = LinearLayoutManager(requireContext())
        adapter = CharacterAdapter()
        binding.rvCharacter.addItemDecoration(
            DividerItemDecoration(
                binding.rvCharacter.context,
                (binding.rvCharacter.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.rvCharacter.adapter = adapter
    }

    private fun setupObserves() {
        viewModel.getCharacters(1).observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.rvCharacter.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { data ->
                            retriveList(data.results)
                        }
                    }
                    Status.ERROR -> {
                        binding.rvCharacter.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        binding.rvCharacter.visibility = View.INVISIBLE
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
            binding.rvCharacter.scheduleLayoutAnimation()
        }
    }

    private fun easterEgg() {
        binding.tvEasterEgg.setOnClickListener {
            val action = CharactersFragmentDirections.actionCharactersFragmentToEasterEggFragment()
            findNavController().navigate(action)
        }
    }


}