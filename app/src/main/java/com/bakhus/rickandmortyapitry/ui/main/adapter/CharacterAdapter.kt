package com.bakhus.rickandmortyapitry.ui.main.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bakhus.rickandmortyapitry.R
import com.bakhus.rickandmortyapitry.databinding.ChracterItemBinding
import com.bakhus.rickandmortyapitry.models.Character
import com.bakhus.rickandmortyapitry.ui.main.view.CharactersFragmentDirections
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class CharacterAdapter:
    PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharactersDiff) {

   // private var character = emptyList<Character>()

    object CharactersDiff : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }


    }

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

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        }
        holder.itemView.setOnClickListener {
            val action =
                CharactersFragmentDirections.actionCharactersFragmentToDetailCharacterFragment(
                    item!!
                )
            it.findNavController().navigate(action)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            ChracterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }


//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
//        val binding =
//            ChracterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return CharacterViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
//        holder.bind(character[position])
//        holder.itemView.setOnClickListener {
//            val action =
//                CharactersFragmentDirections.actionCharactersFragmentToDetailCharacterFragment(
//                    character[position]
//                )
//            it.findNavController().navigate(action)
//        }
//    }
//
//    fun addCharacter(characters: List<Character>) {
//        character = characters
//        notifyDataSetChanged()
//    }

   // override fun getItemCount() = character.size

}