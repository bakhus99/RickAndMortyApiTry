package com.exceptioncatchers.rickandmortyapitry.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.exceptioncatchers.rickandmortyapitry.R
import com.exceptioncatchers.rickandmortyapitry.databinding.FragmentLocationDetailBinding
import com.exceptioncatchers.rickandmortyapitry.ui.main.adapter.CharacterAdapter

class LocationDetailFragment : Fragment() {

    private val binding: FragmentLocationDetailBinding by viewBinding()
    private val args:LocationDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val location = args.location
        binding.tvLocationResidents.text = location.name

    }


}