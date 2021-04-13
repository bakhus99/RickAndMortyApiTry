package com.exceptioncatchers.rickandmortyapitry.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exceptioncatchers.rickandmortyapitry.R
import com.exceptioncatchers.rickandmortyapitry.databinding.ChracterItemBinding
import com.exceptioncatchers.rickandmortyapitry.models.Character
import com.exceptioncatchers.rickandmortyapitry.ui.main.view.CharactersFragmentDirections
import com.google.android.material.animation.AnimationUtils

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
                tvCharacterStatus.text = character.status
                Glide.with(ivCharacter.context).load(character.image)
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
     //   holder.itemView.animation = android.view.animation.AnimationUtils.loadAnimation(holder.itemView.context,R.anim.slide_in)
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