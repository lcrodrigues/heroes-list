package com.example.heroeslist.ui.heroes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.heroeslist.R
import com.example.heroeslist.data.model.Hero
import com.example.heroeslist.databinding.HeroesListItemBinding

class HeroesAdapter(
    val heroesList: MutableList<Hero>,
    private val callback: (String) -> Unit
) : RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {

    inner class HeroesViewHolder(
        val heroesListItemBinding: HeroesListItemBinding
    ) : RecyclerView.ViewHolder(heroesListItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val heroesViewHolder = HeroesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.heroes_list_item,
                parent,
                false
            )
        )

        heroesViewHolder.itemView.setOnClickListener {
            val id = heroesList[heroesViewHolder.adapterPosition].id
            callback(id)
        }

        return heroesViewHolder
    }


    override fun getItemCount(): Int = heroesList.count()

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.heroesListItemBinding.hero = heroesList[position]
    }
}