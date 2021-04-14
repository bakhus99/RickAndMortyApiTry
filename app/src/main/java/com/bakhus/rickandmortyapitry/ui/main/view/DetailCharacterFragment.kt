package com.bakhus.rickandmortyapitry.ui.main.view

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bakhus.rickandmortyapitry.R
import com.bakhus.rickandmortyapitry.databinding.FragmentDetailCharacterBinding


class DetailCharacterFragment : Fragment(R.layout.fragment_detail_character) {


    private val binding: FragmentDetailCharacterBinding by viewBinding()
    private val args: DetailCharacterFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val character = args.character

        binding.apply {
            Glide.with(requireContext()).load(character.image).into(ivDetailCharacter)
            tvCharacterName.text = character.name
            tvCharacterSpecies.text = character.species
            tvCharacterEpisodes.text = character.episode.size.toString()
            tvCharacterGender.text = character.gender
            tvCharacterLocation.text = character.location.name
            tvCharacterOrigin.text = character.origin.name
            tvCharacterStatus.text = character.status
        }

    }


}