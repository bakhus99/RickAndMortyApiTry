package com.exceptioncatchers.rickandmortyapitry.ui.main.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.exceptioncatchers.rickandmortyapitry.R
import com.exceptioncatchers.rickandmortyapitry.databinding.ChracterItemBinding
import com.exceptioncatchers.rickandmortyapitry.models.Character
import com.exceptioncatchers.rickandmortyapitry.ui.main.view.SerachFragmentDirections

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var character = emptyList<Character>()

    class SearchViewHolder(private val binding: ChracterItemBinding) :
        RecyclerView.ViewHolder(binding.root){
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ChracterItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(character[position])

        holder.itemView.setOnClickListener {
            val action = SerachFragmentDirections.actionSerachFragmentToDetailCharacterFragment(character[position])
            it.findNavController().navigate(action)
        }
    }

    fun addCharacter(characters: List<Character>) {
        character = characters
        notifyDataSetChanged()
    }


    override fun getItemCount() = character.size
}