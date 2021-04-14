package com.bakhus.rickandmortyapitry.ui.main.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bakhus.rickandmortyapitry.R
import com.bakhus.rickandmortyapitry.databinding.ChracterItemBinding
import com.bakhus.rickandmortyapitry.models.Character
import com.bakhus.rickandmortyapitry.ui.main.view.CharactersFragmentDirections

class CharacterAdapter() :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var character = emptyList<Character>()
    private var lastPosition = -1

    class CharacterViewHolder(private val binding: ChracterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.apply {
                tvCharacterName.text = character.name
                tvCharacterSpecies.text = character.species
                when (character.status) {
                    "Alive" -> {
                        tvCharacterStatus.setTextColor(Color.YELLOW)
                    }
                    "unknown" -> {
                        tvCharacterStatus.setTextColor(Color.BLUE)
                    }
                    else -> {
                        tvCharacterStatus.setTextColor(Color.RED)
                    }
                }
              tvCharacterStatus.text = character.status
                Glide.with(ivCharacter.context).load(character.image)
                    .placeholder(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(ivCharacter)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            ChracterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(character[position])
        holder.itemView.setOnClickListener {
            val action =
                CharactersFragmentDirections.actionCharactersFragmentToDetailCharacterFragment(
                    character[position]
                )
            it.findNavController().navigate(action)
        }
    }

    fun addCharacter(characters: List<Character>) {
        character = characters
        notifyDataSetChanged()
    }

    override fun getItemCount() = character.size

}